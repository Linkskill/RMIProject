package Models;

import Utilities.DateHandler;

import java.io.Serializable;

/**
 * Classe serializável responsável por armazenar os dados dos pacotes.
 */
public class PackageData implements Serializable {
    public static final long serialVersionUID = 0L;

    private int id;
    private TicketData ticketData;
    private AccommodationData accommodationData;
    private float price;

    /**
     * Concede uma String com todos os dados do pacote.
     * @return str variável String com os dados do pacote
     */
    public String getDataToString() {
        String str = "";

        str += "Oferta de pacote " + id + ": \n";
        str += "  Tipo da passagem: " + ticketData.getTicketMode() + "\n";
        str += "  Origem: " + ticketData.getOrigin() + "\n";
        str += "  Destino: " + ticketData.getDestination() + "\n";
        str += "  Hotel: " + accommodationData.getHotel() + "\n";
        str += "  Número de quartos: " + accommodationData.getRooms() + "\n";
        str += "  Data de partida: " + DateHandler.dateToString(ticketData.getDepartingDate()) + "\n";
        str += "  Data de retorno: " + DateHandler.dateToString(ticketData.getReturningDate()) + "\n";
        str += "  Para: " + accommodationData.getPeople() + " pessoas\n";
        str += "  Preço: " + price + "\n";

        return str;
    }
}
