package Main;

import Classes.Project;
import Classes.Risk;
import Classes.Task;
import Classes.TeamMember;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class TestMain {

    public static void main(String[] args) {

        //1 - Creating a new project

        Project project1 = new Project(1,"Duckies",
                "2018-11-01","2018-12-01",
                430000);

        //2 - Creating 3 new risks & inserting them into the riskList in project1

        Risk risk1 = new Risk(1,"GitHubFrenzy",0.5,3);
        Risk risk2 = new Risk(2,"SlackOverload",0.3,5);
        Risk risk3 = new Risk(3,"TrelloConfusion",0.7,5);

        project1.getRiskList().add(risk1);
        project1.getRiskList().add(risk2);
        project1.getRiskList().add(risk3);

        //3 - Creating 3 new teamMembers & inserting them into the teamMemberList in project1

        TeamMember teamMember1 = new TeamMember(1,"Nafen",250);
        TeamMember teamMember2 = new TeamMember(2,"Thiago",250);
        TeamMember teamMember3 = new TeamMember(3,"Emanuel",300);

        project1.getTeamMemberList().add(teamMember1);
        project1.getTeamMemberList().add(teamMember2);
        project1.getTeamMemberList().add(teamMember3);

        //4 - Creating 1 new tasks & inserting it into the taskList in project1

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String str = "2018-11-01";
        LocalDate date1 = LocalDate.parse(str,formatter);
        String str2 = "2018-10-01";
        LocalDate date2 = LocalDate.parse(str2,formatter);
        String str3 = "2018-11-11";
        LocalDate date3 = LocalDate.parse(str3,formatter);

        Task task1 = new Task(1,"CreateGitRepository","Github 4-life",
                date1,date2,date3);
        task1.getTaskMembers().put(1,10.0);
        project1.getTaskList().add(task1);

        // 5 - Printing content of project1
        System.out.println(task1.getTaskValue());
        project1.calculateEV()
    }

}
