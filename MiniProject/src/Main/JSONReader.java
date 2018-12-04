package Main;

import Classes.Project;
import Classes.Task;
import Classes.TaskMember;
import Classes.TeamMember;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class JSONReader {

    public void methodToCallFromProgram() throws IOException, ParseException {
        createJSONObject();


    }


    public JSONObject createJSONObject() throws FileNotFoundException, ParseException, IOException {
        JSONParser parser = new JSONParser();
        Object object = parser.parse(new FileReader("Project.json"));
        JSONObject jsonObject = (JSONObject) object;
        return jsonObject;
    }

    public Project createProject() throws IOException, ParseException {

        Project newProject = new Project(Integer.valueOf((String) createJSONObject().get("projectId")), (String) createJSONObject().get("projectName"), (String) createJSONObject().get("actualStartDate"),
                (String) createJSONObject().get("projectCompletedDate"), Double.valueOf((String) createJSONObject().get("budgetAtCompletion")));

        newProject.setTeamMemberList(new ArrayList<>());
        newProject.setTaskList(new ArrayList<>());
        newProject.setRiskList(new ArrayList<>());

        return newProject;

    }

    public JSONArray createTeamMembers() throws IOException, ParseException {

        JSONArray teammates = (JSONArray) createJSONObject().get("teamMemberList");

        Iterator teamMateIterator = teammates.iterator();

        while (teamMateIterator.hasNext()) {

            Map member = (Map) teamMateIterator.next();
            int teamMemberId = Integer.valueOf((String) member.get("teamMemberId"));
            String teamMemberName = (String) member.get("teamMemberName");
            double salaryPerHour = Double.valueOf((String) member.get("salaryPerHour"));

            TeamMember newTeamMember = new TeamMember(teamMemberId, teamMemberName, salaryPerHour);
            createProject().getTeamMemberList().add(newTeamMember);

        }

        return teammates;


    }

    public JSONArray createTask() throws IOException, ParseException {

        JSONArray tasks = (JSONArray) createJSONObject().get("taskList");
        Iterator taskIterator = tasks.iterator();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-DD");



        while (taskIterator.hasNext()) {

            Map taskMap = (Map) taskIterator.next();

            int taskId = Integer.valueOf((String) taskMap.get("taskId"));
            String taskName = (String) taskMap.get("taskName");
            String description = (String) taskMap.get("description");
            LocalDate actualStartDate = LocalDate.parse((String) taskMap.get("actualStartDate"), formatter);
            LocalDate projectedCompletedDate = LocalDate.parse((String) taskMap.get("projectedCompletedDate"), formatter);
            LocalDate actualCompletedDate = LocalDate.parse((String) taskMap.get("actualCompletedDate"), formatter);
            // boolean completedTask = Boolean.valueOf((String) taskMap.get("completedTask"));

            Task newTask = new Task(taskId, taskName, description, actualStartDate, projectedCompletedDate, actualCompletedDate);

            createProject().getTaskList().add(newTask);
            newTask.setTeamMembers= new HashMap<Integer, Double>();

        }
        return tasks;

    }

    public JSONArray taskMembers() throws IOException, ParseException {

        JSONArray taskMembers = (JSONArray) createJSONObject().get("taskMembers");
        Iterator taskMemberIterator = taskMembers.iterator();


        while (taskMemberIterator.hasNext()) {

            Map taskMember = (Map) taskMemberIterator.next();

            int id2 = Integer.valueOf((String) taskMember.get("id"));
            double hoursWorked = Double.valueOf((String) taskMember.get("hoursWorked"));

            TaskMember newTaskMember = new TaskMember(id2, hoursWorked);


            System.out.println("Printing ID for taskmember: " + newTaskMember.getId());  //Print only as a reference for us
            System.out.println("Printing hoursWorked for ID " + newTaskMember.getId() + " : " + newTaskMember.getHoursWorked()); //Print only as a reference for us

            // Loop to put key and value into the HasMap, can possibly be an enhanced for-loop later on
            for (int i = 0; i < taskMembers.size(); i++) {
                taskMember.put(newTaskMember.getId(), newTaskMember.getHoursWorked());
            }
        }

        // Print for our reference, to see that the HashMap workes
        for (Object key : taskMembers.keySet()) {
            System.out.println("Pritning from HashMap:  ID " + key + " - Hours Worked : " + taskMembers.get(key));
        }

        Task newTask = new Task(id, name, description, actualStartDate, projectedCompletedDate, actualCompletedDate, taskMembers);
        newProject.getTaskList().add(newTask);

        // newTask.setTaskMatesList(new ArrayList<>());
        //  System.out.println(newTask);
    }
}



    public static void main(String[] args) {

        try {



//                    (JSONArray) jsonObject.get("teamMemberList"), (JSONArray) jsonObject.get("taskList"), (JSONArray) jsonObject.get("riskList"));
            //ArrayList<TeamMember> teamMemberList, ArrayList<Task> taskList, ArrayList< Risk > riskList);




            // System.out.println(teammates);
            // as a reference, here it prints in this format [{"salaryPerHour":"200","name":"Nafen Haj Ahmad","id":"1"},




                // System.out.println(taskMap.get("taskMembers"));
                // System.out.println(taskMates);
                // As a reference, this prints in the following format: [{"ID":"1","hoursWorked":"2"},{"ID":"2","hoursWorked":"4"}]
                // I noted that this format is the same as when printing teammates, and used the same logic to instantiate this info int a object
            JSONArray taskMates = (JSONArray) taskMap.get("taskMembers");

                Iterator taskMemberIterator = taskMates.iterator();

                while (taskMemberIterator.hasNext()) {

                    Map member = (Map) taskMemberIterator.next();



            HashMap<String, Double> map = new HashMap<String, Double>();
            JSONObject taskmate = (JSONObject) jsonObject.get("taskMember");
            JSONArray taskmateArray = (JSONArray) jsonObject.get("taskMember");

            Iterator taskMateIterator = taskmateArray.iterator();  //Creates a NullPointerExeption now
            while (taskMateIterator.hasNext()) {

                Map taskMate = (Map) taskMateIterator.next();

                int id = Integer.valueOf((String) taskMate.get("id"));
                Double hoursWorked = Double.valueOf((String) taskMate.get("hoursWorked"));

                TaskMember taskMember1 = new TaskMember(id, hoursWorked);

                // Task.getTaskMatesList().add(taskMember1);
                // Commented this out for now

                System.out.println(taskMember1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}