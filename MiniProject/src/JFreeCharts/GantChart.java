/*package JFreeCharts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.TimePeriod;

import javax.swing.*;
import java.sql.Date;


public class GantChart extends JFrame {

    private Classes.Project Project;

    private String title;

    public GantChart(String title) {

        this.title = title;


        IntervalCategoryDataset dataset = categoryDatase(Project);

        JFreeChart chart = ChartFactory.createGanttChart(
                "Schedule", "Weeks", "Tasks", dataset);

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);


            TaskSeries series1 = new TaskSeries("Start Date");

            for (Classes.Task t : project.getTaskList()) {

                String description = t.getName();
                TimePeriod startDate = (TimePeriod) Date.valueOf(t.getActualStartDate());

                series1.add(new Task(description, startDate));

            }


            TaskSeriesCollection dataset = new TaskSeriesCollection();
            dataset.add(series1);
            return dataset;
/*
        series1.add(new Task());
        series1.add(new Task("Requirement",
                Date.from(LocalDate.of(2018, 11, 8).atStartOfDay().toInstant(ZoneOffset.UTC)),
                Date.from(LocalDate.of(2018, 11, 28).atStartOfDay().toInstant(ZoneOffset.UTC))));

        series1.add(new Task("Create Trello Workspace", Date.from(LocalDate.of(2018, 11, 9)
                .atStartOfDay().toInstant(ZoneOffset.UTC)), Date.from(LocalDate.of(2018, 11, 10)
                .atStartOfDay().toInstant(ZoneOffset.UTC))
        ));

        series1.add(new Task("Setup GitHub", Date.from(LocalDate.of(2018, 11, 10).atStartOfDay().toInstant(ZoneOffset.UTC)),
                Date.from(LocalDate.of(2018, 11, 11).atStartOfDay().toInstant(ZoneOffset.UTC))
        ));

        series1.add(new Task("Meeting", Date.from(LocalDate.of(2018, 11, 14).atStartOfDay().toInstant(ZoneOffset.UTC)),
                Date.from(LocalDate.of(2018, 11, 15).atStartOfDay().toInstant(ZoneOffset.UTC))
        ));

        series1.add(new Task("Designing", Date.from(LocalDate.of(2018, 11, 15).atStartOfDay().toInstant(ZoneOffset.UTC)),
                Date.from(LocalDate.of(2018, 11, 25).atStartOfDay().toInstant(ZoneOffset.UTC))
        ));*/





