package Main;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public class TestMainForJSON {

    public static void main(String[] args) {

        Program myProgram = new Program();
        try {
            myProgram.run();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(myProgram.listOfProjects.get(0).getProjectName());
        System.out.println(myProgram.listOfProjects.get(0).getTeamMemberList());

    }

}
