package Output;

import Classes.Project;
import Classes.Risk;
import Classes.Task;
import Classes.TeamMember;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;
import static java.lang.Math.toIntExact;

public class Output {

    //Formatting week of year which is based on a specific LocalDate
    private TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();

    //Methods to create strings of asterisks for the output
    private String createRiskAsterisks(double Risk) {
        StringBuilder asteriskBuilder = new StringBuilder("*");

        for(double i = 1.0; i < Risk; i += 0.5) {
            asteriskBuilder.append("*");
        }
        return asteriskBuilder.toString();
    }

    private String createSymbolString(String symbol, int stringLength){
        String string = "";

        for (int i = 0; i < stringLength; i++){
            string += symbol;

        }
        return string;
    }

    private String createScheduleAsterisks(long taskLength) {

        StringBuilder asteriskBuilder = new StringBuilder("*");

        for(int i = 0; i < taskLength; i += 1) {
            asteriskBuilder.append("*");
        }
        return asteriskBuilder.toString();
    }

    //Basic print based on the input message/string
    public void displayMessage(String message){
        System.out.println(message);
    }


    public void displayRiskMatrix(Project project){

        System.out.println(String.format("%15s", "Risk Matrix"));

        //Prints name by the maximum of 30 symbols
        for(Risk risk: project.returnRisks()){
                System.out.printf("%-30.30s %-10.10s %s %n",risk.getRiskName(),createRiskAsterisks(risk.returnRisk()),risk.riskDescription()+" risk");
        }
    }

    public void displayProjectVariance(Project project){

        System.out.print(String.format("%-25s","Project: "+project.getProjectName()));

        //for each date per each 14 days in the project, this will print the week number of year of that date, skipping the starting date of the project
        for(LocalDate date: project.returnDatesPerInterval(1)){
            System.out.print(String.format("%-25s", "Week "+date.get(woy)));
        }

        System.out.println();
        System.out.print(String.format("%-25s","Earned Value"));

        for(LocalDate date: project.returnDatesPerInterval(1)){
            System.out.print(String.format("%-25.2f",project.calculateEV(date)));
        }

        System.out.println();
        System.out.print(String.format("%-25s","Schedule Variance"));

        for(LocalDate date: project.returnDatesPerInterval(1)){
            System.out.print(String.format("%-25.2f",project.calculateSV(date)));
        }

        System.out.println();
        System.out.print(String.format("%-25s","Cost Variance"));

        for(LocalDate date: project.returnDatesPerInterval(1)){
            System.out.print(String.format("%-25.2f",project.calculateCV(date)));
        }

    }

    public void displayTaskByMember(Project project, int teamMemberId){

        System.out.println(project.returnTeamMember(teamMemberId).getTeamMemberName());

        //Prints for each tasks that the teamMember that is searched for has been working with
        for(Task task: project.returnTasksByTeamMember(teamMemberId)){
            System.out.print(String.format("%-25.25s", task.getName()));
            System.out.print(String.format("%10s",task.returnHoursByMember(teamMemberId)));
            System.out.println();
        }

        System.out.print(String.format("%25s %10s","Total Hours", project.returnHoursByTeamMember(teamMemberId)));

    }

    public void displayProjectSchedule(Project project) {
        System.out.print(String.format("%-30s", "Project Schedule"));

        int weekNumFromProjectStart = 0;

        //Prints the amount of per each 2 weeks from the start of the project, starting on the 2nd week
        for (int i = 0; i < project.returnDatesPerInterval(0).size(); i++){
            weekNumFromProjectStart += 2;
            System.out.print(String.format("%-14s", "Week " + weekNumFromProjectStart));
        }

        System.out.println();
        System.out.println("-----------------------------");
        System.out.println(String.format("%-30s", "Tasks"));

        //Prints the tasks and the length of the tasks sorted by start date of task
        for (Task task : project.returnTasksSortedByStartDate()) {
            System.out.printf("%-30s", task.getName());

            String taskLength = createScheduleAsterisks(ChronoUnit.DAYS.between(task.getActualStartDate(),task.getProjectedCompletedDate()));
            long taskSpace = project.returnDaysBetweenProjectAndTask(task) + ChronoUnit.DAYS.between(project.getActualStartDate(),task.getProjectedCompletedDate());
            int taskSpaceAsInt = toIntExact(taskSpace);

            System.out.println(StringUtils.leftPad(taskLength, taskSpaceAsInt, " "));
        }
    }

    public void displayHoursPerTeamMember(Project project){

        //prints the list of teamMembers in the project sorted by hours
        for(TeamMember teamMember: project.returnTeamMembersSortedByHours()){
            System.out.println(String.format("%-30s.30 %s", StringUtils.capitalize(teamMember.getTeamMemberName())
                    ,project.returnHoursByTeamMember(teamMember.getTeamMemberId())));
        }
        System.out.println("==============================");
        System.out.println(String.format("%-30s %s","Total Hours",project.returnAllHoursWorked()));

    }

    public void displayMenu(String[] menuOptions){

        System.out.println();
        System.out.println(createSymbolString("=", 32));
        System.out.println(String.format("|%-30s|", "Option"));

        for (String menuOption: menuOptions){
            System.out.println(String.format("|%30s|", menuOption));
        }
        System.out.println(createSymbolString("=", 32));
        System.out.println();

    }

    public void displayTeamMembers(Project project){
        System.out.println();
        System.out.println(createSymbolString("=", 32));
        System.out.println();
        System.out.println(String.format("|%-30s|", "Choose team member:"));

        //prints name and id of all teamMembers of the project
        for (TeamMember teamMember: project.getTeamMemberList()){
            System.out.println(String.format("|%30s| %s", StringUtils.capitalize(teamMember.getTeamMemberName()), teamMember.getTeamMemberId()));
        }
        System.out.println(createSymbolString("=", 32));
        System.out.println();
     }
}