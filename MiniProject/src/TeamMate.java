public class TeamMate {
    public String name;
    public double salary;
    public int id;

    public TeamMate(String name, int id, double salary) {
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    /*public String toString(){

    }*/
}