package Classes;

import java.time.LocalDate;
import java.util.ArrayList;

public class Project {

    private int projectId;
    private String projectName;
    private String actualStartDate;
    private String projectedCompletedDate;
    private double budgetAtCompletion;

    private ArrayList<TeamMember> teamMemberList;
    private ArrayList<Task> taskList;
    private ArrayList<Risk> riskList;

    public Project(int projectId, String projectName, String actualStartDate,
                   String projectedCompletedDate, double budgetAtCompletion) {

        this.projectId = projectId;
        this.projectName = projectName;
        this.actualStartDate = actualStartDate;
        this.projectedCompletedDate = projectedCompletedDate;
        this.budgetAtCompletion = budgetAtCompletion;

        taskList = new ArrayList<>();
        teamMemberList = new ArrayList<>();
        riskList = new ArrayList<>();

    }

    public double calculateEV(LocalDate date){
        return (percentageOfCompletedTasks(date)*this.budgetAtCompletion);
    }

    public double percentageOfCompletedTasks(LocalDate date){
        double valueOfCompletedTasks = 0.0;
        double valueOfAllTasks = 0.0;

        for(int i = 0; i < this.taskList.size();i++){
            if(this.taskList.get(i).taskIsComplete(date)){
                valueOfCompletedTasks += this.taskList.get(i).getTaskValue();
            }
        }
        for(Task task : this.taskList){
            valueOfAllTasks += task.getTaskValue();
        }
        return (valueOfCompletedTasks/valueOfAllTasks);
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

    public ArrayList<Risk> getRiskList() {
        return riskList;
    }

    public void setRiskList(ArrayList<Risk> riskList) {
        this.riskList = riskList;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getActualStartDate() {
        return actualStartDate;
    }

    public void setActualStartDate(String actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    public String getProjectedCompletionDate() {
        return projectedCompletedDate;
    }

    public void setProjectedCompletionDate(String projectedCompletionDate) {
        this.projectedCompletedDate = projectedCompletionDate;
    }

    public double getBudgetAtCompletion() {
        return budgetAtCompletion;
    }

    public void setBudgetAtCompletion(double budgetAtCompletion) {
        this.budgetAtCompletion = budgetAtCompletion;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectedCompletedDate() {
        return projectedCompletedDate;
    }

    public void setProjectedCompletedDate(String projectedCompletedDate) {
        this.projectedCompletedDate = projectedCompletedDate;
    }
}
