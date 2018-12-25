package Classes;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;
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

    public Project(){

    } //TODO: this method is unnecessary

    public int getProjectId() {
        return projectId;
    }
    /*
    to calculate (EV = eared value), the percentage of the
    completed tasks is multiplied with the budget of the project at completion.
     */

    public double calculateEV(LocalDate date){
        return (this.percentageOfCompletedTasks(date)*this.budgetAtCompletion);
    }
    /*
    to calculate (SV = Scheduled Variance), the difference between
    earned value (EV) and planned value (PV) is calculated.
     */

    public double calculateSV(LocalDate date){
        return (this.calculateEV(date)-this.calculatePV(date));
    }
    /*
    to calculate (CV = cost variance), the difference between
    earned value (EV) and actual value (AC) is calculated.
     */

    public double calculateCV(LocalDate date){
        return this.calculateEV(date)-this.calculateAC(date);
    }
    /*
     Primitive stream that collects the tasks done by a team member
     by calling the getTaskmembers() method of type map from Task Class
     which contains the task members.
     */


    public List<Task> returnTasksByTeamMember(int teamMemberId){
        return taskList.stream()
                .filter(task -> task.getTaskMembers().containsKey(teamMemberId))
                .collect(Collectors.toList());
    }
    /*
    stream that operates the sum of hours spent by each team
    member by calling returnHoursByMember() method from the
    Task class in for each.
     */

    public double returnHoursByTeamMember(int teamMemberId){
        double sum = 0.0;

        for(Task task: taskList){
            sum += task.returnHoursByMember(teamMemberId);
        }
        return sum;
    }
    /*
    stream that operates the sum of the total hours spent on the project
     */

    public double returnAllHoursWorked(){
        return this.taskList.stream()
                .mapToDouble(Task::returnHoursInTask)
                .sum();
    }
    //TODO: why do we need  that method
    public long returnDaysBetweenProjectAndTask(Task task){
        return ChronoUnit.DAYS.between(this.actualStartDate,task.getActualStartDate());
    }
    /*
    to return the number of days the project lasts
     */

    public long returnNumberOfWeeksInProject(){
        return ChronoUnit.WEEKS.between(this.actualStartDate,this.projectedCompletedDate);
    }
    //TODO we might not really need this method, I will introduce a retrieve teamMember method instead

    public TeamMember returnTeamMember(int teamMemberId){
        return this.teamMemberList.stream()
                .filter(teamMember -> teamMemberId == teamMember.getTeamMemberId())
                .findAny()
                .orElse(null);
    }
    /*
    stream that collects the list of risks and
    sorts them in an alphabetical order
     */

    public List<Risk> returnRisks(){
        return this.riskList.stream()
                .sorted(Comparator.comparing(Risk::returnRisk))
                .collect(Collectors.toList());
    }
    /*
    this method creates a "teamList" or a temporary list which
    copares the number of hours spent by each team Member and
    sorts the members from those who spent the most hours to
    the least number of hours
     */

    public List<TeamMember> returnTeamMembersSortedByHours(){

        List<TeamMember> tempList = this.teamMemberList.stream()
                .sorted(Comparator.comparing(teamMember -> returnHoursByTeamMember(teamMember.getTeamMemberId())))
                .collect(Collectors.toList());

        Collections.reverse(tempList);
        return tempList;
    }


    public List<LocalDate> returnDatesPerInterval(int skipNumOfWeeks){
        return this.actualStartDate.datesUntil(this.projectedCompletedDate,Period.ofWeeks(2))
                .skip(skipNumOfWeeks)
                .collect(Collectors.toList());
    }
    /*
    this method return the percentage of the completed task,
    that percentage is used in the above method to calculate the
    earned value
     */

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
    /*
    this method return the planned value for the project
     */

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

    public List<Task> returnTasksSortedByStartDate(){
        return this.taskList.stream()
                .sorted(Comparator.comparing(Task::getActualStartDate))
                .collect(Collectors.toList());
    }




    public List<Task> getTaskList() {
        return this.taskList;
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
        return this.getProjectId()+": "+this.getProjectName();
    }
}