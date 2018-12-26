package Main;

public class Helper {

    public boolean isInteger(String s){
        try{
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException exception){
            return false;
        }
        return true;
    }
}
