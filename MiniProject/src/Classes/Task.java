package Classes;

import java.util.ArrayList;

public class Task {


    private int id;
    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private boolean taskIsComplete;

    public ArrayList<TaskMember> taskMatesList;


    public Task(int id, String name, String description, String startDate, String endDate, boolean taskIsComplete) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.taskIsComplete = taskIsComplete;

    }


    public String toString() {
        return "id=" + id + ", name= " + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", taskIsComplete=" + taskIsComplete +
                '}';
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

    public boolean isTaskIsComplete() {
        return taskIsComplete;
    }

    public void setTaskIsComplete(boolean taskIsComplete) {
        this.taskIsComplete = taskIsComplete;
    }

    public  ArrayList<TaskMember> getTaskMatesList() {
        return taskMatesList ;
    }

    public void setTaskMatesList(ArrayList<TaskMember> taskMatesList) {
        this.taskMatesList = taskMatesList;
    }

}


