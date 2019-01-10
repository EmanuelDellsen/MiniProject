package Main;

public class JSONException extends RuntimeException {

    public JSONException(){
        super("Something went wrong. Please try again");
    }

    JSONException(String message){
        super(message);
    }

}
