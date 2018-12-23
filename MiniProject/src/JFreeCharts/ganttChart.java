package JFreeCharts;

import Classes.Project;
import Classes.Task;
import net.miginfocom.swing.MigLayout;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

public class ganttChart extends JFrame {


    public JFreeChart chart;
    public ChartPanel panel;
    TaskSeries series1 = new TaskSeries("Task");
    TaskSeries series2 = new TaskSeries("Week");

    public ganttChart(Project project){

        super(project.getProjectName());

        IntervalCategoryDataset dataset = getCategoryDataset(project);

        chart = ChartFactory.createGanttChart("Project Schedule", "Tasks", "Weeks", dataset);

        CategoryPlot plot = chart.getCategoryPlot();
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
        axis.setMaximumDate(new Date());

        panel = new ChartPanel(chart);
        setContentPane(panel);
        setVisible(true);
        setSize(800, 600);

        }

        private  IntervalCategoryDataset getCategoryDataset(Project project){

            for (Task task: project.getTaskList()) {
                Instant instant = Instant.from(task.getActualStartDate().atStartOfDay(ZoneId.of("GMT")));
                Date startDate = Date.from(instant);
                instant = Instant.from(task.getProjectedCompletedDate().atStartOfDay(ZoneId.of("GMT")));
                Date projectedCompletedDate = Date.from(instant);

                series1.add(new chartTask(task.getName(), startDate, projectedCompletedDate));
               // series2.add();

            }

            TaskSeriesCollection dataset = new TaskSeriesCollection();

            dataset.add(series1);
            return dataset;

        }

        private static JFrame MAIN_FRAME = new JFrame();
         private static JPanel guinew = new JPanel();



    public static void setupMainFrame() {
        MAIN_FRAME.setLayout(new MigLayout(
                "insets 0, gap 0, wrap", // Layout Constraints
                "[fill, grow]", // Column constraints
                "[fill, grow]")); // Row constraints
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

    }

