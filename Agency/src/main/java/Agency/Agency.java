package Agency;

import Server.Interfaces.AgencyInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Classe principal da Agência.
 * Responsável por inicializar o serviço de nomes do servidor e de chamar o menu.
 */
public class Agency {
    private static AgencyInterface serverReference;
    public static void main(String[] args) {
        int port = 6789;
        try {
            Registry serverNameRegistry = LocateRegistry.getRegistry(null, port);
            serverReference = (AgencyInterface) serverNameRegistry.lookup("AgencyServer");

            Menu menu = new Menu();
            menu.choice();
        } catch (RemoteException | NotBoundException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Método que retorna a referência da interface da Agência.
     * @return serverReference a referência da Agência
     */
    public static AgencyInterface getServerReference() { return serverReference; }
}
