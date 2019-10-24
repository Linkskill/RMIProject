package Server;

import Models.AccommodationData;
import Models.TicketData;
import Server.Entities.AccommodationEntity;
import Server.Entities.TicketEntity;
import Server.Interfaces.AgencyInterface;
import Server.DataAccessObject.DAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Classe responsável por implementar os métodos da Interface da Agência (AgencyInterface).
 */
public class AgencyServerServant extends UnicastRemoteObject implements AgencyInterface {
    private DAO dao;

    /**
     * Construtor da classe AgencyServerServant, responsável por inicializar o objeto da interface DAO.
     * @param dao objeto da interface DAO (Data Access Object) implementada por DAOJPAImplementation
     * @throws RemoteException avisa casos de exceção na criação de objetos remotos
     */
    public AgencyServerServant(DAO dao) throws RemoteException{
        this.dao = dao;
    }

    @Override
    public void announceTickets(TicketData newTicket) throws RemoteException {
        TicketEntity ticket = new TicketEntity(newTicket);
        dao.createTickets(ticket);
        System.out.println("Passagem cadastrada!");
        System.out.println(newTicket.getDataToString());
    }

    @Override
    public void announceAccommodations(AccommodationData newAccommodation) throws RemoteException {
        AccommodationEntity accommodation = new AccommodationEntity(newAccommodation);
        dao.createAccommodations(accommodation);
        System.out.println("Vaga de hospedagem cadastrada!");
        System.out.println(newAccommodation.getDataToString());
    }
}
