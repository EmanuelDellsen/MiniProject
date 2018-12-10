package Main;

import Classes.Project;
import Classes.Task;
import Classes.TaskMember;
import Classes.TeamMember;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Program {

    private List<Project> listOfProjects = new ArrayList<>();

    JSONReader myJSONReader = new JSONReader();


    public void run() throws IOException, ParseException {
        setUpProgram();
        startProgram();
    }

    public void setUpProgram() throws IOException, ParseException {

        myJSONReader.methodToCallFromProgram();


        listOfProjects = myJSONReader.createProjects();

        System.out.println(listOfProjects.get(0).getTaskList().get(0).sumHoursWorkedByMember(1));

        /*giveMemberInformation(listOfProjects);
        giveTaskInformation(listOfProjects);*/
    }

 /*   public void workDoneOnAllTasks(List<Project> projects) {
        for (Project p : projects) {
            for (TeamMember m : p.getTeamMemberList()) {
                double hoursWorkedOnAllTasks = 0;
                for (Task t : p.getTaskList()) {
                    for (TaskMember tm : t.getListOfTaskMembers()) {
                        if (tm.getId() == (m.getTeamMemberId())) {
                            hoursWorkedOnAllTasks = hoursWorkedOnAllTasks + tm.getHoursWorked();
                        }
                    }
                }
                System.out.println("Team Member - " + m.getTeamMemberName() + " has worked " + hoursWorkedOnAllTasks + " hours on all task");
            }
        }
    }

    public void hoursOnEachTask(List<Project> projects) {
        for (Project p : projects) {
            for (Task t : p.getTaskList()) {
                for (TaskMember tm : t.getListOfTaskMembers()) {
                    for (TeamMember m : p.getTeamMemberList()) {
                        if (tm.getId() == m.getTeamMemberId()) {
                            System.out.println("Team Member - " + m.getTeamMemberName() + " has worked " + tm.getHoursWorked() + " on task" + t.getName());
                        }
                    }
                }
            }
        }
    }*/


    public void startProgram(){

    }

    public List<Project> getListOfProjects() {
        return listOfProjects;
    }

    public void setListOfProjects(List<Project> listOfProjects) {
        this.listOfProjects = listOfProjects;
    }

    public JSONReader getMyJSONReader() {
        return myJSONReader;
    }

    public void setMyJSONReader(JSONReader myJSONReader) {
        this.myJSONReader = myJSONReader;
    }
}
