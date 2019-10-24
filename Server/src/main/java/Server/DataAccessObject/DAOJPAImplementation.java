package Server.DataAccessObject;

import Server.Entities.AccommodationEntity;
import Server.Entities.PackageEntity;
import Server.Entities.TicketEntity;
import Server.EventHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

/**
 * Persistencia que usa o JPA (Java Persistence API) utilizado pelo hibernate para
 * manipular o banco de dados.
 */
public class DAOJPAImplementation implements DAO {
    @Override
    public void createTickets(TicketEntity newTicket) {
        try {
            EntityManager entityManager = JPASessionFactory.createEntityManager();

            entityManager.getTransaction().begin();
            entityManager.persist(newTicket);

            // Notifica o cliente sobre a nova passagem aérea disponível
            EventHandler.notifyClients(newTicket);

            // Faz uma busca na tabela de vagas por vagas com mesmo destino, datas e número de pessoas
            Query getMactchingAccommodations = entityManager.createQuery(
                "SELECT ae FROM AccommodationEntity ae WHERE " +
                    "ae.location = :location AND " +
                    "ae.entranceDate = :entranceDate AND " +
                    "ae.exitDate = :exitDate AND " +
                    "ae.numPeople = :numPeople",
                AccommodationEntity.class
            );
            getMactchingAccommodations.setParameter("location", newTicket.getDestination());
            getMactchingAccommodations.setParameter("entranceDate", newTicket.getDepartureDate());
            getMactchingAccommodations.setParameter("exitDate", newTicket.getReturnDate());
            getMactchingAccommodations.setParameter("numPeople", newTicket.getNumPeople());

            List<AccommodationEntity> matchingAccommodations = getMactchingAccommodations.getResultList();

            // Cria pacotes associando as passagens e as vagas que combinaram
            for(AccommodationEntity accommodation : matchingAccommodations) {
                PackageEntity p = new PackageEntity(newTicket, accommodation);
                entityManager.persist(p);
                // Notifica o cliente sobre o novo pacote
                EventHandler.notifyClients(p);
            }

            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (EntityExistsException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createAccommodations(AccommodationEntity newAccommodation) {
        try {
            EntityManager entityManager = JPASessionFactory.createEntityManager();

            entityManager.getTransaction().begin();
            entityManager.persist(newAccommodation);

            // Notifica o cliente sobre a nova vaga de hospedagem
            EventHandler.notifyClients(newAccommodation);

            // Faz uma busca na tabela de passagens por passagens com mesmo destino,
            // datas e número de pessoas
            Query getMactchingTickets = entityManager.createQuery(
                    "SELECT te FROM TicketEntity te WHERE " +
                            "te.destination = :location AND " +
                            "te.departureDate = :entranceDate AND " +
                            "te.returnDate = :exitDate AND " +
                            "te.numPeople = :numPeople",
                    TicketEntity.class
            );
            getMactchingTickets.setParameter("location", newAccommodation.getLocation());
            getMactchingTickets.setParameter("entranceDate", newAccommodation.getEntranceDate());
            getMactchingTickets.setParameter("exitDate", newAccommodation.getExitDate());
            getMactchingTickets.setParameter("numPeople", newAccommodation.getNumPeople());

            List<TicketEntity> matchingTickets = getMactchingTickets.getResultList();

            // Cria pacotes associando essa vaga as passagens que combinaram
            for(TicketEntity ticket : matchingTickets) {
                PackageEntity p = new PackageEntity(ticket, newAccommodation);
                entityManager.persist(p);

                // Notifica os clientes sobre o novo pacote disponível
                EventHandler.notifyClients(p);
            }

            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (EntityExistsException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TicketEntity> searchTickets(String ticketMode, String origin, String destination,
                                            LocalDate departingDate, LocalDate returningDate, int people) {
        EntityManager entityManager = JPASessionFactory.createEntityManager();

        entityManager.getTransaction().begin();

        // Realiza uma busca afim de encontrar as passagens
        Query getSimilarTickets = entityManager.createQuery(
                "SELECT te FROM TicketEntity te WHERE " +
                        "te.ticketType = :type AND " +
                        "te.origin = :origin AND " +
                        "te.destination = :destination AND " +
                        "te.departureDate = :departingDate AND " +
                        "te.returnDate = :returningDate AND " +
                        "te.numPeople = :people",
                TicketEntity.class
        );
        getSimilarTickets.setParameter("type", ticketMode);
        getSimilarTickets.setParameter("origin", origin);
        getSimilarTickets.setParameter("destination", destination);
        getSimilarTickets.setParameter("departingDate", departingDate);
        getSimilarTickets.setParameter("returningDate", returningDate);
        getSimilarTickets.setParameter("people", people);

        List<TicketEntity> results = getSimilarTickets.getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        return results;
    }

    @Override
    public List<AccommodationEntity> searchAccommodations(String hotelOrCityName, LocalDate entranceDate,
                                                   LocalDate exitDate, int rooms, int people) {
        EntityManager entityManager = JPASessionFactory.createEntityManager();

        entityManager.getTransaction().begin();

        // Realiza uma busca afim de encontrar as vagas de hospedagem
        Query getSimilarAccommodations = entityManager.createQuery(
                "SELECT ae FROM AccommodationEntity ae WHERE " +
                        "(ae.location = :location OR ae.hotel = :location) AND " +
                        "ae.entranceDate = :entranceDate AND " +
                        "ae.exitDate = :exitDate AND " +
                        "ae.numRooms = :rooms AND " +
                        "ae.numPeople = :people",
                AccommodationEntity.class
        );
        getSimilarAccommodations.setParameter("location", hotelOrCityName);
        getSimilarAccommodations.setParameter("entranceDate", entranceDate);
        getSimilarAccommodations.setParameter("exitDate", exitDate);
        getSimilarAccommodations.setParameter("rooms", rooms);
        getSimilarAccommodations.setParameter("people", people);

        List<AccommodationEntity> results = getSimilarAccommodations.getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        return results;
    }

    @Override
    public List<PackageEntity> searchPackages(String ticketMode, String origin, String hotelOrCityName,
                                              int rooms, LocalDate departingDate, LocalDate returningDate,
                                              int people) {
        EntityManager entityManager = JPASessionFactory.createEntityManager();

        entityManager.getTransaction().begin();

        // Realiza uma busca afim de encontrar os pacotes
        Query searchPackages = entityManager.createQuery(
                "SELECT pe FROM PackageEntity pe WHERE " +
                        "pe.ticketEntity.ticketType = :type AND " +
                        "pe.ticketEntity.origin = :origin AND " +
                        "(pe.ticketEntity.destination = :location OR pe.accommodationEntity.hotel = :location) AND " +
                        "pe.accommodationEntity.numRooms = :rooms AND " +
                        "pe.ticketEntity.departureDate = :departingDate AND " +
                        "pe.ticketEntity.returnDate = :returningDate AND " +
                        "pe.accommodationEntity.numPeople = :people",
                PackageEntity.class
        );

        searchPackages.setParameter("type", ticketMode);
        searchPackages.setParameter("origin", origin);
        searchPackages.setParameter("location", hotelOrCityName);
        searchPackages.setParameter("rooms", rooms);
        searchPackages.setParameter("departingDate", departingDate);
        searchPackages.setParameter("returningDate", returningDate);
        searchPackages.setParameter("people", people);

        List<PackageEntity> results = searchPackages.getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        return results;
    }

    @Override
    public TicketEntity findTickets(int id) {
        EntityManager entityManager = JPASessionFactory.createEntityManager();

        entityManager.getTransaction().begin();
        TicketEntity ticketEntity = entityManager.find(TicketEntity.class, id);
        entityManager.getTransaction().commit();

        entityManager.close();
        return ticketEntity;
    }

    @Override
    public AccommodationEntity findAccommodations(int id) {
        EntityManager entityManager = JPASessionFactory.createEntityManager();

        entityManager.getTransaction().begin();
        AccommodationEntity accommodationEntity = entityManager.find(AccommodationEntity.class, id);
        entityManager.getTransaction().commit();

        entityManager.close();
        return accommodationEntity;
    }

    @Override
    public PackageEntity findPackage(int id) {
        EntityManager entityManager = JPASessionFactory.createEntityManager();

        entityManager.getTransaction().begin();
        PackageEntity packageEntity = entityManager.find(PackageEntity.class, id);
        entityManager.getTransaction().commit();

        entityManager.close();
        return packageEntity;
    }

    @Override
    public void removeTickets(int ticketID) {
        EntityManager entityManager = JPASessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        TicketEntity ticketToRemove = entityManager.find(TicketEntity.class,ticketID);
        entityManager.remove( entityManager.merge(ticketToRemove) );

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void removeAccommodations(int accommodationId) {
        EntityManager entityManager = JPASessionFactory.createEntityManager();

        entityManager.getTransaction().begin();

        AccommodationEntity accommodationToRemove = entityManager.find(
                AccommodationEntity.class, accommodationId);
        entityManager.remove( entityManager.merge(accommodationToRemove));

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void removePackage(int packageId) {
        EntityManager entityManager = JPASessionFactory.createEntityManager();

        entityManager.getTransaction().begin();

        PackageEntity packageToRemove = entityManager.find(PackageEntity.class, packageId);
        entityManager.remove(packageToRemove);

        TicketEntity ticketToRemove = entityManager.find(
                TicketEntity.class, packageToRemove.getTicketEntity().getId());
        entityManager.remove(ticketToRemove);

        AccommodationEntity accommodationToRemove = entityManager.find(
                AccommodationEntity.class, packageToRemove.getAccommodationEntity().getId());
        entityManager.remove(accommodationToRemove);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
