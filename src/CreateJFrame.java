import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;

import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;

public class CreateJFrame extends JFrame {
    private XYDataset dataset;
    private double Ymax;
    private double YMin;

    public CreateJFrame(XYDataset dataset, double YMax, double YMin, String chartTitle) {
        super("XY Line Chart Example with JFreechart");
        this.dataset = dataset;
        this.Ymax = YMax;
        this.YMin = YMin;
        JPanel chartPanel = createChartPanel(chartTitle);
        add(chartPanel, BorderLayout.CENTER);

        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private JPanel createChartPanel(String chartTitle) {
        String xAxisLabel = "X";
        String yAxisLabel = "Y";
        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
                xAxisLabel, yAxisLabel, dataset, PlotOrientation.VERTICAL,true,false,false);
        XYPlot plot = chart.getXYPlot();
        NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
        xAxis.setTickUnit(new NumberTickUnit(5));

        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setRange(YMin, Ymax);
        return new ChartPanel(chart);
    }
}
