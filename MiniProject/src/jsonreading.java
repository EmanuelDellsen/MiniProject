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

            project.setTasksList(new ArrayList<>());



            JSONArray teammates = (JSONArray) jsonObject.get("teammates");

            Iterator<Map> teamMateIterator = teammates.iterator();

            while (teamMateIterator.hasNext()){

                Map member = teamMateIterator.next();

                String name = (String) member.get("name");
                int id = Integer.valueOf( (String) member.get("id"));
                String salary = (String) member.get("salary");

                TeamMate person = new TeamMate(name, id, salary);

                project.getTeammateList().add(person);

                System.out.println(member);

            }


            JSONArray tasks = (JSONArray) jsonObject.get("tasks");

            Iterator<Map> taskIterator = tasks.iterator();

            while(taskIterator.hasNext()){

                Map task = taskIterator.next();

                int id = Integer.valueOf( (String) task.get("id"));
                String name = (String) task.get("name");
                String description = (String) task.get("description");
                String startDate = (String) task.get("startDate");
                String endDate = (String) task.get("endDate");
                boolean completedTask = Boolean.valueOf( (String) task.get("completedTask"));

                ProjTask projTask1 = new ProjTask(id, name, description, startDate, endDate, completedTask);

                project.getTasksList().add(projTask1);



            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}