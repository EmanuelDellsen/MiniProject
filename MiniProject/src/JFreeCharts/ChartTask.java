package JFreeCharts;

import org.jfree.data.gantt.Task;

import java.util.Date;

class ChartTask extends Task {

    ChartTask(String description, Date start, Date end){
        super(description, start, end);
    }

}
