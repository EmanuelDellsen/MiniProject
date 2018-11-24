import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class jsonreading {


    public static void main(String[] args) {

        JSONParser parser = new JSONParser();

        try {
            Object object = parser.parse(new FileReader("C:\\Users\\Emanuel Dellsén\\Downloads\\MiniProject-master\\MiniProject-master\\MiniProject\\resources\\Project.json"));

            JSONObject jsonObject = (JSONObject) object;

            Project project = new Project((String)jsonObject.get("name"),(String) jsonObject.get("startDate"),(String) jsonObject.get("endDate"),Double.valueOf((String)jsonObject.get("budgetedCost")));

            project.setTeammateList(new ArrayList<>());

            JSONArray teammates = (JSONArray) jsonObject.get("teammates");

            Iterator<Map> iterator = teammates.iterator();

            while (iterator.hasNext()){

                Map member = iterator.next();

                String name = (String) member.get("name");
                int id = Integer.valueOf( (String) member.get("id"));
                String salary = (String) member.get("salary");

                TeamMate person = new TeamMate(name, id, salary);

                project.getTeammateList().add(person);

                System.out.println(member);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}