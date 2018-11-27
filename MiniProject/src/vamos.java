/*
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class vamos {


    public static void main(String[] args) {

        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("/Users/emanueldellsen/Desktop/MiniProject/resources/teammates.json")) {
            Object obj = parser.parse(reader);

            JSONArray teammateList = new JSONArray();
            teammateList.add(obj);
            System.out.println(teammateList);

            teammateList.forEach(teamMate -> parseTeammateObject((JSONObject) teamMate));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public static void parseTeammateObject( JSONObject teammate){

        JSONObject teammateObject = (JSONObject) teammate.get("TeamMember");

        String name = (String) teammateObject.get("name");
        System.out.println(name);

        String ID = (String) teammateObject.get("ID");
        System.out.println(ID);

        String salary = (String) teammateObject.get("salary");
        System.out.println(salary);

    }
}




       */
/* JSONObject jsonObject = (JSONObject) obj;
        System.out.println(jsonObject);

        String name = (String) jsonObject.get("name");
        System.out.println(name);

        int ID = (int) jsonObject.get("ID");
        System.out.println(ID);

        double salary = (double) jsonObject.get("salary");
            System.out.println(salary);

        JSONArray msg = (JSONArray) jsonObject.get("messages ");
            for (String aMsg : (Iterable<String>) msg) {
                System.out.println(aMsg);
            }*//*



*/
