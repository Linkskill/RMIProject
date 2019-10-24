package Models;

import Utilities.DateHandler;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe serializável que armazena os dados das hospedagens.
 */
public class AccommodationData implements Serializable {
	public static final long serialVersionUID = 0L;

	private int id;
	private String location;
	private String hotel;
	private LocalDate entranceDate;
	private LocalDate exitDate;
	private int rooms;
	private int people;
	private float price;

    /**
     * Concede uma String contendo todos os dados da vaga de hospedagem.
     * @return str contendo os dados da hospedagem
     */
	public String getDataToString() {
		String str = "";

		str += "-- Oferta de vaga de hotel #" + id + " --\n";
		str += "  Local: " + location + "\n";
		str += "  Hotel: " + hotel + "\n";
		str += "  Data de entrada: " + DateHandler.dateToString(entranceDate) + "\n";
		str += "  Data de saida: " + DateHandler.dateToString(exitDate) + "\n";
		str += "  Número de quartos: " + rooms + "\n";
		str += "  Para: " + people + " pessoas\n";
		str += "  Preço: " + price + "\n";

		return str;
	}

    /**
     * Retorna uma String com o nome do hotel.
     * @return hotel do tipo String
     */
	public String getHotel() { return hotel; }

    /**
     * Retorna o número de quartos que o hotel possui.
     * @return rooms do tipo inteiro
     */
	public int getRooms() { return rooms; }

    /**
     * Retorna quantas pessoas podem ser hospedadas.
     * @return people do tipo inteiro
     */
	public int getPeople() { return people; }
}