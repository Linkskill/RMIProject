package Server;

import Models.InterestData;
import Server.Interfaces.ClientInterface;
import Server.Entities.TicketEntity;
import Server.Entities.AccommodationEntity;
import Server.Entities.PackageEntity;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe responsável por gerenciar os métodos que auxiliam na notificação dos clientes, no registro e
 * na remoção dos interesses dos clientes.
 */
public class EventHandler {
    private static Map<InterestData, List<ClientInterface>> clientsInterestsMap;

    /**
     * Método que inicializa o hash que mapeia os interesses dos clientes (InterestData) como chave (keySet)
     * e a referência de cada cliente (ClientInterface) como valor (values).
     */
    public static void initialize() {
        clientsInterestsMap = new HashMap<>();
    }

    /**
     * Registra os interesses dos clientes ao inserir os dados no hash clientInterestsMap.
     * @param interest interesse do cliente (do tipo InterestData)
     * @param clientReference referência do cliente (do tipo ClientInterface)
     */
    public static void registerInterest(InterestData interest, ClientInterface clientReference) {
        List<ClientInterface> clientsInterested = clientsInterestsMap.getOrDefault(interest, new ArrayList<>());
        clientsInterested.add(clientReference);
        clientsInterestsMap.put(interest, clientsInterested);
    }

    /**
     * Notifica os clientes caso haja uma nova passagem que seja do interesse do cliente.
     * Usa o método isSatisfiedBy para fazer a comparação e o método sendNotification para o envio
     * da notificação.
     * @param ticket nova passagem (do tipo TicketEntity)
     */
    public static void notifyClients(TicketEntity ticket) {
        String message = "Nova passagem disponível para " + ticket.getDestination() + " por " + ticket.getPrice();

        for(InterestData interest : clientsInterestsMap.keySet()) {
            if(interest.isSatisfiedBy(ticket)) {
                List<ClientInterface> clientsToNotify = clientsInterestsMap.get(interest);
                sendNotification(clientsToNotify, message);
            }
        }
    }

    /**
     * Notifica os clientes caso haja uma nova vaga de hospedagem que seja do interesse do cliente.
     * Usa o método isSatisfiedBy para fazer a comparação e o método sendNotification para o envio
     * da notificação.
     * @param accommodation nova vaga de hospedagem (do tipo AccommodationEntity)
     */
    public static void notifyClients(AccommodationEntity accommodation) {
        String message = "Nova vaga disponível para " + accommodation.getLocation() + " por " + accommodation.getPrice();

        for(InterestData interest : clientsInterestsMap.keySet()) {
            if(interest.isSatisfiedBy(accommodation)) {
                List<ClientInterface> clientsToNotify = clientsInterestsMap.get(interest);
                sendNotification(clientsToNotify, message);
            }
        }
    }

    /**
     * Notifica os clientes caso haja um novo pacote que seja do interesse do cliente.
     * Usa o método isSatisfiedBy para fazer a comparação e o método sendNotification para o envio
     * da notificação.
     * @param packageEntity nova passagem (do tipo PackageEntity)
     */
    public static void notifyClients(PackageEntity packageEntity) {
        String message = "Novo pacote disponível para " + packageEntity.getAccommodationEntity().getLocation() +
                " por " + packageEntity.getPrice();

        for(InterestData interest : clientsInterestsMap.keySet()) {
            if(interest.isSatisfiedBy(packageEntity)) {
                List<ClientInterface> clientsToNotify = clientsInterestsMap.get(interest);
                sendNotification(clientsToNotify, message);
            }
        }
    }

    /**
     * Envia uma notificação (message) a cada cliente fazendo uso da referência do cliente
     * para realizar a chamada do método echo.
     * Ver mais em {@link Server.Interfaces.ClientInterface#echo(String argument)}
     * @param clientsToNotify lista de clientes a serem notificados (do tipo ClientInterface)
     * @param message mensagem de notificação (do tipo String)
     */
    private static void sendNotification(List<ClientInterface> clientsToNotify, String message) {
        for(ClientInterface clientRef : clientsToNotify) {
            try {
                clientRef.echo(message);
            } catch (RemoteException e) {
                System.out.println("Falha ao notificar cliente");
            }
        }
    }

    /**
     * Retorna uma lista de interesse dos clientes
     * @param clientReference referência dos clientes (do tipo ClientInterface)
     * @return clientInterests lista do tipo InterestData
     */
    public static List<InterestData> getInterestsByClient(ClientInterface clientReference) {
        List<InterestData> clientInterests = new ArrayList<>();
        for(InterestData interest : clientsInterestsMap.keySet()) {
            if(clientsInterestsMap.get(interest).contains(clientReference)) {
                clientInterests.add(interest);
            }
        }
        return clientInterests;
    }

    /**
     * Remove o interesse desejado pelo cliente. Faz isso se o interesse e o cliente existir no hash.
     * Se true, remove o interesse e o cliente em um determinado campo do hash. Caso contrário, notifica
     * mensagem de erro ao cliente.
     * @param interest interesse do cliente (do tipo Interest Data)
     * @param clientReference referência do cliente (do tipo ClientInterface)
     */
    public static void removeInterest(InterestData interest, ClientInterface clientReference) {
        try {
            if (clientsInterestsMap.containsKey(interest)) {
                List<ClientInterface> clientsInterestsList = clientsInterestsMap.get(interest);
                if (clientsInterestsList.contains(clientReference)) {
                    clientsInterestsList.remove(clientReference);
                    clientsInterestsMap.replace(interest, clientsInterestsList);
                    System.out.println("Interesse removido: \n" + interest.getDataToString());
                    clientReference.echo("Você não receberá mais notificações desse evento.");
                } else {
                    clientReference.echo("Você não possui interesses." + "\n"
                            + "É necessário estar registrado para retirar um interesse.");
                }
            } else {
                clientReference.echo("Interesse não encontrado.");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
