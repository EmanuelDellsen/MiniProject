package Main;

import Classes.Project;
import Output.Output;

import java.util.Scanner;

public class Menu {

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

        System.out.println("============================");
        System.out.println("|   MENU SELECTION          |");
        System.out.println("============================");
        System.out.println("| Options:                |");
        System.out.println("|        1. Project schedule        |");
        System.out.println("|        2. Project variances        |");
        System.out.println("|        3. Project risk matrix        |");
        System.out.println("|        4. Search by team member for show tasks        |");
        System.out.println("|        5. Time spent on the project by team member         |");
        System.out.println("|        6. Exit          |");
        System.out.println("============================");
    }
}
