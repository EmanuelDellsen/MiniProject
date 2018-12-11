package Main;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        Program newProgram = new Program();

        try{

            newProgram.run();
            
        } catch (IOException | ParseException exception){

            System.out.println("Some exception");

        }
    }
}