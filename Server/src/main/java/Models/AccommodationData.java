package Models;

import Server.Entities.AccommodationEntity;
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
     * Construtor da classe AccommodationData que constrói seus atributos de acordo com
     * os seguintes parâmetros:
     * @param accommodation entidade que representa os dados da hospedagem
     */
	public AccommodationData(AccommodationEntity accommodation) {
        this.id = accommodation.getId();
        this.location = accommodation.getLocation();
        this.hotel = accommodation.getHotel();
        this.entranceDate = accommodation.getEntranceDate();
        this.exitDate = accommodation.getExitDate();
        this.rooms = accommodation.getNumRooms();
        this.people = accommodation.getNumPeople();
        this.price = accommodation.getPrice();
    }

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
     * Retorna o identificador da vaga de hospedagem.
     * @return id do tipo inteiro
     */
	public int getId() { return id; }

    /**
     * Retorna o local da vaga de hospedagem.
     * @return local do tipo String
     */
	public String getLocation() { return location; }

    /**
     * Retorna o nome do hotel que disponibiliza a vaga de hospedagem.
     * @return hotel do tipo String
     */
	public String getHotel() { return hotel; }

    /**
     * Retorna a data de início da hospedagem.
     * @return entranceDate do tipo LocalDate
     */
	public LocalDate getEntranceDate() { return entranceDate; }

    /**
     * Retorna a data de finalização do serviço de hospedagem.
     * @return exitDate do tipo LocalDate
     */
	public LocalDate getExitDate() { return exitDate; }

    /**
     * Retorna o número de quartos que a vaga diponibiliza.
     * @return rooms do tipo inteiro
     */
	public int getRooms() { return rooms; }

    /**
     * Retorna o número de pessoas para adquirir a vaga de hospedagem.
     * @return people do tipo inteiro
     */
	public int getPeople() { return people; }

    /**
     * Retorna o valor do preço da vaga de hospedagem.
     * @return price do tipo float
     */
	public float getPrice() { return price; }
}