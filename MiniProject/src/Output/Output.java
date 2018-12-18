package Output;

import Classes.Project;
import Classes.Risk;
import Classes.Task;
import Classes.TeamMember;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Scanner;

public class Output {

    private TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();

    public String printRiskAsterix(double i, double returnRisk, String asterisk) {
        StringBuilder asteriskBuilder = new StringBuilder("*");

        for(i = 1.0; i < returnRisk; i += 0.5) {
            asteriskBuilder.append("*");
        }

        asterisk = asteriskBuilder.toString();
        return asterisk;
    }

    public String printScheduleAsterix(double i, long taskLength, String asterisk) {
        StringBuilder asteriskBuilder = new StringBuilder("*");

        for(i = 0; i < taskLength; i += 1) {
            asteriskBuilder.append("*");
        }

        asterisk = asteriskBuilder.toString();
        return asterisk;
    }

    public void displayRiskMatrix(Project project){
        String asterisk = "*";
        int i = 1;

        System.out.println(String.format("%15s", "Risk Matrix"));

        System.out.println(String.format("%-20s", "Risk"));

        for(Risk risk: project.getRiskList()){
            System.out.println(String.format("%-20s %-20s %s", risk.getRiskName(), printRiskAsterix(i,risk.returnRisk(),asterisk), risk.riskDescription()));

        }
    }

    public void displayProjectVariance(Project project){

        System.out.print(String.format("%-25s","Project: "+project.getProjectName()));

        for(LocalDate date: project.returnDatesPerInterval()){
            System.out.print(String.format("%-25s", "Week "+date.get(woy)));// this should add the weeks of the project somehow, week2 week4 week6 and so forth
        }

        System.out.println();
        System.out.print(String.format("%-25s","Earned Value"));

        for(LocalDate date: project.returnDatesPerInterval()){
            System.out.print(String.format("%-25.2f",project.calculateEV(date)));
        }

        System.out.println();
        System.out.print(String.format("%-25s","Schedule Variance"));

        for(LocalDate date: project.returnDatesPerInterval()){
            System.out.print(String.format("%-25.2f",project.calculateSV(date)));
        }

        System.out.println();
        System.out.print(String.format("%-25s","Cost Variance"));

        for(LocalDate date: project.returnDatesPerInterval()){
            System.out.print(String.format("%-25.2f",project.calculateCV(date)));
        }

    }

    public void displayTaskByMember(Project project, int teamMemberId){

        System.out.println(project.returnTeamMember(teamMemberId).getTeamMemberName());

        for(Task task: project.returnTasksByTeamMember(1)){
            System.out.print(String.format("%-25s", task.getName()));
            System.out.println(String.format("%10s",task.returnHoursByMember(teamMemberId)));
        }

        System.out.println(String.format("%-31s %s","Total Hours", project.returnHoursByTeamMember(teamMemberId)));

    }

    public void displayProjectSchedule(Project project){

        String asterisk = "*";
        int i = 0;

        System.out.println(String.format("%15s", "Project Schedule"));
        System.out.println( String.format("%25s %7s,%13S,%13S,%13S,%13S,%13S,%13S,%13S, %13S ", "Weeks",
                "w2", "w4","w6","w8","w10","w12", "w14", "w16", "w18"));

        System.out.println(String.format("%-40s", "Tasks"));

        for(Task task: project.getTaskList()){
            System.out.print(String.format("%-31s",task.getName()));
            for(int k = 0; k < project.returnWeeksBetweenTasks(task); k++){
                System.out.print("              ");
            }
            System.out.print(printScheduleAsterix(i,task.returnTaskDurationInDays(),asterisk));
            System.out.println();
        }
    }

    public void displayHoursPerTeamMember(Project project){

        for(TeamMember teamMember: project.getTeamMemberList()){
            System.out.println(String.format("%-30s %s", teamMember.getTeamMemberName(),project.returnHoursByTeamMember(teamMember.getTeamMemberId())));

        }
        System.out.println("==============================");
        System.out.println(String.format("%-30s", "Total Hours"));
    }

}