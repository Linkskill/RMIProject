package Server.Entities;

import Models.PackageData;
import Server.DataAccessObject.DAO;

import javax.persistence.*;

/**
 * Representação da tabela "package" do banco de dados.
 */
@Entity
@Table(name = "package")
public class PackageEntity {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne @JoinColumn(name = "ticket_id")
    private TicketEntity ticketEntity;

    @ManyToOne @JoinColumn(name = "accommodation_id")
    private AccommodationEntity accommodationEntity;

    private float price;

    public PackageEntity() {
        //Necessário para o JPA
    }

    /**
     * Construtor da classe PackageEntity, ajusta os atributos de acordo com as entidades
     * enviadas por parâmetro.
     * @param ticket do tipo TicketEntity
     * @param accommodation do tipo AccommodationEntity
     */
    public PackageEntity(TicketEntity ticket, AccommodationEntity accommodation) {
        this.ticketEntity = ticket;
        this.accommodationEntity = accommodation;
        this.price = (ticket.getPrice() + accommodationEntity.getPrice()) * (float)0.8;
    }

    /**
     * Construtor da classe PackageEntity, ajustada por meio do modelo enviado por parâmetro.
     * @param packageData do tipo PackageData
     */
    public PackageEntity(PackageData packageData) {
        this.id = packageData.getId();
        this.ticketEntity = new TicketEntity(packageData.getTickets());
        this.accommodationEntity = new AccommodationEntity(packageData.getAccommodations());
        this.price = packageData.getPrice();
    }

    /**
     * Remove o pacote "comprado" pelo cliente do banco de dados por meio
     * do DAO que recebe por parâmetro. É sicronizado pelo fato de que pode ocorrer erros
     * caso dois clientes comprem o mesmo pacote ao mesmo tempo.
     * @param dao do tipo DAO
     */
    public synchronized void buy(DAO dao) {
        dao.removePackage(id);
    }

    /**
     * Retorna o ID da entidade do pacote.
     * @return id do tipo int
     */
    public int getId() { return id; }

    /**
     * Retorna o preço do pacote.
     * @return price do tipo int
     */
    public float getPrice() { return price; }

    /**
     * Retorna a entidade das passagens.
     * @return ticketEntity do tipo TicketEntity
     */
    public TicketEntity getTicketEntity() { return ticketEntity; }

    /**
     * Retorna a entidade das vagas de hospedagem.
     * @return accommodationEntity do tipo AccommodationEntity
     */
    public AccommodationEntity getAccommodationEntity() { return accommodationEntity; }
}
