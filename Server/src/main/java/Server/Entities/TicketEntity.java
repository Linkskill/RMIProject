package Server.Entities;

import Models.TicketData;
import Server.DataAccessObject.DAO;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * Representação da tabela "ticket" do banco de dados.
 */
@Entity
@Table(name = "ticket")
public class TicketEntity {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String ticketType;
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private int numPeople;
    private float price;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "ticketEntity", fetch = FetchType.EAGER)
    private Set<PackageEntity> packagesThatIncludes;

    public TicketEntity() {
        //Necessário para o JPA
    }

    /**
     * Construtor da classe TicketEntity, ajusta os atributos de acordo com o modelo
     * enviado por parâmetro.
     * @param ticketData do tipo TicketData
     */
    public TicketEntity(TicketData ticketData) {
        this.id = ticketData.getId();
        this.ticketType = ticketData.getTicketMode();
        this.origin = ticketData.getOrigin();
        this.destination = ticketData.getDestination();
        this.departureDate = ticketData.getDepartingDate();
        this.returnDate = ticketData.getReturningDate();
        this.numPeople = ticketData.getPeople();
        this.price = ticketData.getPrice();
    }

    /**
     * Remove a passagem "comprada" pelo cliente do banco de dados por meio
     * do DAO que recebe por parâmetro. É sicronizado pelo fato de que pode ocorrer erros
     * caso dois clientes comprem a mesma passagem ao mesmo tempo.
     * @param dao do tipo DAO
     */
    public synchronized void buy(DAO dao) { dao.removeTickets(id); }

    /**
     * Retorna o ID da entidade TicketEntity.
     * @return id do tipo int
     */
    public int getId() { return id; }

    /**
     * Retorna o modo das passagens.
     * @return ticketType do tipo String
     */
    public String getTicketType() { return ticketType; }

    /**
     * Retorna o local de origem.
     * @return origin do tipo String
     */
    public String getOrigin() { return origin; }

    /**
     * Retorna local de destino.
     * @return destination do tipo String
     */
    public String getDestination() { return destination; }

    /**
     * Retorna a data de partida.
     * @return departureDate do tipo LocalDate
     */
    public LocalDate getDepartureDate() { return departureDate; }

    /**
     * Retorna a data de retorno.
     * @return returnDate do tipo LocalDate
     */
    public LocalDate getReturnDate() { return returnDate; }

    /**
     * Retorna o número de pessoas.
     * @return numPeople do tipo int
     */
    public int getNumPeople() { return numPeople; }

    /**
     * Retorna o preço da passagem.
     * @return price do tipo float
     */
    public float getPrice() { return price; }
}