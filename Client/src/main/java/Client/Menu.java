package Client;

import java.util.Scanner;

/**
 * Menu de seleção, chama os métodos da classe TriggeredCases para a execução dos métodos desejados.
 * Veja mais em:
 * @see TriggeredCases
 */
public class Menu {
    public void choice() {
        Scanner s = new Scanner(System.in);
        int option;
        TriggeredCases trigger = new TriggeredCases();
        while(true) {
            trigger.displayMenu();
            option = s.nextInt();
            s.nextLine();
            switch(option) {
                case 1:
                    trigger.showTickets();
                    break;
                case 2:
                    trigger.showAccommodations();
                    break;
                case 3:
                    trigger.showPackages();
                    break;
                case 4:
                    trigger.buyTickets();
                    break;
                case 5:
                    trigger.buyAccommodations();
                    break;
                case 6:
                    trigger.buyPackages();
                    break;
                case 7:
                    trigger.registerInterest();
                    break;
                case 8:
                    trigger.showInterests();
                    break;
                case 9:
                    trigger.unregister();
                    break;
                case 0:
                    System.out.println("Boa viagem!");
                    System.exit(0);
                default:
                    System.out.println("Opção Inválida");
            }
        }
    }
}
