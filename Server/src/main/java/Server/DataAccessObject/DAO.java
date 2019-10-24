package Server.DataAccessObject;

import Server.Entities.AccommodationEntity;
import Server.Entities.PackageEntity;
import Server.Entities.TicketEntity;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface DAO (Data Access Object) que tem por objetivo representar as operações que acessam o banco de dados.
 */
public interface DAO {
    /**
     * A cada criação de novas passagens, o método vai buscar dados a fim de criar pacotes
     * com as vagas de hotel que possuem as mesmas datas, mesmo destino e número de pessoas.
     * Posteriormente, notificará os clientes sobre os novos pacotes.
     * @param newTicket nova passagem (do tipo TicketEntity)
     */
    public void createTickets(TicketEntity newTicket);

    /**
     * A cada criação de novas vagas de hospedagem, o método vai buscar dados a fim de criar pacotes
     * com as passagens que possuem as mesmas datas, mesmo destino e número de pessoas.
     * Posteriormente, notificará os clientes sobre os novos pacotes.
     * @param newAccommodation nova vaga de hospedagem (do tipo AccommodationEntity)
     */
    public void createAccommodations(AccommodationEntity newAccommodation);

    /**
     * Realiza uma busca a fim de encontrar as passagens desejadas pelos clientes.
     * @param ticketMode modo da passagem "somente ida" ou "ida e volta"
     * @param origin local de origem
     * @param destination local de destino
     * @param departingDate data de partida
     * @param returningDate data de retorno
     * @param people número de pessoas
     * @return results uma lista (do tipo TicketEntity) com todas as passagens desejadas pelo cliente
     */
    public List<TicketEntity> searchTickets(String ticketMode, String origin, String destination,
                                            LocalDate departingDate, LocalDate returningDate, int people);

    /**
     * Realiza uma busca a fim de encontrar as vagas de hospedagem desejadas pelos clientes.
     * @param hotelOrCityName nome da cidade ou do hotel
     * @param entranceDate data de início da estadia
     * @param exitDate data do final da estadia
     * @param rooms número de quartos
     * @param people número de pessoas
     * @return results lista (do tipo AccommodationEntity) com todas as vagas de hospedagem desejadas pelo cliente
     */
    public List<AccommodationEntity> searchAccommodations(String hotelOrCityName, LocalDate entranceDate,
                                                   LocalDate exitDate, int rooms, int people);

    /**
     * Realiza uma busca a fim de encontrar os pacotes desejados pelos clientes.
     * @param ticketMode modo da passagem "somente ida" ou "ida e volta"
     * @param origin local de origem
     * @param hotelOrCityName nome da cidade ou do hotel
     * @param rooms número de quartos
     * @param departingDate data de partida
     * @param returningDate data de retorno
     * @param people número de pessoas
     * @return results lista (do tipo PackageEntity) com todos os pacotes desejados pelo cliente
     */
    public List<PackageEntity> searchPackages(String ticketMode, String origin, String hotelOrCityName,
                                              int rooms, LocalDate departingDate, LocalDate returningDate,
                                              int people);

    /**
     * Busca e retorna uma passagem presente no banco de dados usando o id (identificador).
     * @param id do tipo int
     * @return ticketEntity do tipo TicketEntity
     */
    public TicketEntity findTickets(int id);

    /**
     * Busca e retorna uma vaga de hospedagem presente no banco de dados usando o id (identificador).
     * @param id do tipo int
     * @return accommodationEntity do tipo AccommodationEntity
     */
    public AccommodationEntity findAccommodations(int id);

    /**
     * Busca e retorna um pacote presente no banco de dados usando o id (identificador).
     * @param id do tipo int
     * @return packageEntity do tipo PackageEntity
     */
    public PackageEntity findPackage(int id);

    /**
     * Busca e remove do banco de dados uma determinada passagem identificada por "ticketId".
     * @param ticketId do tipo int
     */
    public void removeTickets(int ticketId);

    /**
     * Busca e remove do banco de dados uma determinada vaga de hospedagem identificada por "accommodationId".
     * @param accommodationId do tipo int
     */
    public void removeAccommodations(int accommodationId);

    /**
     * Busca e remove do banco de dados um determinado pacote identificado por "packageId".
     * Remove também os dados das tabelas das passagens e hospedagens.
     * @param packageId do tipo int
     */
    public void removePackage(int packageId);
}