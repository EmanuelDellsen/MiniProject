package Main;

import Classes.Project;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Program {

    ArrayList<Project> listOfProjects = new ArrayList<>();

    JSONReader myJSONReader = new JSONReader();


    public void run() throws IOException, ParseException {
        setUpProgram();
        startProgram();
    }

    public void setUpProgram() throws IOException, ParseException {
        createProject();


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

    }





    public void startProgram(){

    }


    public ArrayList<Project> getListOfProjects() {
        return listOfProjects;
    }

    public void setListOfProjects(ArrayList<Project> listOfProjects) {
        this.listOfProjects = listOfProjects;
    }
}
