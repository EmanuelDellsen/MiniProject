package Main;

import Classes.*;

import org.apache.commons.lang3.StringUtils;
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

    private JSONObject createJSONObject() throws ParseException, IOException {
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


            //Checks if the projectId is a String
            if (!StringUtils.isNumeric((CharSequence) projectMap.get("projectId"))) {
                throw new JSONException("Ooops... Looks like your Project ID contains letters. Please enter a number instead");
            }

            int projectId = Integer.valueOf((String) projectMap.get("projectId"));
            String projectName = (String) projectMap.get("projectName");


            //      if(projectMap.get("actualStartDate").toString().chars().)

            //    if (!projectMap.get("actualStartDate").equals(formatter.ofPattern("yyyy-MM-dd")))
            //    throw new JSONException("0. Your date must be in the following format: yyyy-MM-dd. Please check the JSON-file and try again");
            // }

            LocalDate actualStartDate = LocalDate.parse((String) projectMap.get("actualStartDate"), formatter);

            //if (!projectMap.get("projectedCompletedDate").equals(formatter)) {
            //    throw new JSONException("1. Your date must be in the following format: yyyy-MM-dd. Please check the JSON-file and try again");
            //}

            LocalDate projectedCompletedDate = LocalDate.parse((String) projectMap.get("projectedCompletedDate"), formatter);

            if (projectedCompletedDate.compareTo(actualStartDate) < 0) {
                throw new JSONException("Hmmm... Looks like you set your end date before your start date. Please check the JSON-file and try again.");
            }

            Double budgetAtCompletion = Double.valueOf((String) projectMap.get("budgetAtCompletion"));

            if (budgetAtCompletion <= 0) {
                throw new JSONException("Ooops.... Looks like your budgetAtCompletion is negative or at zero. Please check your JSON-file and try again");
            }


            JSONArray teammates = (JSONArray) projectMap.get("teamMemberList");
            List<TeamMember> tempArrayListOfTeamMember = createTeamMember(teammates);

            JSONArray tasks = (JSONArray) projectMap.get("taskList");
            List<Task> tempArrayListOfTask = createTask(tasks);

            JSONArray risks = (JSONArray) projectMap.get("riskList");
            List<Risk> tempArrayListOfRisk = createRisk(risks);


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


            //Checks if the teamMemberID is a String
            if (!StringUtils.isNumeric((CharSequence) member.get("teamMemberId"))) {
                throw new JSONException("Ooops... Looks like one teamMemberID ID contains letters. Please enter a number instead");
            }

            int teamMemberId = Integer.valueOf((String) member.get("teamMemberId"));
            String teamMemberName = (String) member.get("teamMemberName");

            double salaryPerHour = Double.valueOf((String) member.get("salaryPerHour"));

            if (salaryPerHour < 0) {
                throw new JSONException("Ooops.... Looks like your salaryPerHour is negative. Please check your JSON-file and try again");
            }

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


            //Checks if the taskID is a String
            if (!StringUtils.isNumeric((CharSequence) taskMap.get("taskId"))) {
                throw new JSONException("Ooops... Looks like one taskID contains letters. Please enter a number instead");
            }

            int taskId = Integer.valueOf((String) taskMap.get("taskId"));

            String taskName = (String) taskMap.get("taskName");
            String description = (String) taskMap.get("description");


            LocalDate actualStartDate = LocalDate.parse((String) taskMap.get("actualStartDate"), formatter);
            LocalDate projectedCompletedDate = LocalDate.parse((String) taskMap.get("projectedCompletedDate"), formatter);
            LocalDate actualCompletedDate = LocalDate.parse((String) taskMap.get("actualCompletedDate"), formatter);

            if (projectedCompletedDate.compareTo(actualStartDate) < 0) {
                throw new JSONException("Hmmm... Looks like you set your projectedCompletedDate of a task is before your actualStartDate. Please check the JSON-file and try again.");
            }

            if (actualCompletedDate.compareTo(actualStartDate) < 0) {
                throw new JSONException("Hmmm... Looks like you set your actualCompletedDate of a task before your start date. Please check the JSON-file and try again.");
            }

            JSONObject taskMemberArray = (JSONObject) taskMap.get("hoursWorkedPerTeamMembers");

            Task newTask = new Task(taskId, taskName, description, actualStartDate, projectedCompletedDate,
                    actualCompletedDate, createTaskMembers(taskMemberArray));

            arrayListOfTasks.add(newTask);

        }
        return arrayListOfTasks;

    }

    private List<Risk> createRisk(JSONArray riskList) {

        List<Risk> arrayListOfRisks = new ArrayList<>();

        Iterator riskIterator = riskList.iterator();

        while (riskIterator.hasNext()) {

            Map riskMap = (Map) riskIterator.next();

            //Checks if the riskID is a String
            if (!StringUtils.isNumeric((CharSequence) riskMap.get("riskId"))) {
                throw new JSONException("Ooops... Looks like one riskID contains letters. Please enter a number instead");
            }

            int riskId = Integer.valueOf((String) riskMap.get("riskId"));
            String riskName = (String) riskMap.get("riskName");
            double probability = Double.valueOf((String) riskMap.get("probability"));
            int impact = Integer.valueOf((String) riskMap.get("impact"));

            if (probability < 0 || impact < 0) {
                throw new JSONException("Hey there! Don't put a negative impact or probability into your RiskMatrix");
            }

            Risk newRisk = new Risk(riskId, riskName, probability, impact);

            arrayListOfRisks.add(newRisk);
        }

        return arrayListOfRisks;
    }


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
