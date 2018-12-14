package Main;

import Classes.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;


public class TestMain {

    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        //1 - Creating a new project and printing the object

        String strA1 = "2018-10-01";
        LocalDate dateA1 = LocalDate.parse(strA1,formatter);
        String strB1 = "2018-11-30";
        LocalDate dateA2 = LocalDate.parse(strB1,formatter);

        Project project1 = new Project(1,"Duckies",dateA1,dateA2,
                100000);

        System.out.println(project1);

        //2 - Creating 3 new risks & inserting them into the riskList in project1

        Risk risk1 = new Risk(1,"GitHubFrenzy",0.5,3);
        Risk risk2 = new Risk(2,"SlackOverload",0.3,5);
        Risk risk3 = new Risk(3,"TrelloConfusion",0.8,5);

        project1.getRiskList().add(risk1);
        project1.getRiskList().add(risk2);
        project1.getRiskList().add(risk3);

        System.out.println(project1.getRiskList());

        //3 - Creating 3 new teamMembers & inserting them into the teamMemberList in project1

        TeamMember teamMember1 = new TeamMember(1,"Nafen",100);
        TeamMember teamMember2 = new TeamMember(2,"Thiago",200);
        TeamMember teamMember3 = new TeamMember(3,"Emanuel",400);

        project1.getTeamMemberList().add(teamMember1);
        project1.getTeamMemberList().add(teamMember2);
        project1.getTeamMemberList().add(teamMember3);

        System.out.println(project1.getTeamMemberList());

        //4 - Creating 3 new tasks & inserting it into the taskList in project1

        String str1 = "2018-10-01";
        LocalDate date1 = LocalDate.parse(str1,formatter);
        String str2 = "2018-10-15";
        LocalDate date2 = LocalDate.parse(str2,formatter);
        String str3 = "2018-10-08";
        LocalDate date3 = LocalDate.parse(str3,formatter);

        HashMap<Integer,Double> map1 = new HashMap<>();
        map1.put(1,10.0);
        map1.put(2,10.0);

        Task task1 = new Task(1,"Setup Slack","Slack 4-life",
                date1,date2,date3,map1);
        project1.getTaskList().add(task1);

        String str4 = "2018-10-08";
        LocalDate date4 = LocalDate.parse(str4,formatter);
        String str5 = "2018-10-22";
        LocalDate date5 = LocalDate.parse(str5,formatter);
        String str6 = "2018-10-15";
        LocalDate date6 = LocalDate.parse(str6,formatter);

        HashMap<Integer,Double> map2 = new HashMap<>();
        map2.put(1,10.0);
        map2.put(3,10.0);

        Task task2 = new Task(2,"Setup GitHub","Github 4-life",
                date4,date5,date6,map2);
        project1.getTaskList().add(task2);

        String str7 = "2018-10-15";
        LocalDate date7 = LocalDate.parse(str7,formatter);
        String str8 = "2018-10-29";
        LocalDate date8 = LocalDate.parse(str8,formatter);
        String str9 = "2018-10-22";
        LocalDate date9 = LocalDate.parse(str9,formatter);

        HashMap<Integer,Double> map3 = new HashMap<>();
        map3.put(2,20.0);
        map3.put(3,30.0);

        Task task3 = new Task(3,"Setup Trello","Trello 4-life",
                date7,date8,date9,map3);
        project1.getTaskList().add(task3);


        //5 - testing calculateEV

            //testing submethod of calculateEV -> percentageOfCompletedTasks
            String str10 = "2018-10-16";
            LocalDate date10 = LocalDate.parse(str10,formatter);
            System.out.println(project1.percentageOfCompletedTasks(date10)); // should return 0.66666666 since two tasks are completed before 2018-10-16 -> 0.6666666

            //testing submethod of calculateEV -> getBudgetAtCompletion
            System.out.println(project1.getBudgetAtCompletion()); // should return 100.000 since the project budget is set to 100.000 -> 100000.0

            //testing main method

            System.out.println(project1.calculateEV(date10)); // should return 66.666 since 0.666666 * 100.000 as above -> 66666.66

        //6 - testing calculateSV

            //testing submethod of calculateSV -> calculateEV
            System.out.println(project1.calculateEV(date10)); //should return 66.666 as in test above -> 66666.66

            //testing submethod of calculateSV -> calculatePV
            //method will print timeelapsed which is the number of days between startDateOfProject to (checkedDate) -> 15
            //method will print projectDuration which is the number of days between startDateOfProject to date of completion of project -> 60
            //method will do calculation -> timeElapsed / projectDuration * budgetAtCompletion which is currently 0.25*100.000 -> 25.000

            System.out.println(project1.calculatePV(date10));

            //testing main method

            System.out.println(project1.calculateSV(date10)); // should return 66666.66666 - 25000.0 -> 41666.66


        //7 - testing calculateCV

            //testing submethod of calculateCV -> calculateEV
            System.out.println(project1.calculateEV(date10)); // should return calculateEV as above -> 66666.66

            //testing submethod of calculateCV -> calculateAC
            //method will print 20000, 60000, 160000 which is the sum per each teamMember
            System.out.println(project1.calculateAC(date10)); // should return 9142

            //testing main method
            System.out.println(project1.calculateCV(date10)); // should print 66666.66-9142 = 57000 ca

    }

}