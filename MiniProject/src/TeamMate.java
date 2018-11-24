public class TeamMate {
    public String name;
    public String salary;
    public int id;

    public TeamMate(String name, int id, String salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

   /* public String toString(){



    }*/
}