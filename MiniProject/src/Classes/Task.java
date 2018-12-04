package Classes;

import java.util.Map;

public class Task {

    private int taskId;
    private String taskName;
    private String description;

    // private boolean taskIsComplete;

    private Map<Integer, Double> taskMembers;

    private String actualStartDate;
    private String projectedCompletedDate;
    private String actualCompletedDate;

    //private Map<TaskMember, hoursWorked> taskMatesList; this needs to go to the constructor

    public Task(int taskId, String taskName, String description,
                String actualStartDate, String projectedCompletedDate,
                String actualCompletedDate, Map<Integer, Double> taskMembers) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.description = description;

        // this.taskIsComplete = taskIsComplete;
        this.taskMembers = taskMembers;
        this.actualStartDate = actualStartDate;
        this.projectedCompletedDate = projectedCompletedDate;
        this.actualCompletedDate = actualCompletedDate;
        this.taskMembers = taskMembers;
    }

    public String toString() {
        return "id=" + taskId + ", name= " + taskName + '\'' +
                ", description='" + description + '\'' +
                ", start date='" + actualStartDate + '\'' +
                ", project completed date='" + projectedCompletedDate + '\'' +
                ", actual completed date='" + actualCompletedDate +
                '}';
    }

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

    public String getStartDate() {
        return actualStartDate;
    }

    public void setStartDate(String startDate) {
        this.actualStartDate = actualStartDate;
    }

    public String getProjectedCompletedDate() {
        return projectedCompletedDate;
    }

    public void setProjectedCompletedDate(String projectedCompletedDate) {
        this.projectedCompletedDate = projectedCompletedDate;
    }

    public String getActualCompletedDate() {
        return actualCompletedDate;
    }

    public void setActualCompletedDate(String actualCompletedDate) {
        this.actualCompletedDate = actualCompletedDate;
    }

}

