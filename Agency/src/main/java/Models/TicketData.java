package Models;

import Utilities.DateHandler;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe serializável que armazena os dados das passagens.
 */
public class TicketData implements Serializable {
    public static final long serialVersionUID = 0L;

	private String ticketMode;
	private String origin;
	private String destination;
	private LocalDate departingDate;
	private LocalDate returningDate;
	private int people;
	private float price;

    /**
     * Construtor da classe TicketData que constrói seus atributos de acordo com
     * os seguintes parâmetros:
     * @param ticketMode tipo da passagem \"somente ida\" ou \"ida e volta\"
     * @param origin local de partida
     * @param destination local de destino
     * @param departingDate data de partida
     * @param returningDate data de retorno
     * @param people número de pessoas
     * @param price preço da passagem
     */
    public TicketData(String ticketMode, String origin, String destination,
					  LocalDate departingDate, LocalDate returningDate,
					  int people, float price) {
        this.ticketMode = ticketMode;
        this.origin = origin;
        this.destination = destination;
        this.departingDate = departingDate;
        this.returningDate = returningDate;
        this.people = people;
        this.price = price;
    }

    /**
     * Concede uma String com todos os dados da passagem aérea.
     * @return str contendo os dados da passagem
     */
	public String getDataToString() {
		String str = "";

		str += "Passagens aéreas: \n";
		str += "  Tipo da passagem: " + ticketMode + "\n";
		str += "  Origem: " + origin + "\n";
		str += "  Destino: " + destination + "\n";
		str += "  Data de partida: " + DateHandler.dateToString(departingDate) + "\n";
		str += "  Data de retorno: " + DateHandler.dateToString(returningDate) + "\n";
		str += "  Para: " + people + " pessoas\n";
		str += "  Preço: " + price + "\n";

		return str;
	}
}