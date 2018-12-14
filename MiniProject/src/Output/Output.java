package Output;

import Classes.Project;
import Classes.Risk;
import Classes.Task;
import Classes.TeamMember;

import java.time.LocalDate;

public class Output {

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

        for(i = 0.; i < taskLength; i += 0.25) {
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
            System.out.println(String.format("%-20s %s", risk.getRiskName(), printRiskAsterix(i,risk.returnRisk(),asterisk)));
        }
    }

    public void displayProjectVariance(Project project){

        System.out.print(String.format("%-25s","Project: "+project.getProjectName()));

        for(LocalDate date: project.returnProjectIntervalDates()){
            System.out.print(String.format("%-25s", "Week "));// this should add the weeks of the project somehow, week2 week4 week6 and so forth
        }
        System.out.print("\n");

        System.out.print(String.format("%-25s","Earned Value"));

        for(LocalDate date: project.returnProjectIntervalDates()){
            System.out.print(String.format("%-25.2f",project.calculateCV(date)));
        }

        System.out.print("\n");
        System.out.print(String.format("%-25s","Schedule Variance"));

        for(LocalDate date: project.returnProjectIntervalDates()){
            System.out.print(String.format("%-25.2f",project.calculateSV(date)));
        }

        System.out.print("\n");
        System.out.print(String.format("%-25s","Cost Variance"));

        for(LocalDate date: project.returnProjectIntervalDates()){
            System.out.print(String.format("%-25.2f",project.calculateCV(date)));
        }

    }

    public void displayTaskByMember(Project project, int teamMemberId){

        System.out.println(project.retrieveTeamMember(teamMemberId).getTeamMemberName());


        for(Task task: project.assignedTasksByMember(1)){
            System.out.print(String.format("%-25s", task.getName()));
            System.out.println(String.format("%10s",task.returnHoursByMember(teamMemberId)));
        }

        System.out.println(String.format("%35s",project.allHoursByMember(teamMemberId)));


    }

    public void displayProjectSchedule(Project project){

        String asterisk = "*";
        int i = 0;

        System.out.println(String.format("%15s", "Project Schedule"));
        System.out.println( String.format("%-10s %30s,%10S,%10S,%10S,%10S,%10S,%10S,%10S, %10S ", "Weeks",
                "w2", "w4","w6","w8","w10","w12", "w14", "w16", "w18"));

        System.out.println(String.format("%-20s", "Tasks"));

        for(Task task: project.getTaskList()){
            System.out.println(String.format("%-10s %30s", task.getName(),printScheduleAsterix(i,task.getTaskDuration(),asterisk)));
        }
    }





    public void displayHoursPerTeamMember(Project project){

        for(TeamMember teamMember: project.getTeamMemberList()){
            System.out.print(String.format("%-20s", teamMember.getTeamMemberName()));
            System.out.println(project.allHoursByMember(teamMember.getTeamMemberId()));
        }

    }

}
