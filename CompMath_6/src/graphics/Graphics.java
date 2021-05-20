package graphics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import main.Writer;

public class Graphics extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Graphics");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("x");
        yAxis.setLabel("y");
        //creating the chart
        final LineChart<Number,Number> lineChart =
                new LineChart<Number,Number>(xAxis,yAxis);

        lineChart.setTitle("Graphics");

        XYChart.Series series = new XYChart.Series();
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Точное решение");
        series.setName("Вычисленное решение");

        XYChart.Data data;

        for(int i = 0; i < Writer.x.length; i++) {
            data = new XYChart.Data(Writer.x[i], Writer.y[i]);
            series.getData().add(data);
        }

        for(int i = 0; i < Writer.results.length; i++) {
            data = new XYChart.Data(Writer.x[i], Writer.results[i]);
            series1.getData().add(data);
        }

        Scene scene  = new Scene(lineChart,1000,1000);
        lineChart.getData().add(series);
        lineChart.getData().add(series1);
        stage.setScene(scene);
        stage.show();
    }

}
