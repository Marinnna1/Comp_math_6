package graphics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import main.Main;

public class Histogram extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Histogram");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("x");
        yAxis.setLabel("y");
        //creating the chart
        final LineChart<Number,Number> lineChart =
                new LineChart<Number,Number>(xAxis,yAxis);

        lineChart.setTitle("Гистограмма частот");
        //defining a series
        XYChart.Series series = new XYChart.Series();

        XYChart.Data data;

        for(int i = 0; i < Main.count - 2; i++) {
            data = new XYChart.Data(Main.x[i], Main.y[i]);
            series.getData().add(data);
        }

        Scene scene  = new Scene(lineChart,1000,1000);
        lineChart.getData().add(series);
        stage.setScene(scene);
        stage.show();
    }
}
