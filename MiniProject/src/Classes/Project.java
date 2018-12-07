package Classes;

import org.json.simple.JSONArray;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Project {

    private int projectId;
    private String projectName;
    private LocalDate actualStartDate;
    private LocalDate projectedCompletedDate;
    private double budgetAtCompletion;

   private JSONArray teamMemberList;
   private JSONArray taskList;
   private JSONArray riskList;

    public Project(int projectId, String projectName, LocalDate actualStartDate,
                   LocalDate projectedCompletedDate, double budgetAtCompletion, JSONArray teamMemberList, JSONArray taskList, JSONArray riskList) {

        this.projectId = projectId;
        this.projectName = projectName;
        this.actualStartDate = actualStartDate;
        this.projectedCompletedDate = projectedCompletedDate;
        this.budgetAtCompletion = budgetAtCompletion;

       this.taskList = taskList;
        this.teamMemberList = teamMemberList;
      this.riskList = riskList;

    }

    public JSONArray getTeamMemberList() {
        return teamMemberList;
    }

    public void setTeamMemberList(JSONArray teamMemberList) {
        this.teamMemberList = teamMemberList;
    }

    public JSONArray getTaskList() {
        return taskList;
    }

    public void setTaskList(JSONArray taskList) {
        this.taskList = taskList;
    }

    public JSONArray getRiskList() {
        return riskList;
    }

    public void setRiskList(JSONArray riskList) {
        this.riskList = riskList;
    }

    /*



    public double calculateEV(LocalDate date){
        return (percentageOfCompletedTasks(date)*this.budgetAtCompletion);
    }

    public int calculateSV(LocalDate date){
        return (int)(this.calculateEV(date)-calculatePV(date));
    }

    public double calculateCV(LocalDate date){
        return this.calculateEV(date)-this.calculateAC(date);
    }

    // should be private later on.... - Karl
//   public double percentageOfCompletedTasks(LocalDate date){
        double valueOfCompletedTasks = 0.0;
        double valueOfAllTasks = 0.0;

        for(Task task : this.taskList){
            valueOfAllTasks += task.getTaskValue();
            if(task.taskIsComplete(date)){
                valueOfCompletedTasks += task.getTaskValue();
            }
        }
        return (valueOfCompletedTasks/valueOfAllTasks);
    }

    //should be private later on.... -Karl
    public double calculatePV(LocalDate date){
        Long timeElapsed = ChronoUnit.DAYS.between(this.actualStartDate,date);
        Long projectDuration = ChronoUnit.DAYS.between(this.actualStartDate,this.projectedCompletedDate);

        double percentageOfProjectCompleted = ((double)timeElapsed/(double)projectDuration);

        return (percentageOfProjectCompleted*this.budgetAtCompletion);
    }

    public double calculateAC(LocalDate date){
        double sumOfHoursWorked;
        for(TeamMember teamMember : this.teamMemberList){
            sumOfHoursWorked += task.getTaskValue();
        }

    }

    public List<String> assignedTasksByMember(int teamMemberId){
        List<String> collect = taskList.stream()
                .filter(task -> task.getTaskMembers().containsKey(teamMemberId))
                .sorted(Comparator.comparing(Task::getName))
                //.sorted() - ??
                .map(Task::getName)
                .collect(Collectors.toList());

        return collect; //string building here instead and use String as type?
    }
    public List<Risk> retrieveRisks(){
        List<Risk> collect = riskList.stream()
                .sorted(Comparator.comparing(Risk::getRiskName))
                .collect(Collectors.toList());
        return collect;
    }

    public void workDoneByAll(){
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
    public double getBudgetAtCompletion() {
        return budgetAtCompletion;
    }

    public void setBudgetAtCompletion(double budgetAtCompletion) {
        this.budgetAtCompletion = budgetAtCompletion;
    }

    public int getProjectId() {
        return projectId;
    }

    }


*/

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", actualStartDate=" + actualStartDate +
                ", projectedCompletedDate=" + projectedCompletedDate +
                ", budgetAtCompletion=" + budgetAtCompletion +
                ", teamMemberList=" + teamMemberList +
                ", taskList=" + taskList +
                ", riskList=" + riskList +
                '}';
    }
}
