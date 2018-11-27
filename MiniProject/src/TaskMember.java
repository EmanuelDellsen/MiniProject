public class TaskMember {

    private int id;
    private double hoursWorked;

    public TaskMember(int id, double hoursWorked){

        this.id=id;
        this.hoursWorked=hoursWorked;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

}
