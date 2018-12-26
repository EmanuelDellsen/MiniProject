package Output;

import Classes.Project;
import Classes.Risk;
import Classes.Task;
import Classes.TeamMember;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;
import static java.lang.Math.toIntExact;

public class Output {

    private TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
    private static final int MAX_LENGTH = 30;
    private static final String ENDING = "...";

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

    public void displayMessage(String message){
        System.out.println(message);
    }

    public void displayRiskMatrix(Project project){

        System.out.println(String.format("%15s", "Risk Matrix"));

        for(Risk risk: project.getRiskList()){
                System.out.printf("%-30.30s %-10.10s %s %n",risk.getRiskName(),createRiskAsterisks(risk.returnRisk()),risk.riskDescription()+" risk");
        }
    }

    public void displayProjectVariance(Project project){

        System.out.print(String.format("%-25s","Project: "+project.getProjectName()));

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

        for (int i = 0; i < project.returnDatesPerInterval(0).size();i++){
            weekNumFromProjectStart += 2;
            System.out.print(String.format("%-14s", "Week " + weekNumFromProjectStart));
        }

        System.out.println();
        System.out.println("-----------------------------");
        System.out.println(String.format("%-30s", "Tasks"));

        for (Task task : project.returnTasksSortedByStartDate()) {

            System.out.printf("%-30s.30", task.getName());

            String taskLength = createScheduleAsterisks(task.returnTaskDurationInDays());
            long taskSpace = project.returnDaysBetweenProjectAndTask(task) + task.returnTaskDurationInDays();
            int taskSpaceAsInt = toIntExact(taskSpace);

            System.out.println(StringUtils.leftPad(taskLength, taskSpaceAsInt, " "));
        }
    }

    public void displayHoursPerTeamMember(Project project){

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

        for (TeamMember teamMember: project.getTeamMemberList()){
            System.out.println(String.format("|%30s| %s", StringUtils.capitalize(teamMember.getTeamMemberName()), teamMember.getTeamMemberId()));
        }
        System.out.println(createSymbolString("=", 32));
        System.out.println();
     }
}