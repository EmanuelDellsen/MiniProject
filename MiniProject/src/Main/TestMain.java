package Main;

import Classes.Project;
import Classes.Risk;
import Classes.Task;
import Classes.TeamMember;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestMain {

    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        //1 - Creating a new project

        String strA1 = "2018-10-01";
        LocalDate dateA1 = LocalDate.parse(strA1,formatter);
        String strB1 = "2019-02-01";
        LocalDate dateA2 = LocalDate.parse(strB1,formatter);

        Project project1 = new Project(1,"Duckies",dateA1,dateA2,
                430000);

        //2 - Creating 3 new risks & inserting them into the riskList in project1

        Risk risk1 = new Risk(1,"GitHubFrenzy",0.5,3);
        Risk risk2 = new Risk(2,"SlackOverload",0.3,5);
        Risk risk3 = new Risk(3,"TrelloConfusion",0.7,5);

        project1.getRiskList().add(risk1);
        project1.getRiskList().add(risk2);
        project1.getRiskList().add(risk3);

        //3 - Creating 3 new teamMembers & inserting them into the teamMemberList in project1

        TeamMember teamMember1 = new TeamMember(1,"Nafen",100);
        TeamMember teamMember2 = new TeamMember(2,"Thiago",350);
        TeamMember teamMember3 = new TeamMember(3,"Emanuel",300);

        project1.getTeamMemberList().add(teamMember1);
        project1.getTeamMemberList().add(teamMember2);
        project1.getTeamMemberList().add(teamMember3);

        //4 - Creating 2 new tasks & inserting it into the taskList in project1

        String str1 = "2018-11-02";
        LocalDate date1 = LocalDate.parse(str1,formatter);
        String str2 = "2019-01-01";
        LocalDate date2 = LocalDate.parse(str2,formatter);
        String str3 = "2018-11-11";
        LocalDate date3 = LocalDate.parse(str3,formatter);

        Task task1 = new Task(1,"CreateGitRepository","Github 4-life",
                date1,date2,date3);
        task1.getTaskMembers().put(1,10.0);
        project1.getTaskList().add(task1);

        String strb1 = "2018-11-02";
        LocalDate dateb1 = LocalDate.parse(strb1,formatter);
        String strb2 = "2018-12-02";
        LocalDate dateb2 = LocalDate.parse(strb2,formatter);
        String strb3 = "2018-11-30";
        LocalDate dateb3 = LocalDate.parse(strb3,formatter);

        Task task2 = new Task(1,"CreateGittyKitty","Github 4-life",
                dateb1,dateb2,dateb3);
        task2.getTaskMembers().put(1,10.0);
        project1.getTaskList().add(task2);

        // 5 - testing calculateEV
        System.out.println(project1.getTaskList());
        System.out.println(task1.getTaskValue());
        System.out.println(task2.getTaskValue());
        String str4 = "2018-11-15";
        LocalDate date4 = LocalDate.parse(str4,formatter);
        System.out.println(task1.taskIsComplete(date4));
        System.out.println(task2.taskIsComplete(date4));
        System.out.println(project1.percentageOfCompletedTasks(date4));
        System.out.println(project1.calculateEV(date4));

        //6 - testing calculate SV
        System.out.println("*****************************");
        System.out.println(project1.calculateEV(date4));
        System.out.println(project1.calculatePV(date4));
        System.out.println(project1.calculateSV(date4));

        //7 - testing workByMember

        System.out.println(project1.assignedTasksByMember(1));

        //8 - testing retrieveRisks

        System.out.println(project1.retrieveRisks());

        System.out.println(task1.getTaskValue());
        String tempStr = "2018-11-17";
        LocalDate tempDate = LocalDate.parse(tempStr,formatter);
        System.out.println(task1.progressByHour(tempDate,1));
        System.out.println(project1.calculateAC(tempDate));

        System.out.println(project1.calculateEV(tempDate));
        System.out.println(project1.calculateAC(tempDate));
        System.out.println(project1.calculateCV(tempDate));


    }

}
