

import java.util.ArrayList;
import java.util.List;


public class Project {

    private String name;
    private String startDate;
    private String endDate;
    private double budgetedCost;

    private ArrayList<ProjTask> tasksList;
    private ArrayList<TeamMate> teammateList;
    private ArrayList<risk> riskList;


    public Project(String name, String startDate, String endDate, double budgetedCost) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budgetedCost = budgetedCost;
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
        return budgetedCost;
    }

    public void setBudgetedCost(double budgetedCost) {
        this.budgetedCost = budgetedCost;
    }

    public ArrayList<ProjTask> getTasksList() {
        return tasksList;
    }

    public void setTasksList(ArrayList<ProjTask> tasksList) {
        this.tasksList = tasksList;
    }

    public ArrayList<TeamMate> getTeammateList() {
        return teammateList;
    }

    public void setTeammateList(ArrayList<TeamMate> teammateList) {
        this.teammateList = teammateList;
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

    public double getBudgetedActualCost() {
        return budgetedCost;
    }

    public void setBudgetedActualCost(double budgetedActualCost) {
        this.budgetedCost = budgetedActualCost;
    }





}
