package Server.Entities;

import Models.AccommodationData;
import Server.DataAccessObject.DAO;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * Representação da tabela "accommodation" do banco de dados.
 */
@Entity
@Table(name = "accommodation")
public class AccommodationEntity {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String location;
    private String hotel;
    private LocalDate entranceDate;
    private LocalDate exitDate;
    private int numRooms;
    private int numPeople;
    private float price;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "accommodationEntity", fetch = FetchType.EAGER)
    private Set<PackageEntity> packagesThatIncludes;

    public AccommodationEntity() {
        //Necessário para o JPA
    }

    /**
     * Construtor da classe AccommodationEntity, ajusta os atributos de acordo com o modelo
     * enviado por parâmetro.
     * @param accommodation do tipo AccommodationData
     */
    public AccommodationEntity(AccommodationData accommodation) {
        this.id = accommodation.getId();
        this.location = accommodation.getLocation();
        this.hotel = accommodation.getHotel();
        this.entranceDate = accommodation.getEntranceDate();
        this.exitDate = accommodation.getExitDate();
        this.numRooms = accommodation.getRooms();
        this.numPeople = accommodation.getPeople();
        this.price = accommodation.getPrice();
    }

    /**
     * Remove a vaga de hospedagem "comprada" pelo cliente do banco de dados por meio
     * do DAO que recebe por parâmetro. É sicronizado pelo fato de que pode ocorrer erros
     * caso dois clientes comprem a mesma vaga ao mesmo tempo.
     * @param dao do tipo DAO
     */
    public synchronized void buy(DAO dao) { dao.removeAccommodations(id); }

    /**
     * Retorna o ID da entidade da hospedagem.
     * @return id do tipo int
     */
    public int getId() { return id; }

    /**
     * Retorna o nome do local.
     * @return location do tipo String
     */
    public String getLocation() { return location; }

    /**
     * Retorna o nome do hotel
     * @return hotel do tipo String
     */
    public String getHotel() { return hotel; }

    /**
     * Retorna a data de início do serviço de hospedagem.
     * @return entranceDate do tipo LocalDate
     */
    public LocalDate getEntranceDate() { return entranceDate; }

    /**
     * Retorna a data final do serviço de hospedagem.
     * @return exitDate do tipo LocalDate
     */
    public LocalDate getExitDate() { return exitDate; }

    /**
     * Retorna o número de quartos.
     * @return numRooms do tipo int
     */
    public int getNumRooms() { return numRooms; }

    /**
     * Retorna o número de pessoas a serem hospedadas.
     * @return numPeople do tipo int
     */
    public int getNumPeople() { return numPeople; }

    /**
     * Retorna o preço da hospedagem.
     * @return price do tipo float
     */
    public float getPrice() { return price; }
}
