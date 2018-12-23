package JFreeCharts;

import org.jfree.chart.axis.DateAxis;
import org.jfree.data.gantt.Task;

import java.util.Date;

public class chartTask extends Task {

    public chartTask(String description, Date start, Date end ){
        super(description, start, end);
    }

}
