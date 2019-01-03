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
    /*
    This method is to calculate (EV = eared value), the percentageOfCompletedTasks method which
    (receives the LocateDate date as an argument), is multiplied with the budget of the project.
     */
    public double calculateEV(LocalDate date){
        return (this.percentageOfCompletedTasks(date)*this.budgetAtCompletion);
    }
    /*this method is to:calculate (SV = Scheduled Variance), the difference
     between earned value (EV) and planned value (PV) is calculated. The
     method receives a LocalDate date and passes it to both calculatePV() and
    calculatePV() methods.
     */
    public double calculateSV(LocalDate date){
        return (this.calculateEV(date)-this.calculatePV(date));
    }
    /* This method is to calculate (CV = cost variance) up to the LocalDate date,
    the difference between earned value from calculateEV()
     and actual cost from calculateAC() methods.
     */
    public double calculateCV(LocalDate date){
        return this.calculateEV(date)-this.calculateAC(date);
    }
     /*
      This method returns a list of all the tasks done by team Member by
      calling the getTaskMembers() method of type map.
     */
    public List<Task> returnTasksByTeamMember(int teamMemberId){
        return taskList.stream()
                .filter(task -> task.getTaskMembers().containsKey(teamMemberId))
                .collect(Collectors.toList());
    }
    /*
    This method returns the sum of hours spent by each team
    member by calling returnHoursByMember() method from the
    Task class.
     */
    public double returnHoursByTeamMember(int teamMemberId){
        double sum = 0.0;

        for(Task task: taskList){
            sum += task.returnHoursByMember(teamMemberId);
        }
        return sum;
    }
    /*
    This method sums of the total hours spent on the project, by summing
    the number of hours worked on a task from the returnHoursInTask() method
    in the Task class.
     */
    public double returnAllHoursWorked(){
        return this.taskList.stream()
                .mapToDouble(Task::returnHoursInTask)
                .sum();
    }
    /*
    This method returns the number of days between the project start date and
    a task's actual start date
     */
    public long returnDaysBetweenProjectAndTask(Task task){
        return ChronoUnit.DAYS.between(this.actualStartDate,task.getActualStartDate());
    }
    /*
    This method that list the team members in the project
    by considering the team member ID into consideration.
     */
    public TeamMember returnTeamMember(int teamMemberId){
        return this.teamMemberList.stream()
                .filter(teamMember -> teamMemberId == teamMember.getTeamMemberId())
                .findAny()
                .orElse(null);
    }
    /*
    This returns a list of the risks sorted by the
    the risk value
     */
    public List<Risk> returnRisks(){
        return this.riskList.stream()
                .sorted(Comparator.comparing(Risk::returnRisk))
                .collect(Collectors.toList());
    }
    /*
    This method returns a list of team members
    sorted by the number of total hours each member
    worked on the tasks
     */

    public List<TeamMember> returnTeamMembersSortedByHours(){

        List<TeamMember> tempList = this.teamMemberList.stream()
                .sorted(Comparator.comparing(teamMember -> returnHoursByTeamMember(teamMember.getTeamMemberId())))
                .collect(Collectors.toList());

        Collections.reverse(tempList);
        return tempList;
    }
    /*
    This method lists a the dates per an interval of two weeks
    and skips the start date.
     */

    public List<LocalDate> returnDatesPerInterval(int skipNumOfWeeks){
        return this.actualStartDate.datesUntil(this.projectedCompletedDate,Period.ofWeeks(2))
                .skip(skipNumOfWeeks)
                .collect(Collectors.toList());
    }
    /*
    This method lists the tasks in the right chronological order
    in the project.
     */
    public List<Task> returnTasksSortedByStartDate(){
        return this.taskList.stream()
                .sorted(Comparator.comparing(Task::getActualStartDate))
                .collect(Collectors.toList());
    }
    /*
    This method is to return the percentage of the completed Tasks which is
    used later in the calculateEV() method. the valueOfCompletedTasks is the
    sum of each completed task duration up to the LocalDate date
    and valueOfAllTasks is the sum of the duration of all the tasks regardless of
    whether a task is completed or not but only up to the LocalDate date.
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
    This method is to calculate (PV = planned value), which is found by
    multiplying the percentageOfProjectCompleted by the project's budget,
    the method calculates PV up to the LocalDate date
    */
    private double calculatePV(LocalDate date){
        long timeElapsed = ChronoUnit.DAYS.between(this.actualStartDate,date);
        long projectDuration = ChronoUnit.DAYS.between(this.actualStartDate,this.projectedCompletedDate);

        double percentageOfProjectCompleted = ((double)timeElapsed/(double)projectDuration);

        return (percentageOfProjectCompleted*this.budgetAtCompletion);
    }
    /*
    This method calculates (AC = actual cost) by returning the sumofProgress
    which is the sum of the progress of each task up to the LocalDate date multiplied
    by the the total of team member salary  spent up to the LocalDate date which is calculated based on
    how many hours spent by each member multiplied by each team member's salary.
     */
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

    public void setRiskList(List<Risk> riskList) {
        this.riskList = riskList;
    }

    public String getProjectName() {
        return projectName;
    }

    public LocalDate getActualStartDate() {
        return actualStartDate;
    }

    public LocalDate getProjectedCompletedDate() {
        return projectedCompletedDate;
    }

    public int getProjectId() {
        return projectId;
    }

    @Override
    public String toString() {
        return this.getProjectId()+": "+this.getProjectName();
    }

    public List<Risk> getRiskList() {
        return riskList;
    }

    public double getBudgetAtCompletion() {
        return budgetAtCompletion;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setActualStartDate(LocalDate actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    public void setProjectedCompletedDate(LocalDate projectedCompletedDate) {
        this.projectedCompletedDate = projectedCompletedDate;
    }

    public void setBudgetAtCompletion(double budgetAtCompletion) {
        this.budgetAtCompletion = budgetAtCompletion;
    }
}