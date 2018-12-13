package Main;

import Classes.Project;

import Output.Output;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public class Program {

    private List<Project> listOfProjects;
    JSONReader myJSONReader = new JSONReader();


    public void run() throws IOException, ParseException {
        setUpProgram();
        startProgram(this.listOfProjects);
    }

    public void setUpProgram() throws IOException, ParseException {
        setListOfProjects(myJSONReader.createProjects());
    }

    public void startProgram(List<Project> listOfProjects){

        //This is only for this current setup
        Project currentProject = listOfProjects.get(0);
        //This is only for this current setup

        Output currentOutput = new Output();
        Menu currentMenu = new Menu();
        currentMenu.runMenu(currentProject,currentOutput);

    }



    public List<Project> getListOfProjects() {
        return listOfProjects;
    }

    public void setListOfProjects(List<Project> listOfProjects) {
        this.listOfProjects = listOfProjects;
    }

    public JSONReader getMyJSONReader() {
        return myJSONReader;
    }

    public void setMyJSONReader(JSONReader myJSONReader) {
        this.myJSONReader = myJSONReader;
    }
}
