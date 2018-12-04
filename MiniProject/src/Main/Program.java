package Main;

import Classes.Project;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public class Program {

    ArrayList<Project> listOfProjects = new ArrayList<>();



    public void run() throws IOException, ParseException {
        setUpProgram();
        startProgram();
    }

    public void setUpProgram() throws IOException, ParseException {
        createProject();


    }

    public Project createProject(){
        //Denna metoden finns för närvarande i JSON-Reader. Den returnar ett nytt projekt
    }


    public void addIntoArrayList(){
        listOfProjects.add();

    }

    //När man skapat en nytt prjekt ska detta läggas in i en ARRAY-LIST
    // Om det finns flera project så ska dessa läggas in i ArrayListen



    public void startProgram(){

    }

}
