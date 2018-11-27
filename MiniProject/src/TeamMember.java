

public class TeamMember {
    private String name;
    private String salaryPerHour;
    private int id;

    public TeamMember(String name, int id, String salaryPerHour) {
        this.name = name;
        this.id = id;
        this.salaryPerHour = salaryPerHour;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(String salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    public int getId() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }


    public String toString() {
        return "name= " + name + '\'' + ", salaryPerHour= " + salaryPerHour + '\'' + ", id=" + id;
    }

}