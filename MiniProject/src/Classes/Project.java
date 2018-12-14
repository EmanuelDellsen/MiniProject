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


    public double calculateEV(LocalDate date){
        return (this.percentageOfCompletedTasks(date)*this.budgetAtCompletion);
    }

    public double calculateSV(LocalDate date){
        return (this.calculateEV(date)-this.calculatePV(date));
    }

    public double calculateCV(LocalDate date){
        return this.calculateEV(date)-this.calculateAC(date);
    }

    // should be private later on.... - Karl
    public double percentageOfCompletedTasks(LocalDate date){
        double valueOfCompletedTasks = 0.0;
        double valueOfAllTasks = 0.0;

        for(Task task : this.taskList){
            valueOfAllTasks += task.getTaskDuration();
            if(task.taskIsComplete(date)){
                valueOfCompletedTasks += task.getTaskDuration();
            }
        }
        return (valueOfCompletedTasks/valueOfAllTasks);
    }

    //should be private later on.... -Karl
    public double calculatePV(LocalDate date){
        long timeElapsed = ChronoUnit.DAYS.between(this.actualStartDate,date);
        System.out.println(timeElapsed);
        long projectDuration = ChronoUnit.DAYS.between(this.actualStartDate,this.projectedCompletedDate);
        System.out.println(projectDuration);

        double percentageOfProjectCompleted = ((double)timeElapsed/(double)projectDuration);

        return (percentageOfProjectCompleted*this.budgetAtCompletion);
    }

    //should be private later on.... -Karl
    public double calculateAC(LocalDate date){
        double sumOfProgress = 0.0;

        for (TeamMember teamMember: this.teamMemberList){
            for (Task task: assignedTasksByMember(teamMember.getTeamMemberId())){
                //System.out.println("*********");
                //System.out.println(task.taskProgress(date));
                //System.out.println(task.returnHoursByMember(teamMember.getTeamMemberId()));
                //System.out.println(teamMember.getSalaryPerHour());

                sumOfProgress += task.taskProgress(date)*
                        (task.returnHoursByMember(teamMember.getTeamMemberId())*teamMember.getSalaryPerHour());

                //System.out.println(sumOfProgress);
                //System.out.println("*********");

            }
        }

        return sumOfProgress;
    }

    //This returns a list of the name ofM the tasks that a teamMember is assigned to sorted by name
    public List<Task> assignedTasksByMember(int teamMemberId){
        return taskList.stream()
                .filter(task -> task.getTaskMembers().containsKey(teamMemberId))
                .collect(Collectors.toList());
    }

    public double allHoursByMember(int teamMemberId){
        double sum = 0.0;

        for(Task task: taskList){
            sum += task.returnHoursByMember(teamMemberId);
        }
        return sum;
    }


    //This returns a list of objects of type Risk with the format as the to.String in Risk
    public List<Risk> retrieveRisks(){
        return this.riskList.stream()
                .sorted(Comparator.comparing(Risk::getProbability))
                .collect(Collectors.toList());
    }

    //Returns a list of the dates of every 2 weeks skipping first date
    public List<LocalDate> returnProjectIntervalDates(){
        return this.actualStartDate.datesUntil(this.projectedCompletedDate,Period.ofWeeks(2))
                .skip(1)
                .collect(Collectors.toList());
    }

    public Long returnNumberOfWeeksInProject(){
        return ChronoUnit.WEEKS.between(this.actualStartDate,this.projectedCompletedDate);
    }

    public TeamMember retrieveTeamMember(int teamMemberId){
        return this.teamMemberList.stream()
                .filter(teamMember -> teamMemberId == teamMember.getTeamMemberId())
                .findAny()
                .orElse(null);
    }

    public Long distanceBetweenTask(Task task){
        return ChronoUnit.DAYS.between(this.actualStartDate,task.getActualStartDate());
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<TeamMember> getTeamMemberList() {
        return teamMemberList;
    }

    public void setTeamMemberList(List<TeamMember> teamMemberList) {
        this.teamMemberList = teamMemberList;
    }

    public List<Risk> getRiskList() {
        return riskList;
    }

    public void setRiskList(List<Risk> riskList) {
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