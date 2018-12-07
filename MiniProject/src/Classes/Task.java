package Classes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;


public class Task {

    private int taskId;
    private String taskName;
    private String description;

    private Map<Integer, Double> taskMembers;

    private LocalDate actualStartDate;
    private LocalDate projectedCompletedDate;
    private LocalDate actualCompletedDate;

    public Task(int taskId, String taskName, String description,
                LocalDate actualStartDate, LocalDate projectedCompletedDate,
                LocalDate actualCompletedDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.description = description;
        this.actualStartDate = actualStartDate;
        this.projectedCompletedDate = projectedCompletedDate;
        this.actualCompletedDate = actualCompletedDate;
        this.taskMembers = new HashMap<>();
    }

    public String toString() {
        return  "Name: " + taskName + "\n" +
                "Description: " + description + "\n" +
                "Start date: " + actualStartDate + "\n" +
                "Projected completed date: " + projectedCompletedDate + "\n" +
                // should call retrieve taskMembers ", TaskMembers="+taskMembers+
                '}';
    }

    public long getTaskLength(){
        return ChronoUnit.DAYS.between(this.actualStartDate,this.projectedCompletedDate);
    }

    public boolean taskIsComplete(LocalDate date){
        return this.actualCompletedDate.isBefore(date);
    }

    public double progressInHours(LocalDate date, int teamMemberId){
        long daysFromStartToDate = ChronoUnit.DAYS.between(this.actualStartDate,date);
        double progressInPercent = (double)daysFromStartToDate/(double) getTaskLength();

        return (sumHoursWorkedByMember(teamMemberId)*progressInPercent);
    }

    public double sumHoursWorkedByMember(int teamMemberId){
        return taskMembers.values()
                .stream()
                .filter(a->taskMembers.containsKey(teamMemberId))
                .mapToDouble(i->i)
                .sum();
    }

    public Map<Integer, Double> getTaskMembers() {
        return taskMembers;
    }

    public void setTaskMembers(Map<Integer, Double> taskMembers) {
        this.taskMembers = taskMembers;
    }

    public int getId() {
        return taskId;
    }

    public void setId(int id) {
        this.taskId = taskId;
    }

    public String getName(){
        return taskName;
    }

    public void setName(String name) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

