package Main;

public class Main {

    public static void main(String[] args) {

        Program newProgram = new Program();

        try{
            newProgram.run();
        } catch (Exception e){
            e.getMessage();
        }
    }
}