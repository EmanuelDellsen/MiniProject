package Main;

import Classes.Project;

import Output.Output;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

class Program {

    private List<Project> listOfProjects;
    private JSONReader myJSONReader = new JSONReader();
    private Scanner sc = new Scanner(System.in);
    private final static String QUIT = "q";

    void run() throws IOException, ParseException {
        setUpProgram();
        startProgram(this.listOfProjects);
    }

    private void setUpProgram() throws IOException, ParseException {
        setListOfProjects(myJSONReader.createProjects());
    }

    private void startProgram(List<Project> listOfProjects){

        //Creates output menu and helper objects
        Output currentOutput = new Output();
        Menu currentMenu = new Menu();
        Helper currentHelper = new Helper();

        String projectOption;

        //This runs as long as the user doesn't exit the program in the project choice or in the project itself
        do {
            currentOutput.displayMessage("List of projects:");
            for (Project project:listOfProjects) {
                currentOutput.displayMessage(project.toString());
            }
            currentOutput.displayMessage("Please choose project by ID: / Quit program by typing Q");

            //Users type option
            projectOption = sc.next().toLowerCase();

            if (projectOption.equals(QUIT)){
                currentOutput.displayMessage("See you soon!");
            } else if (!currentHelper.isInteger(projectOption)){
                //Prints message if the user types anything but an integer
                currentOutput.displayMessage("Please type a valid option!");
            } else {
                //If integer, then it searches for the project of choice
                String finalProjectOption = projectOption;
                Project currentProject = listOfProjects.stream().filter(project -> Integer.parseInt(finalProjectOption) == project.getProjectId()).findAny().orElse(null);
                if(currentProject==null){
                    //If the project with the user projectID is not found, it displays the following message
                    currentOutput.displayMessage("Cannot find project with ID "+projectOption);
                } else {
                    //If user has typed an existing projectID, then the project starts up by displaying the menu
                    currentMenu.runMenu(currentProject,currentOutput,currentHelper);
                }
            }
        } while (!projectOption.equals(QUIT));
    }

    private void setListOfProjects(List<Project> listOfProjects) {
        this.listOfProjects = listOfProjects;
    }
}
