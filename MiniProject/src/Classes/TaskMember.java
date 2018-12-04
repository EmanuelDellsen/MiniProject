package Classes;

public class TaskMember {

    private int id; //Object since it worked best with the JSONSimple. Can it be Strings/ints?
    private double hoursWorked;

    public TaskMember(int id, double hoursWorked){

        this.id=id;
        this.hoursWorked = hoursWorked;

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

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }
}
