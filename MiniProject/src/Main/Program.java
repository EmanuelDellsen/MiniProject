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

        listOfProjects = myJSONReader.createProjects();

    }


    public void startProgram(){

    }

    public JSONArray getListOfProjects() {
        return listOfProjects;
    }
}
