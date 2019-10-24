package Models;

import Server.Entities.TicketEntity;
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
     * Construtor da classe TicketData que constrói seus atributos de acordo com
     * os seguintes parâmetros:
     * @param ticket entidade que representa os dados da passagem
     */
    public TicketData(TicketEntity ticket) {
        this.id = ticket.getId();
        this.ticketMode = ticket.getTicketType();
        this.origin = ticket.getOrigin();
        this.destination = ticket.getDestination();
        this.departingDate = ticket.getDepartureDate();
        this.returningDate = ticket.getReturnDate();
        this.people = ticket.getNumPeople();
        this.price = ticket.getPrice();
    }

    /**
     * Concede uma String contendo todos os dados da passagem.
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
     * Retorna o identificador da passagem.
     * @return id do tipo inteiro
     */
	public int getId() { return id; }

    /**
     * Retorna o modo da passagem (somente ida ou ida e volta).
     * @return ticketMode do tipo String
     */
	public String getTicketMode() { return ticketMode; }

    /**
     * Retorna o local de partida.
     * @return origin do tipo String
     */
	public String getOrigin() { return origin; }

    /**
     * Retorna o local de destino.
     * @return destination do tipo String
     */
	public String getDestination() { return destination; }

    /**
     * Retorna a data de partida.
     * @return departingDate do tipo LocalDate
     */
	public LocalDate getDepartingDate() { return departingDate; }

    /**
     * Retorna a data de retorno.
     * @return returningDate do tipo LocalDate
     */
	public LocalDate getReturningDate() { return returningDate; }

    /**
     * Retorna o número de pessoas.
     * @return people do tipo int
     */
	public int getPeople() { return people; }

    /**
     * Retorna o preço da passagem.
     * @return price do tipo float
     */
	public float getPrice() { return price; }
}