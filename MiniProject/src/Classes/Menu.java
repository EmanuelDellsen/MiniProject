

import java.util.Scanner;

public class Menu {

    public void runMenu() {

        Scanner sc = new Scanner(System.in);
        int option;

        do {
            showMenu();
            System.out.println("Select an option...");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    // code
                    break;
                case 2:
                    // code
                    break;
                case 3:
                    // code
                    break;
                case 4:
                    // code
                    break;
                case 5:
                    // code
                    break;
                case 6:
                    // code
                    break;
                case 7:
                    // code
                    break;


                default:
                    // The user input an unexpected choice.
            }
        } while (option!=9);
    }

    private void showMenu() {

        System.out.println("============================");
        System.out.println("|   MENU SELECTION          |");
        System.out.println("============================");
        System.out.println("| Options:                |");
        System.out.println("|        1. Option        |");
        System.out.println("|        2. Option        |");
        System.out.println("|        3. Option        |");
        System.out.println("|        4. Option        |");
        System.out.println("|        5. Option        |");
        System.out.println("|        6. Option        |");
        System.out.println("|        7. Option        |");
        System.out.println("|        8. Option        |");
        System.out.println("|        9. Exit          |");
        System.out.println("============================");
    }

    public static void main(String[] args) {
    Menu menu = new Menu();
    menu.runMenu();

    }
}
