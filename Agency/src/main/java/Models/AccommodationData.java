package Models;

import Utilities.DateHandler;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe serializável que armazena os dados das hospedagens.
 */
public class AccommodationData implements Serializable {
	public static final long serialVersionUID = 0L;

	private String location;
	private String hotel;
	private LocalDate entranceDate;
	private LocalDate exitDate;
	private int rooms;
	private int people;
	private float price;

    /**
     * Construtor da classe AccommodationData que constrói seus atributos de acordo com
     * os seguintes parâmetros:
     * @param location o nome do local onde será ofertado a vaga de hospedagem
     * @param hotel o nome do hotel que ofertará a vaga de hospedagem
     * @param entranceDate data de entrada da hospedagem
     * @param exitDate data de finalização da hospedagem
     * @param rooms número de quartos que serão utilizados
     * @param people número de pessoas que querem a vaga
     * @param price preço da vaga de hospedagem
     */
	public AccommodationData(String location, String hotel, LocalDate entranceDate,
							 LocalDate exitDate, int rooms, int people, float price) {
        this.location = location;
        this.hotel = hotel;
        this.entranceDate = entranceDate;
        this.exitDate = exitDate;
        this.rooms = rooms;
        this.people = people;
        this.price = price;
    }

    /**
     * Concede uma String contendo todos os dados da vaga de hospedagem.
     * @return str contendo os dados da hospedagem
     */
	public String getDataToString() {
		String str = "";

        str += "Vagas de hospedagem: \n";
		str += "  Local: " + location + "\n";
		str += "  Hotel: " + hotel + "\n";
		str += "  Data de entrada: " + DateHandler.dateToString(entranceDate) + "\n";
		str += "  Data de saida: " + DateHandler.dateToString(exitDate) + "\n";
		str += "  Número de quartos: " + rooms + "\n";
		str += "  Para: " + people + " pessoas\n";
		str += "  Preço: " + price + "\n";

		return str;
	}
}