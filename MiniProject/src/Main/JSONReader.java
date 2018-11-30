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

            Project newProject = new Project((String) jsonObject.get("name"), (String) jsonObject.get("startDate"),
                    (String) jsonObject.get("endDate"), Double.valueOf((String) jsonObject.get("budgetAtCompletion")));

            newProject.setTeamMemberList(new ArrayList<>());

            newProject.setTaskList(new ArrayList<>());

            newProject.setRiskList(new ArrayList<>());

            JSONArray teammates = (JSONArray) jsonObject.get("teamMembers");

            // System.out.println(teammates);
            // as a reference, here it prints in this format [{"salaryPerHour":"200","name":"Nafen Haj Ahmad","id":"1"},

            Iterator<Map> teamMateIterator = teammates.iterator();

            while (teamMateIterator.hasNext()) {

                Map member = teamMateIterator.next();

                String name = (String) member.get("name");
                int id = Integer.valueOf((String) member.get("id"));
                String salary = (String) member.get("salaryPerHour");

                TeamMember newTeamMember = new TeamMember(name, id, salary);

                newProject.getTeamMemberList().add(newTeamMember);

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

                Map<Object, Object> assignedMembers = new HashMap<>();




                JSONArray taskMates = (JSONArray) taskMap.get("taskMembers");

                // System.out.println(taskMap.get("taskMembers"));
                // System.out.println(taskMates);
                // As a reference, this prints in the following format: [{"ID":"1","hoursWorked":"2"},{"ID":"2","hoursWorked":"4"}]
                // I noted that this format is the same as when printing teammates, and used the same logic to instantiate this info int a object


                Iterator<Map> taskMemberIterator = taskMates.iterator();

                while (taskMemberIterator.hasNext()) {

                    Map member = taskMemberIterator.next();

                    Object id2 = member.get("ID");
                    Object hoursWorked = member.get("hoursWorked");


                    TaskMember newTaskMember = new TaskMember(id2, hoursWorked);

                    //newProject.getTeamMemberList().add(newTeamMember);
                    //Commented this line out since it gave error



                    System.out.println("Printing ID for taskmember: " + newTaskMember.getId());  //Print only as a reference for us
                    System.out.println("Printing hoursWorked for ID " + newTaskMember.getId() + " : " + newTaskMember.getHoursWorked()); //Print only as a reference for us


                    // Loop to put key and value into the HasMap, can possibly be an enhanced for-loop later on
                    for (int i = 0; i < taskMates.size(); i++){
                        assignedMembers.put(newTaskMember.getId(), newTaskMember.getHoursWorked());
                    }

                }

                // Print for our reference, to see that the HashMap workes
                for (Object key : assignedMembers.keySet()){
                    System.out.println("Pritning from HashMap:  ID " + key + " - Hours Worked : " + assignedMembers.get(key));
                }

                Task newTask = new Task(id, name, description, startDate, endDate, completedTask, assignedMembers);

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