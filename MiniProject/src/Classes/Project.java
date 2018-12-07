package Classes;

import java.time.LocalDate;
import java.time.Period;
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

    private ArrayList<TeamMember> teamMemberList;
    private ArrayList<Task> taskList;
    private ArrayList<Risk> riskList;

    public Project(int projectId, String projectName, LocalDate actualStartDate,
                   LocalDate projectedCompletedDate, double budgetAtCompletion) {

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

    public int calculateSV(LocalDate date){
        return (int)(this.calculateEV(date)-calculatePV(date));
    }

    public double calculateCV(LocalDate date){
        return this.calculateEV(date)-this.calculateAC(date);
    }

    // should be private later on.... - Karl
    public double percentageOfCompletedTasks(LocalDate date){
        double valueOfCompletedTasks = 0.0;
        double valueOfAllTasks = 0.0;

        for(Task task : this.taskList){
            valueOfAllTasks += task.getTaskLength();
            if(task.taskIsComplete(date)){
                valueOfCompletedTasks += task.getTaskLength();
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

    //This returns a list of the name of the tasks that a teamMember is assigned to sorted by name
    public List<String> assignedTasksByMember(int teamMemberId){
        return taskList.stream()
                .filter(task -> task.getTaskMembers().containsKey(teamMemberId))
                .sorted(Comparator.comparing(Task::getName))
                .map(Task::getName)
                .collect(Collectors.toList());
        //string building here instead and use String as type?
    }
    //This returns a list of objects of type Risk with the format as the to.String in Risk
    public List<Risk> retrieveRisks(){
        return this.riskList.stream()
                .sorted(Comparator.comparing(Risk::getRiskName))
                .collect(Collectors.toList());
    }

    /*
    public List<String> workDoneByAll(){
    }
*/

    public List<LocalDate> returnProjectIntervalDates(){
        return this.actualStartDate.datesUntil(this.projectedCompletedDate,Period.ofWeeks(2))
                .collect(Collectors.toList());
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
