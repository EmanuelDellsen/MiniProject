package Main;

import Classes.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class JSONReader {

    private JSONExceptionMethods exceptionsClass = new JSONExceptionMethods();

    //creating a formatter for dates read from the JSON to match our calculations

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    //creating a jsonObject to use for parsing the information from the local JSON-file

    private JSONObject createJSONObject() throws ParseException, IOException {
        JSONParser parser = new JSONParser();
        Object object = parser.parse(new FileReader("Project.json"));
        JSONObject jsonObject = (JSONObject) object;

        return jsonObject;
    }

    //Method to create an array of projects found in the JSON-file and returning it to the class "Program"

    protected List<Project> createProjects() throws IOException, ParseException {

        List<Project> arrayListOfProjects = new ArrayList<>();

        //method call to create an JSONArray using the createJSONObject method

        JSONArray listOfProjects = (JSONArray) createJSONObject().get("listOfProjects");

        //Creating an iterator to search for keys and their values

        Iterator projectIterator = listOfProjects.iterator();

        //While loop using the projectIterator and a "hasNext()" function to continue reading only while it has a next element

        while (projectIterator.hasNext()) {

            Map projectMap = (Map) projectIterator.next();

            //Checks if the projectId is a String
            exceptionsClass.validateProjectId((CharSequence) projectMap.get("projectId"));

            //Checks if project actualStartDate and projectCompletedDate are in the format yyyy-mm-dd
            exceptionsClass.checkDate((String) projectMap.get("actualStartDate"));
            exceptionsClass.checkDate((String) projectMap.get("projectedCompletedDate"));

            //Since all our values are String we need to do casting to match the primitive types in their respective classes

            int projectId = Integer.valueOf((String) projectMap.get("projectId"));
            String projectName = (String) projectMap.get("projectName");

            LocalDate actualStartDate = LocalDate.parse((String) projectMap.get("actualStartDate"), formatter);
            LocalDate projectedCompletedDate = LocalDate.parse((String) projectMap.get("projectedCompletedDate"), formatter);

            //checks if start date is before end date
            exceptionsClass.checkProjectCompletedDateAfterStart(projectedCompletedDate, actualStartDate);

            Double budgetAtCompletion = Double.valueOf((String) projectMap.get("budgetAtCompletion"));
            //validates if BudgetAtCompletion is positive
            exceptionsClass.validateBudgetAtCompletion(budgetAtCompletion);

            //Wrapping the other methods to read the rest of the JSON-File and create objects based on the values of the keys

            JSONArray teammates = (JSONArray) projectMap.get("teamMemberList");

            //Adding the created team members to a temporary List

            List<TeamMember> tempArrayListOfTeamMember = createTeamMember(teammates);

            JSONArray tasks = (JSONArray) projectMap.get("taskList");

            //Adding the created tasks to a temporary List

            List<Task> tempArrayListOfTask = createTask(tasks);

            JSONArray risks = (JSONArray) projectMap.get("riskList");

            //Adding the created risks to a temporary List

            List<Risk> tempArrayListOfRisk = createRisk(risks);

            //Creating the projects read from the JSON-file

            Project newProject = new Project(projectId, projectName, actualStartDate, projectedCompletedDate,
                    budgetAtCompletion);

            //Setting the all the lists that each project that we created has and adding the temporary Lists we created to those

            newProject.setTeamMemberList(tempArrayListOfTeamMember);
            newProject.setTaskList(tempArrayListOfTask);
            newProject.setRiskList(tempArrayListOfRisk);

            arrayListOfProjects.add(newProject);
        }
        return arrayListOfProjects;
    }

    //The method that the createProjects() method calls to read through the JSON and parse the values found from the keys provided

    private List<TeamMember> createTeamMember(JSONArray teamMemberList) {

        List<TeamMember> arrayListOfTeamMembers = new ArrayList<>();
        Iterator teamMateIterator = teamMemberList.iterator();

        while (teamMateIterator.hasNext()) {

            Map member = (Map) teamMateIterator.next();

            //Checks if the teamMemberID is an int
            exceptionsClass.validateTeamMemberId((CharSequence) member.get("teamMemberId"));

            int teamMemberId = Integer.valueOf((String) member.get("teamMemberId"));
            String teamMemberName = (String) member.get("teamMemberName");

            double salaryPerHour = Double.valueOf((String) member.get("salaryPerHour"));

            //checks if salaryPerHour is negative
            exceptionsClass.validateSalaryPerHour(salaryPerHour);

            TeamMember newTeamMember = new TeamMember(teamMemberId, teamMemberName, salaryPerHour);
            arrayListOfTeamMembers.add(newTeamMember);
        }
        return arrayListOfTeamMembers;
    }

    //The method that the createProjects() method calls to read through the JSON and parse the values found from the keys provided

    private List<Task> createTask(JSONArray taskList) throws IOException, ParseException {

        List<Task> arrayListOfTasks = new ArrayList<>();
        Iterator taskIterator = taskList.iterator();

        while (taskIterator.hasNext()) {

            Map taskMap = (Map) taskIterator.next();

            //Checks if the taskID is an integer
            exceptionsClass.validateTaskId((CharSequence) taskMap.get("taskId"));

            //Using the taskMap (iterator) to find keys and retrieve their values
            // and cast them to match the primitive type set in class of the the respective

            int taskId = Integer.valueOf((String) taskMap.get("taskId"));
            String taskName = (String) taskMap.get("taskName");
            String description = (String) taskMap.get("description");

            //validates, with regex, data format yyyy-mm-dd
            exceptionsClass.checkDate((String) taskMap.get("actualStartDate"));
            exceptionsClass.checkDate((String) taskMap.get("projectedCompletedDate"));
            exceptionsClass.checkDate((String) taskMap.get("actualCompletedDate"));

            LocalDate actualStartDate = LocalDate.parse((String) taskMap.get("actualStartDate"), formatter);
            LocalDate projectedCompletedDate = LocalDate.parse((String) taskMap.get("projectedCompletedDate"), formatter);
            LocalDate actualCompletedDate = LocalDate.parse((String) taskMap.get("actualCompletedDate"), formatter);

            //this checks if projectedCompletedDate and actualCompletedDate are after actualStartDate of tasks
            exceptionsClass.checkTaskEndDatesAfterStart(actualStartDate, projectedCompletedDate, actualCompletedDate);

            //Iterating through each task to find the key "hoursWorkedPerTeamMember" and retrieving the values those keys hold

            JSONObject taskMemberMap = (JSONObject) taskMap.get("hoursWorkedPerTeamMember");

            //Creating an object of type Task using the values found from iterations done above

            Task newTask = new Task(taskId, taskName, description, actualStartDate, projectedCompletedDate,
                    actualCompletedDate, createTaskMembers(taskMemberMap));

            arrayListOfTasks.add(newTask);
        }
        return arrayListOfTasks;
    }

    //The method that the createProjects() method calls to read through the JSON and parse the values found from the keys provided

    private List<Risk> createRisk(JSONArray riskList) {

        List<Risk> arrayListOfRisks = new ArrayList<>();
        Iterator riskIterator = riskList.iterator();
        while (riskIterator.hasNext()) {

            Map riskMap = (Map) riskIterator.next();

            //Checks if the riskID is a String
            exceptionsClass.validateRiskId((CharSequence) riskMap.get("riskId"));

            //Using the riskMap (iterator) to find keys and retrieve their values
            // and cast them to match the primitive type set in class of the the respective

            int riskId = Integer.valueOf((String) riskMap.get("riskId"));
            String riskName = (String) riskMap.get("riskName");
            double probability = Double.valueOf((String) riskMap.get("probability"));
            int impact = Integer.valueOf((String) riskMap.get("impact"));

            //Checks if probability and impact are not negative
            exceptionsClass.validateRiskProbabilityAndImpact(probability, impact);

            //Creating an object of type risk using the values found from iterations done above

            Risk newRisk = new Risk(riskId, riskName, probability, impact);
            arrayListOfRisks.add(newRisk);
        }
        return arrayListOfRisks;
    }

    //A method to create a Map assigned to each task containing ID(key) of the team member who worked on it
    // and the hours he/she worked(value). This is later returned as a Map of these members and assigned to each
    // individual task read from the createTask method

    private Map<Integer, Double> createTaskMembers(JSONObject taskList) {

        Map<Integer, Double> mapOfTaskMembers = new HashMap<>();
        Iterator taskMateIterator = taskList.keySet().iterator();

        while (taskMateIterator.hasNext()) {

            Integer key = Integer.valueOf((String) taskMateIterator.next());
            double value = Double.valueOf((String) taskList.get(key.toString()));
            mapOfTaskMembers.put(key, value);
        }
        return mapOfTaskMembers;
    }

}