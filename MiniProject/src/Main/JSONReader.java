package Main;

import Classes.Project;
import Classes.Task;
import Classes.TaskMember;
import Classes.TeamMember;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.*;

public class JSONReader {


    public static void main(String[] args) {

        JSONParser parser = new JSONParser();

        try {
            Object object = parser.parse(new FileReader("/Users/emanueldellsen/Desktop/MiniProject1/MiniProject/resources/Classes.Project.json"));

            JSONObject jsonObject = (JSONObject) object;

            Project newProject = new Project((String) jsonObject.get("name"), (String) jsonObject.get("startDate"),
                    (String) jsonObject.get("endDate"), Double.valueOf((String) jsonObject.get("budgetAtCompletion")));

            newProject.setTeamMemberList(new ArrayList<>());

            newProject.setTaskList(new ArrayList<>());

            newProject.setRiskList(new ArrayList<>());

            JSONArray teammates = (JSONArray) jsonObject.get("teamMembers");

            Iterator<Map> teamMateIterator = teammates.iterator();

            while (teamMateIterator.hasNext()) {

                Map member = teamMateIterator.next();

                String name = (String) member.get("name");
                int id = Integer.valueOf((String) member.get("id"));
                String salary = (String) member.get("salaryPerHour");

                TeamMember newTeamMember = new TeamMember(name, id, salary);

                newProject.getTeamMemberList().add(newTeamMember);

                System.out.println(newTeamMember);

            }

            JSONArray tasks = (JSONArray) jsonObject.get("tasks");

            Iterator<Map> taskIterator = tasks.iterator();

            while (taskIterator.hasNext()) {

                Map taskMap = taskIterator.next();

                int id = Integer.valueOf((String) taskMap.get("id"));
                String name = (String) taskMap.get("name");
                String description = (String) taskMap.get("description");
                String startDate = (String) taskMap.get("startDate");
                String endDate = (String) taskMap.get("endDate");
                boolean completedTask = Boolean.valueOf((String) taskMap.get("completedTask"));

                Task newTask = new Task(id, name, description, startDate, endDate, completedTask);

                newProject.getTaskList().add(newTask);

                newTask.setTaskMatesList(new ArrayList<>());

                System.out.println(newTask);

                // Map<String, String> mapObject = new HashMap<> ();

            }


                JSONArray taskmate = (JSONArray) jsonObject.get("taskMember");

                Iterator<Map> taskMateIterator = taskmate.iterator();

                while (taskMateIterator.hasNext()) {

                    Map taskMate = taskMateIterator.next();

                    int id = Integer.valueOf((String) taskMate.get("id"));
                    Double hoursWorked = Double.valueOf((String) taskMate.get("hoursWorked"));

                    TaskMember taskMember1 = new TaskMember(id, hoursWorked);

                    Task.getTaskMatesList().add(taskMember1);

                    System.out.println(taskMember1);

                }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}