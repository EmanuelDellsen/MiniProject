package Classes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;

public class Task {

    private int taskId;
    private String taskName;
    private String description;

    private Map<Integer, Double> taskMembers;

    private LocalDate actualStartDate;
    private LocalDate projectedCompletedDate;
    private LocalDate actualCompletedDate;

    private final static double COMPLETE = 1.0;
    private final static double TASK_NOT_STARTED = 0.0;

    public Task(int taskId, String taskName, String description,
                LocalDate actualStartDate, LocalDate projectedCompletedDate,
                LocalDate actualCompletedDate, Map<Integer, Double> taskMembers){
        this.taskId = taskId;
        this.taskName = taskName;
        this.description = description;
        this.actualStartDate = actualStartDate;
        this.projectedCompletedDate = projectedCompletedDate;
        this.actualCompletedDate = actualCompletedDate;
        this.taskMembers = taskMembers;

    }

    long returnTaskDurationInDays(){

        // returns the amount of days depending on if the task has been completed before or after the projected completed date
        if (this.actualCompletedDate.isAfter(projectedCompletedDate)){
            return ChronoUnit.DAYS.between(this.actualStartDate,this.actualCompletedDate);
        } else {
            return ChronoUnit.DAYS.between(this.actualStartDate,this.projectedCompletedDate);
        }

    }

    boolean taskIsComplete(LocalDate date){
        return this.actualCompletedDate.isBefore(date);
    }

    double taskProgress(LocalDate date){
        //returns the percentage of the task that is complete
        if (taskIsComplete(date)){
            return COMPLETE;
        } else {
            if (date.isBefore(this.actualStartDate)){
                return TASK_NOT_STARTED;
            } else {
                long daysFromStartToDate = ChronoUnit.DAYS.between(this.actualStartDate,date);
                return (double) daysFromStartToDate /(double) returnTaskDurationInDays();
            }
        }
    }

    double returnHoursInTask(){
        //returns all of the hours that has been worked with the task
        return this.taskMembers.values()
                .stream()
                .mapToDouble(i->i)
                .sum();
    }

    public LocalDate getActualStartDate() {
        return actualStartDate;
    }

    public double returnHoursByMember(int teamMemberId){
        //returns the amount of hours by teamMember. if team Member is not found, it returns 0
        return this.taskMembers.getOrDefault(teamMemberId,0.0);

    }

    Map<Integer, Double> getTaskMembers() {
        return taskMembers;
    }

    public String getName(){
        return taskName;
    }

    public String toString() {
        return  "Name: " + taskName + "\n" +
                "Description: " + description + "\n" +
                "Start date: " + actualStartDate + "\n" +
                "Projected completed date: " + projectedCompletedDate + "\n" +
                "Task members"+taskMembers+
                // should call retrieve taskMembers ", TaskMembers="+taskMembers+
                taskMembers + "\n" +
                '}';
    }

    public LocalDate getProjectedCompletedDate() {
        return projectedCompletedDate;
    }

    public void setProjectedCompletedDate(LocalDate projectedCompletedDate) {
        this.projectedCompletedDate = projectedCompletedDate;
    }

    public LocalDate getActualCompletedDate() {
        return actualCompletedDate;
    }

    public void setActualCompletedDate(LocalDate actualCompletedDate) {
        this.actualCompletedDate = actualCompletedDate;
    }
}

