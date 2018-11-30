package Classes;

public class TeamMember {
    private String teamMemberName;
    private double salaryPerHour;
    private int teamMemberId;

    public TeamMember(String name, int id, double salaryPerHour) {
        this.teamMemberName = name;
        this.teamMemberId = id;
        this.salaryPerHour = salaryPerHour;
    }

    public String getTeamMemberName() {
        return teamMemberName;
    }

    public void setTeamMemberName(String teamMemberName) {
        this.teamMemberName = teamMemberName;
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
        return "teamMemberName= " +
                teamMemberName +
                '\'' + ", salaryPerHour= " + salaryPerHour + '\'' + ", teamMemberId=" + teamMemberId;
    }

}