package Classes;

public class TeamMember {
    private String TeamMemeberName;
    private double salaryPerHour;
    private int teamMemberId;

    public TeamMember(String name, int id, double salaryPerHour) {
        this.TeamMemeberName = name;
        this.teamMemberId = id;
        this.salaryPerHour = salaryPerHour;
    }



    public String getTeamMemeberName() {
        return TeamMemeberName;
    }

    public void setTeamMemeberName(String teamMemeberName) {
        this.TeamMemeberName = teamMemeberName;
    }

    public double getSalaryPerHour() {
        return this.salaryPerHour;
    }

    public void setSalaryPerHour(double salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    public int getTeamMemberId() {
        return teamMemberId;
    }

    public void setID(int id) {
        this.teamMemberId = id;
    }


    public String toString() {
        return "TeamMemeberName= " + TeamMemeberName + '\'' + ", salaryPerHour= " + salaryPerHour + '\'' + ", teamMemberId=" + teamMemberId;
    }

}