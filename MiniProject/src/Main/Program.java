package Main;

import Classes.Project;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Program {

    private List<Project> listOfProjects = new ArrayList<>();

    JSONReader myJSONReader = new JSONReader();


    public void run() throws IOException, ParseException {
        setUpProgram();
        startProgram();
    }

    public void setUpProgram() throws IOException, ParseException {

        myJSONReader.methodToCallFromProgram();


        listOfProjects = myJSONReader.createProjects();


    }

    public void startProgram(){

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
