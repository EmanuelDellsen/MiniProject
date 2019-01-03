package Main;

import Classes.Project;
import JFreeCharts.GanttChart;
import Output.Output;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

class Menu {

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

    void runMenu(Project currentProject, Output currentOutput, Helper currentHelper) {

        int option;

        do {
            currentOutput.displayMenu(menuOptions);
            currentOutput.displayMessage("Select an option...");
            while (!sc.hasNextInt()){
                String input = sc.next();
                currentOutput.displayMessage(String.format("\"%s\" is not a valid option.\n",input));
                currentOutput.displayMenu(menuOptions);
            }
            option = sc.nextInt();

            switch (option) {
                case PROJECT_SCHEDULE:
                    //Displays the project schedule in ascii
                    currentOutput.displayProjectSchedule(currentProject);

                    //Displays the project schedule as a ganttChart in a seperate frame
                    SwingUtilities.invokeLater(()->{
                        GanttChart ganttChart = new GanttChart(currentProject);
                            ganttChart.setSize(800,400);
                            ganttChart.setLocationRelativeTo(null);

                            //When the Ganttchart is closed, the system continues to run
                            ganttChart.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                            ganttChart.setVisible(true);
                    });
                    break;
                case PROJECT_VARIANCE:
                    currentOutput.displayProjectVariance(currentProject);
                    break;
                case RISK_MATRIX:
                    currentOutput.displayRiskMatrix(currentProject);
                    break;
                case TASKS_BY_MEMBER:

                    boolean caseIsActive = true;
                    do {
                        currentOutput.displayTeamMembers(currentProject);
                        currentOutput.displayMessage("Enter team member ID:");
                        String teamMemberId = sc.next();

                        //Validation of user input when choosing team member to be displayed
                        if (!currentHelper.isInteger(teamMemberId)){
                            currentOutput.displayMessage("Please type a valid option");
                        } else if (currentProject.returnTeamMember(Integer.parseInt(teamMemberId))==null){
                            currentOutput.displayMessage("Couldn't not find any team member with ID: "+teamMemberId);
                        } else {
                            currentOutput.displayTaskByMember(currentProject,Integer.parseInt(teamMemberId));
                            caseIsActive = false;
                        }

                    } while (caseIsActive);
                    break;
                case HOURS_BY_MEMBER:
                    currentOutput.displayHoursPerTeamMember(currentProject);
                    break;
                case EXIT_PROGRAM:
                    currentOutput.displayMessage("See you soon!");
                    System.exit(0);
                default:
                    // The user input an unexpected choice.
            }
        } while (option!=CHANGE_PROJECT);
    }

}

