

import java.util.ArrayList;


public class Project {

    private String name;
    private String startDate;
    private String endDate;
    private double budgetAtCompletion;

    //private ArrayList<ArrayList<Task>> taskList;
    private ArrayList<Task> taskList;
    private ArrayList<TeamMember> teamMemberList;
    private ArrayList<risk> riskList;


    public Project(String name, String startDate, String endDate, double budgetAtCompletion) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budgetAtCompletion = budgetAtCompletion;
    }


    public double calcEstimValue(){

        double estimatedValue = 0;

    return estimatedValue;
    }

    public void scheduleVariance(){


    }

    public void retrieveRisk(){


    }

    public void workDoneByMember(){


    }

    public void workDoneByAll(){


    }

    public void timeWorkedByMember(){


    }

    public void calcCostVariance(){


    }

    public double calcPlannedValue(){

       double plannedValue = 0;
        return plannedValue;
    }

    public void projectSchedule(){


    }

    public void retrieveTeamMember(){


    }

    public double getBudgetedCost() {
        return budgetAtCompletion;
    }

    public void setBudgetedCost(double budgetAtCompletion) {
        this.budgetAtCompletion = budgetAtCompletion;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<TeamMember> getTeamMemberList() {
        return teamMemberList;
    }

    public void setTeamMemberList(ArrayList<TeamMember> teamMemberList) {
        this.teamMemberList = teamMemberList;
    }

    public ArrayList<risk> getRiskList() {
        return riskList;
    }

    public void setRiskList(ArrayList<risk> riskList) {
        this.riskList = riskList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getBudgetAtCompletion() {
        return budgetAtCompletion;
    }

    public void setBudgetAtCompletion(double budgetAtCompletion) {
        this.budgetAtCompletion = budgetAtCompletion;
    }





}
