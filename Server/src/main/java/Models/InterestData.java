package Models;

import Server.Entities.TicketEntity;
import Server.Entities.AccommodationEntity;
import Server.Entities.PackageEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Classe serializável responsável por armazenar os dados dos interesses do cliente.
 */
public class InterestData implements Serializable {
    public static final long serialVersionUID = 0L;

    private List<EventType> eventsInterested;
    private String destination;
    private float maxPrice;

    /**
     * Construtor da classe InterestData, ajusta os atributos por meio dos seguintes parâmetros:
     * @param eventTypes lista do tipo de eventos escolhidos pelo cliente
     * @param destination local do destino
     * @param maxPrice preço máximo aceitado pelo cliente
     */
    public InterestData(List<EventType> eventTypes, String destination, float maxPrice) {
        this.eventsInterested = eventTypes;
        this.destination = destination;
        this.maxPrice = maxPrice;
    }

    /**
     * Retorna uma String contendo os dados dos interesses do cliente.
     * @return str do tipo String
     */
    public String getDataToString() {
        String str = "";

        str += "  Events: " + eventsInterested.toString() + "\n";
        str += "  Destination: " + destination + "\n";
        str += "  MaxPrice: " + maxPrice;

        return str;
    }

    /**
     * Retorna uma lista contendo todos os eventos (passagens, hospedagens ou pacotes) do interesse do cliente.
     * @return eventsInterested lista do tipo EventType
     */
    public List<EventType> getEventsInterested() { return eventsInterested; }

    /**
     * Retorna o local de destino.
     * @return destination do tipo String
     */
    public String getDestination() { return destination; }

    /**
     * Retorna o preço máximo aceitado pelo cliente.
     * @return maxPrice do tipo float
     */
    public float getMaxPrice() { return maxPrice; }

    /**
     * Sobrescreve o método equals padrão da biblioteca do java.
     * Compara os objetos que representam o interesse do cliente comparando os eventos,
     * o local de destino e o preço.
     * @param obj objeto do tipo Object contendo os interesses do cliente
     * @return true ou false
     */
    @Override
    public boolean equals(Object obj) {
        InterestData interest = (InterestData)obj;
        if(eventsInterested.equals(interest.getEventsInterested())
                && destination.equals(interest.getDestination())
                && Math.abs(maxPrice - interest.getMaxPrice()) < 1) {
            return true;
        }
        return false;
    }

    /**
     * Sobrescreve o método hashCode padrão do java para o método containsKey (utilizado no método
     * removeInterests da classe EventHandler) poder funcionar.
     * Organiza os elementos do hash da classe EventHandler que mapeia os interesses de cada cliente.
     * @return um inteiro
     */
    @Override
    public int hashCode() {
        return Objects.hash(eventsInterested, destination, maxPrice);
    }

    /**
     * Verifica se a passagem enviada é do interesse do cliente.
     * @param newTicket a nova passagem (ticket)
     * @return true se for o interesse do cliente, false caso contrário
     */
    public boolean isSatisfiedBy(TicketEntity newTicket) {
        boolean registeredToNewTickets = eventsInterested.contains(EventType.NEW_TICKET);
        boolean rightDestiny = destination.equals(newTicket.getDestination());
        boolean priceIsLowerThanDesired = newTicket.getPrice() <= maxPrice;

        return registeredToNewTickets && rightDestiny && priceIsLowerThanDesired;
    }

    /**
     * Verifica se a vaga de hospedagem enviada é do interesse do cliente.
     * @param newAccommodation a nova vaga de hospedagem
     * @return true se for o interesse do cliente, false caso contrário
     */
    public boolean isSatisfiedBy(AccommodationEntity newAccommodation) {
        boolean registeredToNewVacancies = eventsInterested.contains(EventType.NEW_ACCOMMODATION);
        boolean rightLocation = destination.equals(newAccommodation.getLocation());
        boolean priceIsLowerThanDesired = newAccommodation.getPrice() <= maxPrice;

        return registeredToNewVacancies && rightLocation && priceIsLowerThanDesired;
    }

    /**
     * Verifica se o pacote enviado é do interesse do cliente.
     * @param newPackage o novo pacote
     * @return true se for o interesse do cliente, false caso contrário
     */
    public boolean isSatisfiedBy(PackageEntity newPackage) {
        boolean registeredToNewPackages = eventsInterested.contains(EventType.NEW_PACKAGE);
        boolean rightDestiny = destination.equals(newPackage.getAccommodationEntity().getLocation());
        boolean priceIsLowerThanDesired = newPackage.getPrice() <= maxPrice;

        return registeredToNewPackages && rightDestiny && priceIsLowerThanDesired;
    }
}
