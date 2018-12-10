package Main;

import Classes.*;

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

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    protected void methodToCallFromProgram() throws IOException, ParseException {
        System.out.println(createProjects());


    }


    private JSONObject createJSONObject() throws FileNotFoundException, ParseException, IOException {
        JSONParser parser = new JSONParser();
        Object object = parser.parse(new FileReader("Project.json"));
        JSONObject jsonObject = (JSONObject) object;

        return jsonObject;
    }


    protected List<Project> createProjects() throws IOException, ParseException {

        List<Project> arrayListOfProjects = new ArrayList<>();

        JSONArray listOfProjects = (JSONArray) createJSONObject().get("listOfProjects");

        Iterator projectIterator = listOfProjects.iterator();

        while (projectIterator.hasNext()) {

            Map projectMap = (Map) projectIterator.next();

            int projectId = Integer.valueOf((String) projectMap.get("projectId"));
            String projectName = (String) projectMap.get("projectName");
            LocalDate actualStartDate = LocalDate.parse((String) projectMap.get("actualStartDate"), formatter);
            LocalDate projectedCompletedDate = LocalDate.parse((String) projectMap.get("projectedCompletedDate"), formatter);
            Double budgetAtCompletion = Double.valueOf((String) projectMap.get("budgetAtCompletion"));

            JSONArray teammates = (JSONArray) projectMap.get("teamMemberList");
            List<TeamMember> tempArrayListOfTeamMember = createTeamMember(teammates);

            JSONArray tasks = (JSONArray) projectMap.get("taskList");
            List<Task> tempArrayListOfTask = createTask(tasks);

            JSONArray risks = (JSONArray) projectMap.get("riskList");
            List<Risk> tempArrayListOfRisk = createRisk(risks);


            //JSONArray riskList = new JSONArray(); // This is here to add something as an argument in creation of Project
            // that argument will later be replaced with method-call: createRisk(risks);

            Project newProject = new Project(projectId, projectName, actualStartDate, projectedCompletedDate,
                    budgetAtCompletion);
            newProject.setTeamMemberList(tempArrayListOfTeamMember);
            newProject.setTaskList(tempArrayListOfTask);
            newProject.setRiskList(tempArrayListOfRisk);

            arrayListOfProjects.add(newProject);

        }

        return arrayListOfProjects;
    }

    private List<TeamMember> createTeamMember(JSONArray teamMemberList) {

        List<TeamMember> arrayListOfTeamMembers = new ArrayList<>();

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

    private List<Task> createTask(JSONArray taskList) throws IOException, ParseException {

        List<Task> arrayListOfTasks = new ArrayList<>();

        Iterator taskIterator = taskList.iterator();


        while (taskIterator.hasNext()) {

            Map taskMap = (Map) taskIterator.next();

            int taskId = Integer.valueOf((String) taskMap.get("taskId"));
            String taskName = (String) taskMap.get("taskName");
            String description = (String) taskMap.get("description");
            LocalDate actualStartDate = LocalDate.parse((String) taskMap.get("actualStartDate"), formatter);
            LocalDate projectedCompletedDate = LocalDate.parse((String) taskMap.get("projectedCompletedDate"), formatter);
            LocalDate actualCompletedDate = LocalDate.parse((String) taskMap.get("actualCompletedDate"), formatter);
            // boolean completedTask = Boolean.valueOf((String) taskMap.get("completedTask"));

            JSONObject taskMemberArray = (JSONObject) taskMap.get("hoursWorkedPerTeamMembers");

            Task newTask = new Task(taskId, taskName, description, actualStartDate, projectedCompletedDate,
                    actualCompletedDate, createTaskMembers(taskMemberArray)); //createTaskMemberAndList(taskMemberArray));

            arrayListOfTasks.add(newTask);

        }
        return arrayListOfTasks;

    }

    private List<Risk> createRisk (JSONArray riskList){

        List<Risk> arrayListOfRisks = new ArrayList<>();

        Iterator riskIterator = riskList.iterator();

        while (riskIterator.hasNext()){

            Map riskMap = (Map) riskIterator.next();

            int riskId = Integer.valueOf((String) riskMap.get("riskId"));
            String riskName = (String) riskMap.get("riskName");
            double probability = Double.valueOf((String) riskMap.get("probability"));
            int impact = Integer.valueOf((String) riskMap.get("impact"));

            Risk newRisk = new Risk(riskId, riskName, probability, impact);

            arrayListOfRisks.add(newRisk);
        }

        return  arrayListOfRisks;
    }

  /*  private List<TaskMember> createTaskMemberAndList(JSONArray taskMembers) {
        List<TaskMember> listOfTaskMembers = new ArrayList();

        Iterator taskMateIterator = taskMembers.iterator();

        while (taskMateIterator.hasNext()) {

            Map member = (Map) taskMateIterator.next();

            int id = Integer.valueOf((String) member.get("id"));
            double hoursWorked = Double.valueOf((String) member.get("hoursWorked"));

            TaskMember memberObject = new TaskMember(id, hoursWorked);

            listOfTaskMembers.add(memberObject);

        }

        return listOfTaskMembers;
    }
*/


    private Map<Integer, Double> createTaskMembers(JSONObject taskMembersList) {
        Map<Integer, Double> hashMapWithTaskMembers = new HashMap<Integer, Double>();

        Iterator<String> taskMateIterator = taskMembersList.keySet().iterator();

        while (taskMateIterator.hasNext()) {

            String member = taskMateIterator.next();

            int id = Integer.parseInt("id");
            double hoursWorked = Double.parseDouble("hoursWorked");

            hashMapWithTaskMembers.put(id, hoursWorked);

        }

        return hashMapWithTaskMembers;
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
