package Main;

import Classes.*;

import org.apache.commons.lang3.StringUtils;
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

    private JsonExceptionMethods exceptionsClass = new JsonExceptionMethods();

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
            exceptionsClass.validateProjectId((CharSequence) projectMap.get("projectId"));

            //Checks if project actualStartDate and projectCompletedDate are in the format yyyy-mm-dd
            exceptionsClass.checkDate((String) projectMap.get("actualStartDate"));
            exceptionsClass.checkDate((String) projectMap.get("projectedCompletedDate"));

            int projectId = Integer.valueOf((String) projectMap.get("projectId"));
            String projectName = (String) projectMap.get("projectName");

            LocalDate actualStartDate = LocalDate.parse((String) projectMap.get("actualStartDate"), formatter);
            LocalDate projectedCompletedDate = LocalDate.parse((String) projectMap.get("projectedCompletedDate"), formatter);

            //checks if start date is before end date
            exceptionsClass.checkProjectCompletedDateAfterStart(projectedCompletedDate, actualStartDate);

            Double budgetAtCompletion = Double.valueOf((String) projectMap.get("budgetAtCompletion"));
            //validates if BudgetAtCompletion is positive
            exceptionsClass.validateBudgetAtCompletion(budgetAtCompletion);

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

    private List<Task> createTask(JSONArray taskList) throws IOException, ParseException {

        List<Task> arrayListOfTasks = new ArrayList<>();
        Iterator taskIterator = taskList.iterator();

        while (taskIterator.hasNext()) {

            Map taskMap = (Map) taskIterator.next();

            //Checks if the taskID is an integer
            exceptionsClass.validateTaskId((CharSequence) taskMap.get("taskId"));

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
            exceptionsClass.validateRiskId((CharSequence) riskMap.get("riskId"));

            int riskId = Integer.valueOf((String) riskMap.get("riskId"));
            String riskName = (String) riskMap.get("riskName");
            double probability = Double.valueOf((String) riskMap.get("probability"));
            int impact = Integer.valueOf((String) riskMap.get("impact"));

            //Checks if probability and impact are not negative
            exceptionsClass.validateRiskProbabilityAndImpact(probability, impact);

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