package Main;

import Classes.Project;
import Output.Output;

import java.util.Scanner;

public class Menu {

    private final static int PROJECT_SCHEDULE = 1;
    private final static int PROJECT_VARIANCE = 2;
    private final static int RISK_MATRIX = 3;
    private final static int TASKS_BY_MEMBER = 4;
    private final static int HOURS_BY_MEMBER = 5;

    private static final String[] menuOptions = {
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
            while (!sc.hasNextInt()){
                output.displayMessage("Select an option...");
                String input = sc.next();
                output.displayMessage(String.format("\"%s\" is not a valid option.\n",input));
                output.displayMenu(menuOptions);
            }
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case PROJECT_SCHEDULE:
                    output.displayProjectSchedule(project);
                    break;
                case PROJECT_VARIANCE:
                    output.displayProjectVariance(project);
                    break;
                case RISK_MATRIX:
                    output.displayRiskMatrix(project);
                    break;
                case TASKS_BY_MEMBER:
                    output.displayTeamMembers(project);
                    output.displayMessage("Enter team member ID:");
                    int teamMemberId = sc.nextInt();
                    output.displayTaskByMember(project,teamMemberId);
                    break;
                case HOURS_BY_MEMBER:
                    output.displayHoursPerTeamMember(project);
                    break;

                default:
                    // The user input an unexpected choice.
            }
        } while (option!=6);
    }
}

