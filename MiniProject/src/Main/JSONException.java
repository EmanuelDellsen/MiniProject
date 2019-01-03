package Main;

public class JSONException extends RuntimeException {

    public JSONException(){
        super("This is a default message, printing from JSONException method");
    }

    public JSONException(String message){
        super(message);
    }

}
