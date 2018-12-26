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

    private static JFrame MAIN_FRAME = new JFrame();
    private static JPanel guinew = new JPanel();
    public JFreeChart chart;
    public ChartPanel panel;
    private TaskSeries series1 = new TaskSeries("Task");

    public GanttChart(Project project){

        //Set title of project chart
        super(project.getProjectName());

        //Create dataset
        IntervalCategoryDataset dataset = getCategoryDataset(project);

        //Create chart
        JFreeChart chart = ChartFactory.createGanttChart("Project Schedule", "Tasks", null, dataset);

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);

        CategoryPlot plot = chart.getCategoryPlot();

        DateAxis axis = (DateAxis) plot.getRangeAxis();

        Date startDateOfProject = Date.from(project.getActualStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDateOfProject = Date.from(project.getProjectedCompletedDate().atStartOfDay(ZoneId.systemDefault()).toInstant());

        axis.setRange(startDateOfProject,endDateOfProject);



       /* CategoryPlot plot = chart.getCategoryPlot();
        Shape shape = new Ellipse2D.Double(-.5, -.5, 1, 1 );
        plot.setRenderer(new BarRenderer());
        BarRenderer barRenderer = (BarRenderer) plot.getRenderer();
        CategoryItemRenderer renderer = (CategoryItemRenderer) barRenderer;

        renderer.setBaseShape(shape);
        renderer.setSeriesShape(0, shape);
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesShape(1, shape);
        renderer.setSeriesPaint(1, Color.GRAY);
        renderer.setSeriesShape(2, shape);
        renderer.setSeriesPaint(2, Color.CYAN);

        DateAxis axis = (DateAxis) plot.getRangeAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));

        Instant instant = Instant.from(project.getActualStartDate().atStartOfDay(ZoneId.of("GMT")));
        Date projectStart = Date.from(instant);
        axis.setMinimumDate(projectStart);
        instant = Instant.from(project.getProjectedCompletedDate().atStartOfDay(ZoneId.of("GMT")));
        Date projectEndDate = Date.from(instant);
        axis.setMaximumDate(projectEndDate);


        panel = new ChartPanel(chart);
        setContentPane(panel);
        setVisible(true);
        setSize(800, 600);*/

        }

        private IntervalCategoryDataset getCategoryDataset(Project project){

            for (Task task: project.getTaskList()) {
                series1.add(new ChartTask(task.getName(),
                        Date.from(task.getActualStartDate().atStartOfDay().toInstant(ZoneOffset.UTC)),
                        Date.from(task.getProjectedCompletedDate().atStartOfDay().toInstant(ZoneOffset.UTC))));

/*                Date.from(task.getActualStartDate().atStartOfDay().toInstant(ZoneOffset.UTC));
                Instant instant = Instant.from(task.getActualStartDate().atStartOfDay(ZoneId.of("GMT")));
                Date startDate = Date.from(instant);
                instant = Instant.from(task.getProjectedCompletedDate().atStartOfDay(ZoneId.of("GMT")));
                Date projectedCompletedDate = Date.from(instant);

                series1.add(new ChartTask(task.getName(), startDate, projectedCompletedDate));*/
               // series2.add();

            }

            TaskSeriesCollection dataset = new TaskSeriesCollection();
            dataset.add(series1);
            return dataset;

        }





    /*public static void setStage() {
        MAIN_FRAME.setLayout();
        MAIN_FRAME.getContentPane().setBackground(Color.black);
        MAIN_FRAME.setPreferredSize(new Dimension(1024, 768));
        MAIN_FRAME.setSize(1024,768);

        MAIN_FRAME.setTitle("Dynamic Data Annotator");

        MAIN_FRAME.setResizable(false);
        MAIN_FRAME.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //add them
        MAIN_FRAME.add(guinew);
        MAIN_FRAME.revalidate();
        MAIN_FRAME.repaint();

        //need to add a loading popup
        MAIN_FRAME.setVisible(true);
    }
*/
    }

