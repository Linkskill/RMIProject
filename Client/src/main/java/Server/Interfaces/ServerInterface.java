package Server.Interfaces;

import Models.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

/**
 * Interface que representa as operações executadas pelo Servidor que auxiliam as operações executadas pelo
 * cliente. É implementada pela classe ClientServerServant do Serivdor.
 */
public interface ServerInterface extends Remote {
    /**
     * Procura e Retorna as passagens presentes no banco de dados.
     * A busca é feita por meio da invocação do método searchTickets da interface DAO implementada por
     * DAOJPAImplementation. Os seguintes parâmetros são utilizados na busca:
     * @param ticketMode modo da passagem \"somente ida\" ou \"ida e volta\"
     * @param origin local de origem
     * @param destination local de destino
     * @param departingDate data de partida
     * @param returningDate data de retorno
     * @param people número de pessoas
     * @return tickets uma lista do tipo TicketData contendo todas as passagens disponiveis
     * @throws RemoteException avisa exceções na criação de objetos remotos
     */
    public List<TicketData> getTickets(String ticketMode, String origin,
                                       String destination, LocalDate departingDate,
                                       LocalDate returningDate, int people) throws RemoteException;

    /**
     * Procura e Retorna as vagas de hospedagem presentes no banco de dados.
     * A busca é feita por meio da invocação do método searchAccommodations da interface DAO implementada por
     * DAOJPAImplementation. Os seguintes parâmetros são utilizados na busca:
     * @param hotelOrCityName nome da cidade ou do hotel
     * @param entranceDate data de entrada
     * @param exitDate data de saída
     * @param rooms número de quartos diponíveis
     * @param people total de pessoas a serem hospedadas
     * @return accommodations uma lista do tipo AccommodationData contendo todas as vagas de hospedagem
     * @throws RemoteException avisa casos de exceção na criação de objetos remotos
     */
    public List<AccommodationData> getAccommodations(String hotelOrCityName,
                                                     LocalDate entranceDate, LocalDate exitDate,
                                                     int rooms, int people) throws RemoteException;

    /**
     * Procura e Retorna os pacotes presentes no banco de dados.
     * A busca é feita por meio da invocação do método searchPackages da interface DAO implementada por
     * DAOJPAImplementation. Os seguintes parâmetros são utilizados na busca:
     * @param ticketMode modo da passagem \"somente ida\" ou \"ida e volta\"
     * @param origin local de origem
     * @param hotelOrCityName nome da cidade ou do hotel
     * @param rooms número de quartos
     * @param departingDate data de partida
     * @param returningDate data de retorno
     * @param people número de pessoas
     * @return packages lista do tipo PackageData contendo todos os pacotes disponíveis
     * @throws RemoteException avisa exceções na criação de objetos remotos
     */
    public List<PackageData> getPackages(String ticketMode, String origin, String hotelOrCityName,
                                         int rooms, LocalDate departingDate, LocalDate returningDate,
                                         int people) throws RemoteException;

    /**
     * Busca e retira as passagens desejadas pelo cliente do banco de dados.
     * Realiza a busca por meio da chamada do método findTickets da interface DAO implementada por
     * DAOJPAImplementation. A remoção se dá por meio da chamada do método sincronizado buy
     * da classe TicketEntity.
     * @param id número que identifica a passagem
     * @throws RemoteException avisa casos de exceção na criação de objetos remotos
     */
    public void buyTickets(int id) throws RemoteException;

    /**
     * Busca e retira as vagas de hospedagem desejadas pelo cliente do banco de dados.
     * Realiza a busca por meio da chamada do método findAccommodations da interface DAO implementada por
     * DAOJPAImplementation. A remoção se dá por meio da chamada do método sincronizado buy
     * da classe AccommodationEntity.
     * @param id número que identifica a vaga de hospedagem
     * @throws RemoteException avisa casos de exceção na criação de objetos remotos
     */
    public void buyAccommodations(int id) throws RemoteException;

    /**
     * Busca e retira os pacotes desejados pelo cliente do banco de dados.
     * Realiza a busca por meio da chamada do método findPackage da interface DAO implementada por
     * DAOJPAImplementation. A remoção se dá por meio da chamada do método sincronizado buy
     * da classe PackageEntity.
     * @param id número que identifica o pacote
     * @throws RemoteException avisa casos de exceção na criação de objetos remotos
     */
    public void buyPackages(int id) throws RemoteException;

    /**
     * Registra o interesse do cliente por algum tipo de evento (passagens, hospedagens ou pacotes).
     * Cria um interesse do tipo InterestData que armazena os parâmetros e o registra por meio da chamada
     * do método registerInterest da classe EventHandler. Posteriormente, é imprimido na tela do cliente
     * o interesse registrado.
     * @param eventTypeList lista do tipo EventType contendo os eventos escolhidos pelo cliente
     * @param destination local de destino
     * @param maxPrice o preço máximo aceitado pelo cliente
     * @param clientReference a referência do cliente
     * @throws RemoteException avisa casos de exceção na criação de objetos remotos
     */
    public void registerInterest(List<EventType> eventTypeList, String destination, float maxPrice,
                                 ClientInterface clientReference) throws RemoteException;

    /**
     * Retorna para o cliente todos os seus interesses.
     * @param clientReference referência do cliente
     * @return clientInterests lista do tipo InterestData com todos os interesses do cliente
     * @throws RemoteException avisa casos de exceção na criação de objetos remotos
     */
    List<InterestData> getInterests(ClientInterface clientReference) throws RemoteException;

    /**
     * Remove um determinado interesse. Cria um objeto da classe InterestData a partir dos parâmentros e
     * o transmite para o método removeInterest da classe EventHandler.
     * @param eventTypeList lista do tipo EventType contendo os eventos escolhidos pelo cliente
     * @param destination local de destino
     * @param maxPrice preço máximo aceitado pelo cliente
     * @param clientReference referência do cliente
     * @throws RemoteException avisa casos de exceção na criação de objetos remotos
     */
    void removeInterest(List<EventType> eventTypeList, String destination,
                        float maxPrice, ClientInterface clientReference) throws RemoteException;

}
