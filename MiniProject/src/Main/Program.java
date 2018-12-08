package Main;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Program {

    JSONArray listOfProjects;

    JSONReader myJSONReader = new JSONReader();


    public void run() throws IOException, ParseException {
        setUpProgram();
        startProgram();
    }

    public void setUpProgram() throws IOException, ParseException {

        myJSONReader.methodToCallFromProgram();

<<<<<<< HEAD
    }

    public JSONArray createProject() throws IOException, ParseException {

        JSONArray listOfProjects = (JSONArray) myJSONReader.createJSONObject().get("listOfProjects");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        Iterator projectIterator = listOfProjects.iterator();

        while(projectIterator.hasNext()) {

            Map projectMap = (Map) projectIterator.next();

            int projectId = Integer.valueOf((String) projectMap.get("projectId"));
            String projectName = (String) projectMap.get("projectName");
            LocalDate actualStartDate = LocalDate.parse((String) projectMap.get("actualStartDate"), formatter );
            LocalDate projectCompletedDate = LocalDate.parse((String) projectMap.get("projectCompletedDate"), formatter );
            Double budgetAtCompletion = Double.valueOf((String) projectMap.get("budgetAtCompletion"));



            Project newProject = new Project(projectId, projectName, actualStartDate, projectCompletedDate, budgetAtCompletion);
            getListOfProjects().add(newProject);
        }
        return listOfProjects;
=======
        listOfProjects = myJSONReader.createProjects();
>>>>>>> f35fc8ef83a6d70a9dd3a0d3e3cd1ea5bf3c6b01

    }


    public void startProgram(){

    }

    public JSONArray getListOfProjects() {
        return listOfProjects;
    }
}
