package Main;

import Classes.Project;

import Output.Output;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Program {

    private List<Project> listOfProjects;
    private JSONReader myJSONReader = new JSONReader();
    private Scanner sc = new Scanner(System.in);
    private final static String QUIT = "q";

    public void run() throws IOException, ParseException {
        setUpProgram();
        startProgram(this.listOfProjects);
    }

    private void setUpProgram() throws IOException, ParseException {
        setListOfProjects(myJSONReader.createProjects());
    }

    private void startProgram(List<Project> listOfProjects){

        Output currentOutput = new Output();
        Menu currentMenu = new Menu();
        Helper currentHelper = new Helper();

        String projectOption;

        do {
            currentOutput.displayMessage("List of projects:");
            for (Project project:listOfProjects) {
                currentOutput.displayMessage(project.toString());
            }
            currentOutput.displayMessage("Please choose project by ID: / Quit program by typing Q");
            projectOption = sc.next().toLowerCase();

            if (projectOption.equals(QUIT)){
                currentOutput.displayMessage("See you soon!");
            } else if (!currentHelper.isInteger(projectOption)){
                currentOutput.displayMessage("Please type a valid option!");
            } else {
                //this is because it has to be sure of being a string in order to parse it.
                String finalProjectOption = projectOption;
                Project currentProject = listOfProjects.stream().filter(project -> Integer.parseInt(finalProjectOption) == project.getProjectId()).findAny().orElse(null);
                if(currentProject==null){
                    currentOutput.displayMessage("Cannot find project with ID "+projectOption);
                } else {
                    currentMenu.runMenu(currentProject,currentOutput,currentHelper);
                }
            }
        } while (!projectOption.equals(QUIT));
    }

    private void setListOfProjects(List<Project> listOfProjects) {
        this.listOfProjects = listOfProjects;
    }
}
