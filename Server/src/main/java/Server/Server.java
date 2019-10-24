package Server;

import Server.DataAccessObject.DAO;
import Server.DataAccessObject.DAOJPAImplementation;
import Server.DataAccessObject.JPASessionFactory;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe principal do Servidor.
 * Responsável por inicializar o DAO (Data Access Object), JPASessionFactory, EventHandler
 * e o serviço de nomes do servidor, além de instanciar os objetos das classes que implementam as
 * Interfaces.
 */
public class Server {
    public static void main(String[] args) {
        int port = 6789;
        DAO dao;
        dao = new DAOJPAImplementation();
        JPASessionFactory.initialize();
        EventHandler.initialize();
        try {
            Registry serverNameRegistry = LocateRegistry.createRegistry(port);
            ClientServerServant clientServerServant = new ClientServerServant(dao);
            serverNameRegistry.bind("ClientServer", clientServerServant);
            AgencyServerServant agencyServerServant = new AgencyServerServant(dao);
            serverNameRegistry.bind("AgencyServer", agencyServerServant);
        } catch (RemoteException | AlreadyBoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}