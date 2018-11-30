package Main;

import Classes.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class JSONReader {


    public void readJSONFile() throws FileNotFoundException, ParseException, IOException {

    }

    public void createProject(){

    }

    public static void main(String[] args) {


        try {
            JSONParser parser = new JSONParser();
            Object object = parser.parse(new FileReader("Project.json"));
            JSONObject jsonObject = (JSONObject) object;

            Project newProject = new Project(Integer.valueOf((String) jsonObject.get("projectId")),(String) jsonObject.get("projectName"), (String) jsonObject.get("actualStartDate"),
                    (String) jsonObject.get("projectCompletedDate"), Double.valueOf((String) jsonObject.get("budgetAtCompletion"))/*,
                    (JSONArray) jsonObject.get("teamMemberList"), (JSONArray) jsonObject.get("taskList"), (JSONArray) jsonObject.get("riskList")*/);
            //ArrayList<TeamMember> teamMemberList, ArrayList<Task> taskList, ArrayList< Risk > riskList) ;

            newProject.setTeamMemberList(new ArrayList<>());

            newProject.setTaskList(new ArrayList<>());

            newProject.setRiskList(new ArrayList<>());

            JSONArray teammates = (JSONArray) jsonObject.get("teamMemberList");

            // System.out.println(teammates);
            // as a reference, here it prints in this format [{"salaryPerHour":"200","name":"Nafen Haj Ahmad","id":"1"},

            Iterator<Map> teamMateIterator = teammates.iterator();

            while (teamMateIterator.hasNext()) {

                Map member = teamMateIterator.next();

                String name = (String) member.get("teamMemberName");
                int id = Integer.valueOf((String) member.get("teamMemberId"));
                double salary = Double.valueOf((String) member.get("salaryPerHour"));

                TeamMember newTeamMember = new TeamMember(name, id, salary);

                newProject.getTeamMemberList().add(newTeamMember);

            }



            JSONArray tasks = (JSONArray) jsonObject.get("taskList");
            Iterator<Map> taskIterator = tasks.iterator();

            while (taskIterator.hasNext()) {

                Map taskMap = taskIterator.next();

                int id = Integer.valueOf((String) taskMap.get("taskId"));
                String name = (String) taskMap.get("taskName");
                String description = (String) taskMap.get("description");
                String actualStartDate = (String) taskMap.get("actualStartDate");
                String projectedCompletedDate = (String) taskMap.get("projectedCompletedDate");
                String actualCompletedDate = (String) taskMap.get("actualCompletedDate");
               // boolean completedTask = Boolean.valueOf((String) taskMap.get("completedTask"));

                Map<Integer, Double> taskMembers = new HashMap<>();




                JSONArray taskMates = (JSONArray) taskMap.get("taskMembers");

                // System.out.println(taskMap.get("taskMembers"));
                // System.out.println(taskMates);
                // As a reference, this prints in the following format: [{"ID":"1","hoursWorked":"2"},{"ID":"2","hoursWorked":"4"}]
                // I noted that this format is the same as when printing teammates, and used the same logic to instantiate this info int a object


                Iterator<Map> taskMemberIterator = taskMates.iterator();

                while (taskMemberIterator.hasNext()) {

                    Map member = taskMemberIterator.next();

                    //This works niklas if we want to move away from using "object" and use int+double instead
                    int id2 = Integer.valueOf((String) member.get("id"));
                    double hoursWorked = Double.valueOf((String)member.get("hoursWorked"));
                   // Object id2 = member.get("id");
                   // Object hoursWorked = member.get("hoursWorked");


                    TaskMember newTaskMember = new TaskMember(id2, hoursWorked);

                    //newProject.getTeamMemberList().add(newTeamMember);
                    //Commented this line out since it gave error



                    System.out.println("Printing ID for taskmember: " + newTaskMember.getId());  //Print only as a reference for us
                    System.out.println("Printing hoursWorked for ID " + newTaskMember.getId() + " : " + newTaskMember.getHoursWorked()); //Print only as a reference for us


                    // Loop to put key and value into the HasMap, can possibly be an enhanced for-loop later on
                    for (int i = 0; i < taskMates.size(); i++){
                        taskMembers.put(newTaskMember.getId(), newTaskMember.getHoursWorked());
                    }

                }

                // Print for our reference, to see that the HashMap workes
                for (Object key : taskMembers.keySet()){
                    System.out.println("Pritning from HashMap:  ID " + key + " - Hours Worked : " + taskMembers.get(key));
                }

                Task newTask = new Task(id, name, description, actualStartDate, projectedCompletedDate,  actualCompletedDate, taskMembers);

                newProject.getTaskList().add(newTask);

                // newTask.setTaskMatesList(new ArrayList<>());

              //  System.out.println(newTask);


            }


            HashMap<String, Double> map = new HashMap<String, Double>();

           JSONObject taskmate = (JSONObject) jsonObject.get("taskMember");


                JSONArray taskmateArray = (JSONArray) jsonObject.get("taskMember");

                Iterator<Map> taskMateIterator = taskmateArray.iterator();  //Creates a NullPointerExeption now

                while (taskMateIterator.hasNext()) {

                    Map taskMate = taskMateIterator.next();

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