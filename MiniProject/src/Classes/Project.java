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

    private List<TeamMember> teamMemberList = new ArrayList<>();
    private List<Task> taskList = new ArrayList<>();
    private List<Risk> riskList = new ArrayList<>();


    public Project(int projectId, String projectName, LocalDate actualStartDate,
                   LocalDate projectedCompletedDate, double budgetAtCompletion) {

        this.projectId = projectId;
        this.projectName = projectName;
        this.actualStartDate = actualStartDate;
        this.projectedCompletedDate = projectedCompletedDate;
        this.budgetAtCompletion = budgetAtCompletion;

    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public LocalDate getActualStartDate() {
        return actualStartDate;
    }

    public void setActualStartDate(LocalDate actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    public LocalDate getProjectedCompletedDate() {
        return projectedCompletedDate;
    }

    public void setProjectedCompletedDate(LocalDate projectedCompletedDate) {
        this.projectedCompletedDate = projectedCompletedDate;
    }

    public double getBudgetAtCompletion() {
        return budgetAtCompletion;
    }

    public void setBudgetAtCompletion(double budgetAtCompletion) {
        this.budgetAtCompletion = budgetAtCompletion;
    }

    public List<TeamMember> getTeamMemberList() {
        return teamMemberList;
    }

    public void setTeamMemberList(List<TeamMember> teamMemberList) {
        this.teamMemberList = teamMemberList;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Risk> getRiskList() {
        return riskList;
    }

    public void setRiskList(List<Risk> riskList) {
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
        long timeElapsed = ChronoUnit.DAYS.between(this.actualStartDate,date);
        long projectDuration = ChronoUnit.DAYS.between(this.actualStartDate,this.projectedCompletedDate);

        double percentageOfProjectCompleted = ((double)timeElapsed/(double)projectDuration);

        return (percentageOfProjectCompleted*this.budgetAtCompletion);
    }


    //should be private later on.... -Karl
    public double calculateAC(LocalDate date){
        double sumOfCostByHours = 0.0;

        for (TeamMember teamMember : this.teamMemberList){
            for (Task task: this.taskList){
                sumOfCostByHours += task.progressByHour(date,teamMember.getTeamMemberId())*teamMember.getSalaryPerHour();
            }
        }
        return sumOfCostByHours;
    }

    public List<String> assignedTasksByMember(int teamMemberId){
        return taskList.stream()
                .filter(task -> task.getTaskMembers().containsKey(teamMemberId))
                .sorted(Comparator.comparing(Task::getName))
                .map(Task::getName)
                .collect(Collectors.toList());
        //string building here instead and use String as type?
    }
    public List<Risk> retrieveRisks(){
        return riskList.stream()
                .sorted(Comparator.comparing(Risk::getRiskName))
                .collect(Collectors.toList());
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
