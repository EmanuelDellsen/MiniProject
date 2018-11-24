import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class jsonreading {


    List<TeamMate> list = new ArrayList<>();


    public static void main(String[] args) {

        JSONParser parser = new JSONParser();

        try {
            Object object = parser.parse(new FileReader("/Users/emanueldellsen/Desktop/MiniProject/resources/teammates.json"));

            //convert object to JSONObject
            JSONObject jsonObject = (JSONObject) object;

            //reading the string


            String name = (String) jsonObject.get("name");
            int id = (int) jsonObject.get("ID");
            double salary = (double) jsonObject.get("salary");

            JSONArray teammate = (JSONArray) jsonObject.get("teammate");

            System.out.println(name);
            System.out.println(id);
            System.out.println(salary);


            for (Object TeamMate : teammate){

                TeamMate member = new TeamMate((String) jsonObject.get(name), (int) jsonObject.get(id), (double) jsonObject.get(salary));



            }

        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}