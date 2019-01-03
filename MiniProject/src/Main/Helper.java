package Main;

class Helper {

    boolean isInteger(String s){
        try{
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException exception){
            return false;
        }
        return true;
    }
}
