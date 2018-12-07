package Main;

import Classes.Project;
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

    }

}
