package Server.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface que representa as operações executadas pelo Cliente implementada pela classe ClientServant.
 */
public interface ClientInterface extends Remote {
    /**
     * Imprime na tela do cliente os dados passados por parâmetro.
     * @param argument argumentos do tipo String
     * @throws RemoteException avisa casos de erro na criação de objetos Remotos
     */
    public void echo (String argument) throws RemoteException;
}

