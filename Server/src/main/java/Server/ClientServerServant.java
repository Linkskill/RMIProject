package Server;

import Server.Entities.AccommodationEntity;
import Server.Entities.PackageEntity;
import Server.Entities.TicketEntity;
import Server.DataAccessObject.DAO;
import Models.*;

import Server.Interfaces.ClientInterface;
import Server.Interfaces.ServerInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por implementar os métodos da Interface do servidor (ServerInterface)
 */
public class ClientServerServant extends UnicastRemoteObject implements ServerInterface {
    private DAO dao;

    /**
     * Construtor da classe ClientServerServant, responsável por inicializar o objeto da interface DAO.
     * @param dao objeto da interface DAO (Data Access Object) implementada por DAOJPAImplementation
     * @throws RemoteException avisa casos de exceção na criação de objetos remotos
     */
    public ClientServerServant(DAO dao) throws RemoteException { // Construtor
        this.dao = dao;
    }

    @Override
    public List<TicketData> getTickets(String ticketMode, String origin, String destination,
                                       LocalDate departingDate, LocalDate returningDate,
                                       int people) throws RemoteException {
        System.out.println("Consulta por passagens aéreas: ");
        System.out.println("  Tipo: " + ticketMode);
        System.out.println("  Origem: " + origin);
        System.out.println("  Destino: " + destination);
        System.out.println("  Data de partida: " + departingDate);
        System.out.println("  Data de retorno: " + returningDate);
        System.out.println("  Número de pessoas: " + people);

        List<TicketEntity> similarTickets = dao.searchTickets(ticketMode, origin,
                destination, departingDate, returningDate, people);

        System.out.println("Encontrados: " + similarTickets.size());

        List<TicketData> tickets = new ArrayList<>();
        for(TicketEntity ticket : similarTickets) {
            tickets.add(new TicketData(ticket));
        }
        return tickets;
    }

    @Override
    public List<AccommodationData> getAccommodations(String hotelOrCityName, LocalDate entranceDate,
                                                     LocalDate exitDate, int rooms,
                                                     int people) throws RemoteException {
        System.out.println("Consulta por vagas de hospedagem: ");
        System.out.println("  Destino ou hotel: " + hotelOrCityName);
        System.out.println("  Data de entrada: " + entranceDate);
        System.out.println("  Data de saida: " + exitDate);
        System.out.println("  Número de quartos: " + rooms);
        System.out.println("  Número de pessoas: " + people);

        List<AccommodationEntity> similarAccommodations = dao.searchAccommodations(
                hotelOrCityName, entranceDate, exitDate,
                rooms, people);

        System.out.println("Encontrados: " + similarAccommodations.size());

        List<AccommodationData> accommodations = new ArrayList<>();
        for(AccommodationEntity accommodation : similarAccommodations) {
            accommodations.add(new AccommodationData(accommodation));
        }
        return accommodations;
    }

    @Override
    public List<PackageData> getPackages(String ticketMode, String origin, String hotelOrCityName,
                              int rooms, LocalDate departingDate, LocalDate returningDate,
                              int people) throws RemoteException {
        System.out.println("Consulta por pacotes: ");
        System.out.println("  Tipo: " + ticketMode);
        System.out.println("  Origem: " + origin);
        System.out.println("  Destino ou hotel: " + hotelOrCityName);
        System.out.println("  Número de quartos: " + rooms);
        System.out.println("  Data de partida: " + departingDate);
        System.out.println("  Data de retorno: " + returningDate);
        System.out.println("  Número de pessoas: " + people);

        List<PackageEntity> similarPackages = dao.searchPackages(
                ticketMode, origin, hotelOrCityName, rooms,
                departingDate, returningDate, people);

        System.out.println("Encontrados: " + similarPackages.size());

        //Converte pra representação do mundo exterior
        List<PackageData> packages = new ArrayList<>();
        for(PackageEntity packageEntity : similarPackages) {
            packages.add(new PackageData(packageEntity));
        }
        return packages;
    }

    @Override
    public void buyTickets(int id) throws RemoteException {
        TicketEntity ticketBeingBought = dao.findTickets(id);
        if(ticketBeingBought == null) {
            throw new RemoteException("A passagem não está disponível!");
        }
        ticketBeingBought.buy(dao);
    }

    @Override
    public void buyAccommodations(int id) throws RemoteException {
        AccommodationEntity vacancyBeingBought = dao.findAccommodations(id);
        if(vacancyBeingBought == null) {
            throw new RemoteException("A vaga não está mais disponível");
        }
        vacancyBeingBought.buy(dao);
    }

    @Override
    public void buyPackages(int id) throws RemoteException {
        PackageEntity packageBeingBought = dao.findPackage(id);
        if(packageBeingBought == null) {
            throw new RemoteException("O pacote não está mais disponível");
        }
        packageBeingBought.buy(dao);
    }

    @Override
    public void registerInterest(List<EventType> events, String destination, float maxPrice,
                                 ClientInterface clientReference) throws RemoteException {
        InterestData interest = new InterestData(events, destination, maxPrice);

        EventHandler.registerInterest(interest, clientReference);
        System.out.println("Interesse registrado: \n" + interest.getDataToString());
    }

    @Override
    public List<InterestData> getInterests(ClientInterface clientRef) throws RemoteException {
        System.out.println("Consulta de interesses");

        List<InterestData> clientInterests = EventHandler.getInterestsByClient(clientRef);
        System.out.println("Encontrados: " + clientInterests.size());

        return clientInterests;
    }

    @Override
    public void removeInterest(List<EventType> events, String destination, float maxPrice,
                               ClientInterface clientReference) throws RemoteException {
        InterestData interest = new InterestData(events, destination, maxPrice);
        EventHandler.removeInterest(interest, clientReference);
    }
}
