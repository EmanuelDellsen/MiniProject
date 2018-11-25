import java.util.ArrayList;

public class ProjTask {


    private int id;
    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private boolean completedTask;

    public ArrayList<TaskMate> taskMatesList;


    public ProjTask(int id, String name, String description, String startDate, String endDate, boolean completedTask) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.completedTask = completedTask;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isCompletedTask() {
        return completedTask;
    }

    public void setCompletedTask(boolean completedTask) {
        this.completedTask = completedTask;
    }

    public  ArrayList<TaskMate> getTaskMatesList() {
        return taskMatesList ;
    }

    public void setTaskMatesList(ArrayList<TaskMate> taskMatesList) {
        this.taskMatesList = taskMatesList;
    }

}


