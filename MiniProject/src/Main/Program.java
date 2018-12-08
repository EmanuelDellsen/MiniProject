package Main;

import Classes.Project;
import Classes.Task;
import Classes.TaskMember;
import Classes.TeamMember;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        giveMemberInformation(listOfProjects);
        giveTaskInformation(listOfProjects);
    }

    public void giveMemberInformation(List<Project> projects) {
        for (Project p : projects) {
            for (TeamMember m : p.getTeamMemberList()) {
                double hoursWorkedOnAllTasks = 0;
                for (Task t : p.getTaskList()) {
                    for (TaskMember tm : t.getCorrectWayToHandleThis()) {
                        if (tm.getId() == (m.getTeamMemberId())) {
                            hoursWorkedOnAllTasks = hoursWorkedOnAllTasks + tm.getHoursWorked();
                        }
                    }
                }
                System.out.println("Team Member - " + m.getTeamMemberName() + " has worked " + hoursWorkedOnAllTasks + " hours on all taks");
            }
        }
    }

    public void giveTaskInformation(List<Project> projects) {
        for (Project p : projects) {
            for (Task t : p.getTaskList()) {
                for (TaskMember tm : t.getCorrectWayToHandleThis()) {
                    for (TeamMember m : p.getTeamMemberList()) {
                        if (tm.getId() == m.getTeamMemberId()) {
                            System.out.println("Team Member - " + m.getTeamMemberName() + " has worked " + tm.getHoursWorked() + " on task" + t.getName());
                        }
                    }
                }
            }
        }
    }


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
