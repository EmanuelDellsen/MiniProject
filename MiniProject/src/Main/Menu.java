package Main;

import Classes.Project;
import Output.Output;

import java.util.Scanner;

public class Menu {

    public static final String[] menuOptions = {
            "1. Project Schedule",
            "2. Project Variances",
            "3. Risk Matrix",
            "4. Tasks by team member",
            "5. Time spent on project",
            "6. Exit program"
    };

    public void runMenu(Project currentProject, Output currentOutput) {

        Scanner sc = new Scanner(System.in);
        int option;

        do {
            showMenu();
            System.out.println("Select an option...");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    currentOutput.displayProjectSchedule(currentProject);
                    break;
                case 2:
                    currentOutput.displayProjectVariance(currentProject);
                    break;
                case 3:
                    currentOutput.displayRiskMatrix(currentProject);
                    break;
                case 4:
                    int teamMemberId = sc.nextInt();
                    currentOutput.displayTaskByMember(currentProject,teamMemberId);
                    break;
                case 5:
                    currentOutput.displayHoursPerTeamMember(currentProject);
                    break;

                default:
                    // The user input an unexpected choice.
            }
        } while (option!=6);
    }

    private void showMenu() {

        System.out.println();
        System.out.println();
        printSymbol("=",32);
        System.out.println();
        System.out.println(String.format("|%-30s|", "Option"));

        for (String menuOption: menuOptions){
            System.out.println(String.format("|%30s|", menuOption));
        }

        printSymbol("=",32);
        System.out.println();
    }

    private void printSymbol(String symbol, int printLength){
        for (int i = 0; i < printLength; i++){
            System.out.print(symbol);
        }
    }
}
