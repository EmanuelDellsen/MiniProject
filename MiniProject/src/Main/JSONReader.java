package Main;

import Classes.Project;
import Classes.Task;
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
        System.out.println(createProjects());
    }


    public JSONObject createJSONObject() throws FileNotFoundException, ParseException, IOException {
        JSONParser parser = new JSONParser();
        Object object = parser.parse(new FileReader("Project.json"));
        JSONObject jsonObject = (JSONObject) object;
        return jsonObject;
    }


    public JSONArray createProjects() throws IOException, ParseException {

        JSONArray arrayListOfProjects = new JSONArray();

        JSONArray listOfProjects = (JSONArray) createJSONObject().get("listOfProjects");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Iterator projectIterator = listOfProjects.iterator();

        while (projectIterator.hasNext()) {

            Map projectMap = (Map) projectIterator.next();

            int projectId = Integer.valueOf((String) projectMap.get("projectId"));
            String projectName = (String) projectMap.get("projectName");
            LocalDate actualStartDate = LocalDate.parse((String) projectMap.get("actualStartDate"), formatter);
            LocalDate projectedCompletedDate = LocalDate.parse((String) projectMap.get("projectedCompletedDate"), formatter);
            Double budgetAtCompletion = Double.valueOf((String) projectMap.get("budgetAtCompletion"));


            JSONArray teammates = (JSONArray) projectMap.get("teamMemberList");
            JSONArray tasks = (JSONArray) projectMap.get("taskList");


            JSONArray riskList = new JSONArray(); // This is here to add something as an argument in creation of Project
            // that argument will later be replaced with method-call: createRisk(risks);


                Project newProject = new Project(projectId, projectName, actualStartDate, projectedCompletedDate,
                        budgetAtCompletion, createTeamMember(teammates), createTask(tasks), riskList);
                arrayListOfProjects.add(newProject);

            }

            return arrayListOfProjects;
    }


    public JSONArray createTeamMember (JSONArray teamMemberList){

        JSONArray arrayListOfTeamMembers = new JSONArray();

        Iterator teamMateIterator = teamMemberList.iterator();

        while (teamMateIterator.hasNext()) {

            Map member = (Map) teamMateIterator.next();
            int teamMemberId = Integer.valueOf((String) member.get("teamMemberId"));
            String teamMemberName = (String) member.get("teamMemberName");
            double salaryPerHour = Double.valueOf((String) member.get("salaryPerHour"));

            TeamMember newTeamMember = new TeamMember(teamMemberId, teamMemberName, salaryPerHour);

            arrayListOfTeamMembers.add(newTeamMember);

        }

        return arrayListOfTeamMembers;

    }



    public HashMap createTaskMembers (JSONArray taskMembersList){
        HashMap<Object, Object> HashMapWithTaskMembers = new HashMap<>();

        Iterator taskMateIterator = taskMembersList.iterator();

        while (taskMateIterator.hasNext()) {

            Map member = (Map) taskMateIterator.next();

            Object id = member.get("id");
            Object hoursWorked = member.get("hoursWorked");

            HashMapWithTaskMembers.put(id,hoursWorked);

        }

        return HashMapWithTaskMembers;
    }



<<<<<<< HEAD
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
=======
    public JSONArray createTask(JSONArray taskList) throws IOException, ParseException {

        JSONArray arrayListOftasks = new JSONArray();
>>>>>>> f35fc8ef83a6d70a9dd3a0d3e3cd1ea5bf3c6b01

        Iterator taskIterator = taskList.iterator();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (taskIterator.hasNext()) {

            Map taskMap = (Map) taskIterator.next();

            int taskId = Integer.valueOf((String) taskMap.get("taskId"));
            String taskName = (String) taskMap.get("taskName");
            String description = (String) taskMap.get("description");
            LocalDate actualStartDate = LocalDate.parse((String) taskMap.get("actualStartDate"), formatter);
            LocalDate projectedCompletedDate = LocalDate.parse((String) taskMap.get("projectedCompletedDate"), formatter);
            LocalDate actualCompletedDate = LocalDate.parse((String) taskMap.get("actualCompletedDate"), formatter);
            // boolean completedTask = Boolean.valueOf((String) taskMap.get("completedTask"));

            JSONArray taskMemberArray = (JSONArray) taskMap.get("taskMembers");

            Task newTask = new Task(taskId, taskName, description, actualStartDate, projectedCompletedDate, actualCompletedDate, createTaskMembers(taskMemberArray));

            arrayListOftasks.add(newTask);

          //  newTask.setTaskMembers( new HashMap<>()) ;

        }
        return arrayListOftasks;

    }






}
    /*

    public JSONArray createTeamMembers() throws IOException, ParseException {

        JSONArray arrayListOfTeamMembers = new JSONArray();

        JSONArray teammates = (JSONArray) createJSONObject().get("teamMemberList");

        System.out.println(teammates); // DENNA ÄR TOM! Därav NULL POINTER

        Iterator teamMateIterator = teammates.iterator();

        while (teamMateIterator.hasNext()) {

            Map member = (Map) teamMateIterator.next();
            int teamMemberId = Integer.valueOf((String) member.get("teamMemberId"));
            String teamMemberName = (String) member.get("teamMemberName");
            double salaryPerHour = Double.valueOf((String) member.get("salaryPerHour"));

            TeamMember newTeamMember = new TeamMember(teamMemberId, teamMemberName, salaryPerHour);

            arrayListOfTeamMembers.add(newTeamMember);


        }

        return arrayListOfTeamMembers;


    }



    public JSONArray createTaskMembers() throws IOException, ParseException {

        JSONArray taskMembers = (JSONArray) createJSONObject().get("taskMembers");
        Iterator taskMemberIterator = taskMembers.iterator();


        while (taskMemberIterator.hasNext()) {

            Map taskMember = (Map) taskMemberIterator.next();

            int id = Integer.valueOf((String) taskMember.get("id"));
            double hoursWorked = Double.valueOf((String) taskMember.get("hoursWorked"));

            TaskMember newTaskMember = new TaskMember(id, hoursWorked);

            // Loop to put key and value into the HasMap, can possibly be an enhanced for-loop later on
            // for (int i = 0; i < taskMembers.size(); i++) {
//                taskMember.put(newTaskMember.getId(), newTaskMember.getHoursWorked());
            }
            return taskMembers;

        }



    }





           /* // Print for our reference, to see that the HashMap workes
            for (Object key : taskMembers.keySet()) {
                System.out.println("Pritning from HashMap:  ID " + key + " - Hours Worked : " + taskMembers.get(key));
            }


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

   */
