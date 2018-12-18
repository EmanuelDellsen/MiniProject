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

    public List<Task> returnTasksByTeamMember(int teamMemberId){
        return taskList.stream()
                .filter(task -> task.getTaskMembers().containsKey(teamMemberId))
                .collect(Collectors.toList());
    }

    public double returnHoursByTeamMember(int teamMemberId){
        double sum = 0.0;

        for(Task task: taskList){
            sum += task.returnHoursByMember(teamMemberId);
        }
        return sum;
    }

    public Long returnWeeksBetweenTasks(Task task){
        return ChronoUnit.WEEKS.between(this.actualStartDate,task.getActualStartDate());
    }

    public Long returnNumberOfWeeksInProject(){
        return ChronoUnit.WEEKS.between(this.actualStartDate,this.projectedCompletedDate);
    }

    public TeamMember returnTeamMember(int teamMemberId){
        return this.teamMemberList.stream()
                .filter(teamMember -> teamMemberId == teamMember.getTeamMemberId())
                .findAny()
                .orElse(null);
    }

    public List<Risk> returnRisks(){
        return this.riskList.stream()
                .sorted(Comparator.comparing(Risk::getProbability))
                .collect(Collectors.toList());
    }

    public List<LocalDate> returnDatesPerInterval(){
        return this.actualStartDate.datesUntil(this.projectedCompletedDate,Period.ofWeeks(2))
                .skip(1)
                .collect(Collectors.toList());
    }

    private double percentageOfCompletedTasks(LocalDate date){
        double valueOfCompletedTasks = 0.0;
        double valueOfAllTasks = 0.0;

        for(Task task : this.taskList){
            valueOfAllTasks += task.returnTaskDurationInDays();
            if(task.taskIsComplete(date)){
                valueOfCompletedTasks += task.returnTaskDurationInDays();
            }
        }
        return (valueOfCompletedTasks/valueOfAllTasks);
    }

    private double calculatePV(LocalDate date){
        long timeElapsed = ChronoUnit.DAYS.between(this.actualStartDate,date);
        long projectDuration = ChronoUnit.DAYS.between(this.actualStartDate,this.projectedCompletedDate);

        double percentageOfProjectCompleted = ((double)timeElapsed/(double)projectDuration);

        return (percentageOfProjectCompleted*this.budgetAtCompletion);
    }

    private double calculateAC(LocalDate date){
        double sumOfProgress = 0.0;

        for (TeamMember teamMember: this.teamMemberList){
            for (Task task: returnTasksByTeamMember(teamMember.getTeamMemberId())){
                sumOfProgress += task.taskProgress(date)*
                        (task.returnHoursByMember(teamMember.getTeamMemberId())*teamMember.getSalaryPerHour());
            }
        }

        return sumOfProgress;
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

    public double getBudgetAtCompletion() {
        return budgetAtCompletion;
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