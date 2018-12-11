package Classes;

import java.time.LocalDate;
import java.util.List;

public class TestOutput {

    public void displayRiskMatrix(List<Risk> riskList){

        System.out.println(String.format("%15s", "Risk Matrix"));

        System.out.println(String.format("%-20s %-15s %-15s %s", "Risk name", "Probability", "Impact", "Risk"));

        for(Risk risk: riskList){
            System.out.println(String.format("%-20s %-15s %-15s %s", risk.getRiskName(), risk.getProbability(), risk.getImpact(), risk.returnRisk()));
        }
    }

    public void displayProjectVariance(Project project){

        System.out.print(String.format("%-25s","Project: "+project.getProjectName()));

        int index = 2;
        for(LocalDate date: project.returnProjectIntervalDates()){
            System.out.print(String.format("%-25s", "Week "+(index++)));
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

    public void displaySearchByMember(){



    }

    public void displayTaskByMember(Project project, int teamMemberId){


        for(Task task: project.getTaskList()){
            System.out.print(String.format("%-25s",task.returnHoursByMember(teamMemberId)));
        }


    }

    public void displayProjectSchedule(){

    }

    public void displayTasksByAllMembers(){

    }
}
