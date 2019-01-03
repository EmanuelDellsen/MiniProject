package JFreeCharts;

import Classes.Project;
import Classes.Task;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;

import javax.swing.*;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

public class GanttChart extends JFrame {

    TaskSeriesCollection dataset = new TaskSeriesCollection();
    private TaskSeries projectedDate = new TaskSeries("Projected to be complete");
    private TaskSeries completedDate = new TaskSeries("Actually complete");


    public GanttChart(Project project){

        //Set title of project chart
        super(project.getProjectName());

        //Create dataset (from method)
        IntervalCategoryDataset dataset = getCategoryDataset(project);

        //Create chart
        JFreeChart chart = ChartFactory.createGanttChart("Project Schedule", "Tasks", null, dataset);

        //Set up panel and plot
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
        CategoryPlot plot = chart.getCategoryPlot();
        DateAxis axis = (DateAxis) plot.getRangeAxis();

        //Set range of axis
        Date startDateOfProject = Date.from(project.getActualStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDateOfProject = Date.from(project.getProjectedCompletedDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        axis.setRange(startDateOfProject,endDateOfProject);
    }

    private IntervalCategoryDataset getCategoryDataset(Project project){

        for (Task task: project.returnTasksSortedByStartDate()) {
            projectedDate.add(new ChartTask(task.getName(),
                    Date.from(task.getActualStartDate().atStartOfDay().toInstant(ZoneOffset.UTC)),
                    Date.from(task.getProjectedCompletedDate().atStartOfDay().toInstant(ZoneOffset.UTC))));
        }

        dataset.add(projectedDate);


        for (Task task: project.returnTasksSortedByStartDate()) {
            completedDate.add(new ChartTask(task.getName(),
                    Date.from(task.getActualStartDate().atStartOfDay().toInstant(ZoneOffset.UTC)),
                    Date.from(task.getActualCompletedDate().atStartOfDay().toInstant(ZoneOffset.UTC))));
        }

        dataset.add(completedDate);
        return dataset;

    }
}
