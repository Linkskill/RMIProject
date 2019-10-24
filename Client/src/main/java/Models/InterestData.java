package Models;

import java.io.Serializable;
import java.util.List;

/**
 * Classe serializável responsável por armazenar os dados dos interesses do cliente.
 */
public class InterestData implements Serializable {
    public static final long serialVersionUID = 0L;

    private List<EventType> eventsInterested;
    private String destination;
    private float maxPrice;

    /**
     * Concede uma String contendo todos os dados dos interesses.
     * @return str contendo os dados dos interesses
     */
    public String getDataToString() {
        String str = "";
        str += "  Eventos: " + eventsInterested.toString() + "\n";
        str += "  Destino: " + destination + "\n";
        str += "  Preço máximo a pagar: " + maxPrice;
        return str;
    }
}
