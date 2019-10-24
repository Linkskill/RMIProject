package Client;

import Server.Interfaces.ClientInterface;
import Server.Interfaces.ServerInterface;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe responsável por implementar os métodos da Interface do cliente.
 */
public class ClientServant extends UnicastRemoteObject implements ClientInterface {
    /**
     * Construtor da Classe implementadora da Interface do cliente.
     * @throws RemoteException avisa exceções na criação de objetos remotos
     */
    public ClientServant() throws RemoteException {}

    /**
     * Mostra avisos do funcionamento do programa
     * @param argument é uma String que contém um aviso sobre casos em que o programa não pode executar um método
     */
    public void echo (String argument) {
        System.out.println(argument);
    }
}
