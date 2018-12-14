package Main;

import java.time.format.DateTimeParseException;

public class JSONException extends RuntimeException {

    public JSONException(){
        super("This is a default message, printing from JSONException method");
    }

    public JSONException(String message){
        super(message);
    }

}
