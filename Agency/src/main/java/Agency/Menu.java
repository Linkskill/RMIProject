package Agency;

import Models.AccommodationData;
import Models.TicketData;
import Server.Interfaces.AgencyInterface;
import Utilities.DateHandler;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Classe que representa o Menu de seleção para a Agencia.
 * Possui opções para publicar novas passagens aéreas e hospedagens e os métodos relativos a essa opções.
 */
public class Menu {
    private final Scanner s = new Scanner(System.in);

    public void choice() {
        while(true) {
            displayMenu();
            int option = s.nextInt();
            s.nextLine();
            if (option != 0) {
                handleOption(option);
            } else {
                return;
            }
        }
    }

    /**
     * Mostra as opções disponíveis.
     */
    private void displayMenu() {
        System.out.println("\n → Nome da Agência ←");
        System.out.println("1 - Publicar nova passagem aérea");
        System.out.println("2 - Publicar nova vaga de hotel");
        System.out.println("0 - Sair");
    }

    /**
     * Recebe uma opção por parâmetro e age de acordo com a escolha.
     * @param option é a opção inserida pelo funcionário da agência
     */
    private void handleOption(int option) {
        AgencyInterface serverRef = Agency.getServerReference();

        switch(option) {
            case 1:
                TicketData newTicket = newTicketPublished();
                try {
                    serverRef.announceTickets(newTicket);
                    System.out.println("\nPublicado: ");
                    System.out.println(newTicket.getDataToString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                AccommodationData newAccommodation = newAccommodationPublished();
                try {
                    serverRef.announceAccommodations(newAccommodation);
                    System.out.println("\nPublicado: ");
                    System.out.println(newAccommodation.getDataToString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("Opcao invalida");
        }
    }

    /**
     * Cria uma nova passagem de acordo com os dados inseridos pelo usuário.
     * Usa o modelo TicketData para armazenar os dados a partir de seu construtor.
     * Veja em {@link Models.TicketData#TicketData(String ticketMode,String origin, String destination,
     *                  LocalDate departingDate, LocalDate returningDate, int people, float price)}
     * @return ticketData possui os dados que uma oferta de passagem possui.
     */
    private TicketData newTicketPublished() {
        System.out.println("Insira os dados da nova oferta de passagem aérea:");
        System.out.println("A passagem é do modo \"somente ida\" ou \"ida e volta\"?");

        System.out.print("Modo da passagem: ");
        String ticketMode = s.nextLine();

        System.out.print("Origem: ");
        String origin = s.nextLine();

        System.out.print("Destino: ");
        String destination = s.nextLine();

        System.out.print("Data de partida (dia-mês-ano): ");
        LocalDate departingDate = DateHandler.stringToDate( s.nextLine() );

        System.out.print("Data de retorno (dia-mês-ano): ");
        LocalDate returningDate = DateHandler.stringToDate( s.nextLine() );

        System.out.print("Número de pessoas: ");
        int people = s.nextInt();
        s.nextLine();

        System.out.print("Preço: ");
        float price = s.nextFloat();

        TicketData ticketData = new TicketData(
                ticketMode, origin, destination, departingDate,
                returningDate, people, price
        );
        return ticketData;
    }

    /**
     * Cria uma nova oferta de hospedagem de acordo com os dados inseridos pelo usuário.
     * Usa o modelo AccommodationData para armazenar os dados a partir de seu construtor.
     * Veja em {@link Models.AccommodationData#AccommodationData(String location, String hotel,
     *          LocalDate entranceDate, LocalDate exitDate, int rooms, int people, float price)}
     * @return accommodationData possui os dados que uma oferta de hospedagem possui.
     */
    private AccommodationData newAccommodationPublished() {
        System.out.println("Insira os dados da nova vaga de hotel:");

        System.out.print("Local: ");
        String location = s.nextLine();

        System.out.print("Hotel: ");
        String hotel = s.nextLine();

        System.out.print("Data de entrada (dia-mês-ano): ");
        LocalDate entranceDate = DateHandler.stringToDate( s.nextLine() );

        System.out.print("Data de saída: (dia-mês-ano): ");
        LocalDate exitDate = DateHandler.stringToDate( s.nextLine() );

        System.out.print("Número de quartos: ");
        int rooms = s.nextInt();
        s.nextLine();

        System.out.print("Número de pessoas: ");
        int people = s.nextInt();
        s.nextLine();

        System.out.print("Preco: ");
        float price = s.nextFloat();

        AccommodationData accommodationData = new AccommodationData(
                location, hotel, entranceDate, exitDate,
                rooms, people, price
        );
        return accommodationData;
    }
}
