package Client;

import Server.Interfaces.ClientInterface;
import Server.Interfaces.ServerInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

/**
 * Classe principal do Cliente.
 * Responsável por inicializar o serviço de nomes do servidor e de chamar o menu.
 */
public class Client {
    private static ServerInterface serverReference;
    private static ClientInterface clientReference;

    public static void main(String[] args) {
        int port = 6789;
        try {
            Registry serverNameRegistry = LocateRegistry.getRegistry(null, port);
            serverReference = (ServerInterface) serverNameRegistry.lookup("ClientServer");
            clientReference = new ClientServant();

            Menu menu = new Menu();
            menu.choice();
        } catch (RemoteException | NotBoundException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @return serverReference: a interface do servidor
     */
    public static ServerInterface getServerReference() { return serverReference; }

    /**
     * Retorna a referência do Cliente.
     * @return clientReference: a interface do cliente
     */
    public static ClientInterface getClientReference() { return clientReference; }
}