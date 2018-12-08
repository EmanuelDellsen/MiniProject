package Classes;

public class TaskMember {

    private Object id; //Object since it worked best with the JSONSimple. Can it be Strings/ints?
    private Object hoursWorked;

    public TaskMember(int id, double hoursWorked){

        this.id=id;
        this.hoursWorked = hoursWorked;

    }


    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(Object hoursWorked) {
        this.hoursWorked = hoursWorked;
    }
}
