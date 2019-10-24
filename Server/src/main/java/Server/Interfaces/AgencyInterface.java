package Server.Interfaces;

import Models.AccommodationData;
import Models.TicketData;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface que representa as operações executadas pela Agência implementada pelo servidor.
 */
public interface AgencyInterface extends Remote {
    /**
     * Cadastra nova oferta de passagem, inserindo os dados no banco de dados.
     * Invoca o método createTickets da interface DAO implementada por DAOJPAImplementation
     * para armazenar os dados da passagem.
     * @param newTicket nova oferta de passagem
     * @throws RemoteException avisa problemas relativos a criação do objetos remotos
     */
    public void announceTickets(TicketData newTicket) throws RemoteException;

    /**
     * Cadastra nova vaga de hospedagem, inserindo os dados no banco de dados.
     * Invoca o método createAccommodations da interface DAO implementada por DAOJPAImplementation
     * para armazenar os dados da hospedagem.
     * @param newAccommodation nova vaga de hospedagem
     * @throws RemoteException avisa problemas relativos a criação de objetos remotos
     */
    public void announceAccommodations(AccommodationData newAccommodation) throws RemoteException;
}
