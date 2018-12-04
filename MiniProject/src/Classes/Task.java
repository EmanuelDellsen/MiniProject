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

    public Task(int taskId, String taskName, String description,
                LocalDate actualStartDate, LocalDate projectedCompletedDate,
                LocalDate actualCompletedDate, Map<Integer, Double> taskMembers) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.description = description;
        this.taskMembers = taskMembers;
        this.actualStartDate = actualStartDate;
        this.projectedCompletedDate = projectedCompletedDate;
        this.actualCompletedDate = actualCompletedDate;
        this.taskMembers = taskMembers;
    }

    public String toString() {
        return  "Name: " + taskName + "\n" +
                "Description: " + description + "\n" +
                "Start date: " + actualStartDate + "\n" +
                "Projected completed date: " + projectedCompletedDate + "\n" +
                "Description: " + description + "\n" +
                // should call retrieve taskMembers ", TaskMembers="+taskMembers+
                '}';
    }

    public long getTaskValue(){
        return ChronoUnit.DAYS.between(this.actualStartDate,this.projectedCompletedDate);
    }

    public boolean taskIsComplete(LocalDate date){
        return this.actualCompletedDate.isBefore(date);
    }

        /*
    public boolean taskIsComplete(){
        String actualCompletedDate = this.actualCompletedDate.replace("-","");
        String currentDate = LocalDate.now().toString().replace("-","");

        int actualCompletedDateAsInteger = Integer.parseInt(actualCompletedDate);
        int actualCurrentDateAsInteger = Integer.parseInt(currentDate);

        return actualCurrentDateAsInteger >= actualCompletedDateAsInteger;
    }
*/

    public int getId() {
        return taskId;
    }

    public void setId(int id) {
        this.taskId = taskId;
    }

    public String getName() {
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

