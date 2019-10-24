package Models;

import Utilities.DateHandler;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe serializável que armazena os dados das passagens.
 */
public class TicketData implements Serializable {
	public static final long serialVersionUID = 0L;

    private int id;
	private String ticketMode;
	private String origin;
	private String destination;
	private LocalDate departingDate;
	private LocalDate returningDate;
	private int people;
	private float price;

    /**
     * Concede uma String contendo todos os dados da oferta de passagem.
     * @return str contendo os dados da passagem
     */
	public String getDataToString() {
		String str = "";

		str += "-- Oferta de passagem aérea #" + id + " --\n";
		str += "  Tipo da passagem: " + ticketMode + "\n";
		str += "  Origem: " + origin + "\n";
		str += "  Destino: " + destination + "\n";
		str += "  Data de partida: " + DateHandler.dateToString(departingDate) + "\n";
		str += "  Data de retorno: " + DateHandler.dateToString(returningDate) + "\n";
		str += "  Para: " + people + " pessoas\n";
		str += "  Preço: " + price + "\n";

		return str;
	}

    /**
     * Retorna uma String contendo o modo da passagem \"somente ida\" ou \"ida e volta\".
     * @return ticketMode do tipo String
     */
	public String getTicketMode() { return ticketMode; }

    /**
     * Retorna o local de partida.
     * @return origin do tipo String
     */
	public String getOrigin() { return origin; }

    /**
     * Retorna o destino final da viagem.
     * @return destination do tipo String
     */
	public String getDestination() { return destination; }

    /**
     * Retorna a data de partida.
     * @return departingDate data do tipo LocalDate
     */
	public LocalDate getDepartingDate() { return departingDate; }

    /**
     * Retorna a data de retorno.
     * @return returningDate data do tipo LocalDate
     */
	public LocalDate getReturningDate() { return returningDate; }
}