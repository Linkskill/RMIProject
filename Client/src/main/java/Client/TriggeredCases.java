package Client;

import Models.*;
import Server.Interfaces.ClientInterface;
import Utilities.DateHandler;
import Server.Interfaces.ServerInterface;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TriggeredCases {
    private final Scanner s = new Scanner(System.in);

    /**
     * Mostra o menu de seleção (opções disponíveis).
     */
    public void displayMenu() {
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Consultar passagens aéreas;");
        System.out.println("2 - Consultar hospedagens;");
        System.out.println("3 - Consultar pacotes;");
        System.out.println("4 - Comprar passagens aéreas;");
        System.out.println("5 - Comprar vaga de hospedagem;");
        System.out.println("6 - Comprar pacotes;");
        System.out.println("7 - Registrar interesse;");
        System.out.println("8 - Ver interesses registrados;");
        System.out.println("9 - Remover interesse;");
        System.out.println("0 - Sair.");
    }

    /**
     * Responde se as passagens desejadas pelo cliente estão disponíveis.
     * Usa a referência do servidor para executar os métodos a cerca disso.
     * Veja em {@link Server.Interfaces.ServerInterface#getTickets(String ticketMode, String origin,
     *          String destination, LocalDate departingDate, LocalDate returningDate, int people)}
     */
    public void showTickets() {
        System.out.println("→ Para verificar as passagens é necessário preencher alguns campos.\n");

        System.out.println("A passagem é de \"somente ida\" ou \"ida e volta\"?");
        System.out.print("Modo de passagem: ");
        String ticketMode = s.nextLine();

        System.out.print("Origem: ");
        String origin = s.nextLine();

        System.out.print("Destino: ");
        String destination = s.nextLine();

        System.out.print("Partida (dia-mês-ano): ");
        LocalDate departingDate = DateHandler.stringToDate(s.nextLine());

        System.out.print("Retorno (dia-mês-ano): ");
        LocalDate returningDate = DateHandler.stringToDate(s.nextLine());

        System.out.print("Número de pessoas: ");
        int people = s.nextInt();
        s.nextLine();

        ServerInterface serverRef = Client.getServerReference();
        List<TicketData> ticketDataList;

        try {
            ticketDataList = serverRef.getTickets(ticketMode, origin, destination,
                    departingDate, returningDate, people);
        } catch (RemoteException e) {
            e.printStackTrace();
            return;
        }

        System.out.println("\n\n");
        System.out.println("Passagens encontradas: " + ticketDataList.size());
        for (TicketData tickets : ticketDataList) {
            System.out.println(tickets.getDataToString());
        }
    }

    /**
     * Verifica se as hospedagens desejadas pelo cliente estão disponíveis.
     * Usa a referência do servidor para executar os métodos a cerca disso.
     * Veja em {@link Server.Interfaces.ServerInterface#getAccommodations(String hotelOrCityName,
     *          LocalDate entranceDate, LocalDate exitDate, int rooms, int people)}
     */
    public void showAccommodations() {
        System.out.println("→ Para verificar as hospedagens é necessário preencher alguns campos.\n");
        System.out.print("Nome do hotel ou da cidade: ");
        String hotelOrCityName = s.nextLine();

        System.out.print("Data de entrada (dia-mês-ano): ");
        LocalDate entranceDate = DateHandler.stringToDate(s.nextLine());

        System.out.print("Data de saida (dia-mês-ano): ");
        LocalDate exitDate = DateHandler.stringToDate(s.nextLine());

        System.out.print("Número de quartos: ");
        int rooms = s.nextInt();
        s.nextLine();

        System.out.print("Número de pessoas: ");
        int people = s.nextInt();
        s.nextLine();

        ServerInterface serverRef = Client.getServerReference();
        List<AccommodationData> accommodationDataList;

        try {
            accommodationDataList = serverRef.getAccommodations(hotelOrCityName,
                    entranceDate, exitDate, rooms, people);
        } catch (RemoteException e) {
            e.printStackTrace();
            return;
        }

        System.out.println("\n\n");
        System.out.println("Vagas de hospedagem encontradas: " + accommodationDataList.size());
        for (AccommodationData accommodations : accommodationDataList) {
            System.out.println(accommodations.getDataToString());
        }
    }

    /**
     * Verifica se os pacotes desejados pelo cliente estão disponíveis.
     * Usa a referência do servidor para executar os métodos a cerca disso.
     * Veja em {@link Server.Interfaces.ServerInterface#getPackages(String ticketMode, String origin,
     *      String hotelOrCityName, int rooms, LocalDate departingDate, LocalDate returningDate, int people)}
     */
    public void showPackages() {
        System.out.println("→ Para verificar as pacotes é necessário preencher alguns campos.\n");

        System.out.println("A passagem é de \"somente ida\" ou \"ida e volta\"?");
        System.out.print("Modo da passagem: ");
        String ticketMode = s.nextLine();

        System.out.print("Origem: ");
        String origin = s.nextLine();

        System.out.print("Destino: ");
        String hotelOrCityName = s.nextLine();

        System.out.print("Número de quartos: ");
        int rooms = s.nextInt();
        s.nextLine();

        System.out.print("Data de partida (dia-mês-ano): ");
        LocalDate departingDate = DateHandler.stringToDate(s.nextLine());

        System.out.print("Data de retorno (dia-mês-ano): ");
        LocalDate returningDate = DateHandler.stringToDate(s.nextLine());

        System.out.print("Número de Pessoas: ");
        int people = s.nextInt();
        s.nextLine();

        ServerInterface serverRef = Client.getServerReference();
        List<PackageData> packageDataList;

        try {
            packageDataList = serverRef.getPackages(ticketMode, origin, hotelOrCityName,
                    rooms, departingDate, returningDate, people);
        } catch (RemoteException e) {
            e.printStackTrace();
            return;
        }

        System.out.println("\n\n");
        System.out.println("Pacotes encontrados: " + packageDataList.size());
        for (PackageData packages : packageDataList) {
            System.out.println(packages.getDataToString());
        }
    }

    /**
     * Retira as passagens compradas pelos clientes do banco de dados.
     * Usa a referência do servidor para executar os métodos a cerca disso.
     * Veja em {@link Server.Interfaces.ServerInterface#buyTickets(int id)}
     */
    public void buyTickets() {
        System.out.print("Insira o ID da passagem: ");
        int id = s.nextInt();
        s.nextLine();

        ServerInterface serverRef = Client.getServerReference();
        try {
            serverRef.buyTickets(id);
        } catch (RemoteException e) {
            e.printStackTrace();
            return;
        }
        System.out.print("Passagem " + id + " adquirida\n");
    }

    /**
     * Retira as hospedagens compradas pelos clientes do banco de dados.
     * Usa a referência do servidor para executar os métodos a cerca disso.
     * Veja em {@link Server.Interfaces.ServerInterface#buyAccommodations(int id)}
     */
    public void buyAccommodations() {
        System.out.print("Insira o ID da hospedagem: ");
        int id = s.nextInt();
        s.nextLine();

        ServerInterface serverRef = Client.getServerReference();
        try {
            serverRef.buyAccommodations(id);
        } catch (RemoteException e) {
            e.printStackTrace();
            return;
        }
        System.out.print("Vaga de hospedagem " + id + " adquirida\n");
    }

    /**
     * Retira os pacotes comprados pelos clientes do banco de dados.
     * Usa a referência do servidor para executar os métodos a cerca disso.
     * Veja em {@link Server.Interfaces.ServerInterface#buyPackages(int id)}
     */
    public void buyPackages() {
        System.out.print("Insira o ID do pacote: ");
        int id = s.nextInt();
        s.nextLine();

        ServerInterface serverRef = Client.getServerReference();
        try {
            serverRef.buyPackages(id);
        } catch (RemoteException e) {
            e.printStackTrace();
            return;
        }
        System.out.print("Pacote " + id + " adquirido\n");
    }

    /**
     * Registra os interesses do cliente. Usa a referência do servidor para executar os métodos a cerca disso.
     * Veja em {@link Server.Interfaces.ServerInterface#registerInterest(List eventTypes, String destination,
     *          float maxPrice, ClientInterface clientReference)}
     */
    public void registerInterest() {
        System.out.println("→ No que você está interessado? ←");
        System.out.print("Qual tipo de interesse (passagens, hospedagens ou pacotes?): ");
        String eventsString = s.nextLine();

        List<EventType> eventTypes = new ArrayList<>();
        if(eventsString.matches(".*passagens.*"))
            eventTypes.add(EventType.NEW_TICKET);
        if(eventsString.matches(".*hospedagens.*"))
            eventTypes.add(EventType.NEW_ACCOMMODATION);
        if(eventsString.matches(".*pacotes.*"))
            eventTypes.add(EventType.NEW_PACKAGE);

        System.out.print("Destino: ");
        String destination = s.nextLine();

        System.out.print("Preço máximo a pagar: ");
        float maxPrice = s.nextFloat();
        s.nextLine();

        ServerInterface serverRef = Client.getServerReference();
        try {
            serverRef.registerInterest(eventTypes, destination, maxPrice, Client.getClientReference());
        } catch (RemoteException e) {
            System.err.println(e.getMessage());
            return;
        }

        System.out.println("Interesse registrado. Você receberá uma notificação quando o evento ocorrer.");
    }

    /**
     * Imprime todos os interesses do cliente.
     * Recebe os dados usando a referência do Servidor para a invocação do método getInterests.
     * Veja em {@link Server.Interfaces.ServerInterface#getInterests(ClientInterface clientReference)}
     */
    public void showInterests() {
        ServerInterface serverRef = Client.getServerReference();
        List<InterestData> interestsRegistered;
        try {
            interestsRegistered = serverRef.getInterests(Client.getClientReference());
        } catch (RemoteException e) {
            System.err.println(e.getMessage());
            return;
        }

        System.out.println("\n");
        System.out.println("→ " + interestsRegistered.size() + " interesses registrados.");
        for (InterestData interest : interestsRegistered) {
            System.out.println(interest.getDataToString());
        }
    }

    /**
     * Remove o registro de interesse do cliente.
     * Usa a referência do servidor para executar os métodos relativos a remoção do registro.
     * Veja em {@link Server.Interfaces.ServerInterface#removeInterest(List eventTypeList, String destination,
     *          float maxPrice, ClientInterface clientReference)}
     */
    public void unregister() {
        System.out.println("→ Preencha os campos para remover o interesse.");
        System.out.print("Qual tipo de interesse (passagens, hospedagens ou pacotes?): ");
        String eventsString = s.nextLine();

        List<EventType> eventTypes = new ArrayList<>();
        if(eventsString.matches(".*passagens.*"))
            eventTypes.add(EventType.NEW_TICKET);
        if(eventsString.matches(".*hospedagens.*"))
            eventTypes.add(EventType.NEW_ACCOMMODATION);
        if(eventsString.matches(".*pacotes.*"))
            eventTypes.add(EventType.NEW_PACKAGE);

        System.out.print("Destino: ");
        String destination = s.nextLine();

        System.out.print("Preço máximo a pagar: ");
        float maxPrice = s.nextFloat();
        s.nextLine();

        ServerInterface serverRef = Client.getServerReference();
        try {
            serverRef.removeInterest(eventTypes, destination, maxPrice, Client.getClientReference());
        } catch (RemoteException e) {
            System.err.println(e.getMessage());
            return;
        }
    }
}
