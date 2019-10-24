package Models;

import Server.Entities.PackageEntity;
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
     * Construtor da classe PackageData, ajusta os atributos por meio dos seguintes parâmetros:
     * @param packageEntity entidade que representa os dados do pacote
     */
    public PackageData(PackageEntity packageEntity) {
        this.id = packageEntity.getId();
        this.ticketData = new TicketData(packageEntity.getTicketEntity());
        this.accommodationData = new AccommodationData(packageEntity.getAccommodationEntity());
        this.price = packageEntity.getPrice();
    }

    /**
     * Concede uma String contendo todos os dados do pacote.
     * @return str contendo os dados do pacote
     */
    public String getDataToString() {
        String str = "";

        str += "Oferta de pacote #" + id + ": \n";
        str += "  Tipo da passagem: " + ticketData.getTicketMode() + "\n";
        str += "  Origem: " + ticketData.getOrigin() + "\n";
        str += "  Destino: " + accommodationData.getLocation() + "\n";
        str += "  Hotel: " + accommodationData.getHotel() + "\n";
        str += "  Número de quartos: " + accommodationData.getPeople() + "\n";
        str += "  Data de partida: " + DateHandler.dateToString(accommodationData.getEntranceDate()) + "\n";
        str += "  Data de retorno: " + DateHandler.dateToString(accommodationData.getExitDate()) + "\n";
        str += "  Número de quartos: " + accommodationData.getRooms() + "\n";
        str += "  Para: " + accommodationData.getPeople() + " pessoas\n";
        str += "  Preço: " + price + "\n";

        return str;
    }

    /**
     * Retorna o identificador do pacote.
     * @return id do tipo inteiro
     */
    public int getId() { return id; }

    /**
     * Retorna os dados relativos a passagem.
     * @return ticketData do tipo TicketData
     */
    public TicketData getTickets() { return ticketData; }

    /**
     * Retorna os dados relativos a hospedagem.
     * @return accommodationData do tipo AccommodationData
     */
    public AccommodationData getAccommodations() { return accommodationData; }

    /**
     * Retorna o preço do pacote.
     * @return price do tipo float
     */
    public float getPrice() { return price; }
}
