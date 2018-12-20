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

    public void runMenu(Project project, Output output) {

        Scanner sc = new Scanner(System.in);
        int option;

        do {
            output.displayMenu(menuOptions);
            output.displayMessage("Select an option...");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    output.displayProjectSchedule(project);
                    break;
                case 2:
                    output.displayProjectVariance(project);
                    break;
                case 3:
                    output.displayRiskMatrix(project);
                    break;
                case 4:
                    output.displayTeamMembers(project);
                    output.displayMessage("Enter team member ID:");
                    int teamMemberId = sc.nextInt();
                    output.displayTaskByMember(project,teamMemberId);
                    break;
                case 5:
                    output.displayHoursPerTeamMember(project);
                    break;

                default:
                    // The user input an unexpected choice.
            }
        } while (option!=6);
    }
}

