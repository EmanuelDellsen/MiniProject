package Main;

import Classes.Project;
import JFreeCharts.ganttChart;
import Output.Output;

import java.util.Scanner;

public class Menu {

    private final static int PROJECT_SCHEDULE = 1;
    private final static int PROJECT_VARIANCE = 2;
    private final static int RISK_MATRIX = 3;
    private final static int TASKS_BY_MEMBER = 4;
    private final static int HOURS_BY_MEMBER = 5;
    private final static int CHANGE_PROJECT = 6;
    private final static int EXIT_PROGRAM = 7;

    private final Scanner sc = new Scanner(System.in);


    private static final String[] menuOptions = {
            "1. Project Schedule",
            "2. Project Variances",
            "3. Risk Matrix",
            "4. Tasks by team member",
            "5. Time spent on project",
            "6. Change project",
            "7. Exit program"
    };

    public void runMenu(Project project, Output output) {

        int option;

        do {
            output.displayMenu(menuOptions);
            output.displayMessage("Select an option...");
            while (!sc.hasNextInt()){
                String input = sc.next();
                output.displayMessage(String.format("\"%s\" is not a valid option.\n",input));
                output.displayMenu(menuOptions);
            }
            option = sc.nextInt();

            switch (option) {
                case PROJECT_SCHEDULE:
                    output.displayProjectSchedule(project);
                    ganttChart ganttChart = new ganttChart(project);
                    //ganttChart.setStage();
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
                case EXIT_PROGRAM:
                    System.exit(0);
                default:
                    // The user input an unexpected choice.
            }
        } while (option!=CHANGE_PROJECT);
    }
}

