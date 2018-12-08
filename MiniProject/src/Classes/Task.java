package Classes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;


public class Task {

    private int taskId;
    private String taskName;
    private String description;

    private Map<Integer, Double> taskMembers;

    private List<TaskMember> correctWayToHandleThis;

    private LocalDate actualStartDate;
    private LocalDate projectedCompletedDate;
    private LocalDate actualCompletedDate;

    public Task(int taskId, String taskName, String description,
                LocalDate actualStartDate, LocalDate projectedCompletedDate,
                LocalDate actualCompletedDate, Map taskMembers, List<TaskMember> correctWayToHandleThis) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.description = description;
        this.actualStartDate = actualStartDate;
        this.projectedCompletedDate = projectedCompletedDate;
        this.actualCompletedDate = actualCompletedDate;
        this.taskMembers = taskMembers;
        this.correctWayToHandleThis = correctWayToHandleThis;
    }

    public String toString() {
        return  "Name: " + taskName + "\n" +
                "Description: " + description + "\n" +
                "Start date: " + actualStartDate + "\n" +
                "Projected completed date: " + projectedCompletedDate + "\n" +
                // should call retrieve taskMembers ", TaskMembers="+taskMembers+
                taskMembers + "\n" +
                '}';
    }

    public long getTaskValue(){
        return ChronoUnit.DAYS.between(this.actualStartDate,this.projectedCompletedDate);
    }

    public boolean taskIsComplete(LocalDate date){
        return this.actualCompletedDate.isBefore(date);
    }

    public double progressByHour(LocalDate date,int teamMemberId){
        long daysFromStartToDate = ChronoUnit.DAYS.between(this.actualStartDate,date);
        double progressInPercent = (double)daysFromStartToDate/(double)getTaskValue();

        return (sumHoursWorkedByMember(teamMemberId)*progressInPercent);
    }

    public double sumHoursWorkedByMember(int teamMemberId){
        return taskMembers.values()
                .stream()
                .filter(a->taskMembers.containsKey(teamMemberId))
                .mapToDouble(i-> (double) i)
                .sum();
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

    public List<TaskMember> getCorrectWayToHandleThis() {
        return correctWayToHandleThis;
    }

    public void setCorrectWayToHandleThis(List<TaskMember> correctWayToHandleThis) {
        this.correctWayToHandleThis = correctWayToHandleThis;
    }
}

