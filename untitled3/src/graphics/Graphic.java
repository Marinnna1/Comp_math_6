package graphics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import main.Main;

public class Graphic extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Graphic");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("x");
        yAxis.setLabel("y");
        //creating the chart
        final LineChart<Number,Number> lineChart =
                new LineChart<Number,Number>(xAxis,yAxis);

        lineChart.setTitle("График функции распределения");
        //defining a series
        XYChart.Series[] series = new XYChart.Series[Main.x.length - 1];


        XYChart.Data data;
        XYChart.Data data1;
        for(int i = 0; i < Main.x.length -1; i++){
            series[i] = new XYChart.Series();
            data = new XYChart.Data(Main.x[i], Main.y[i]);
            data1 = new XYChart.Data(Main.x[i + 1], Main.y[i]);
            series[i].getData().add(data);
            series[i].getData().add(data1);
            lineChart.getData().add(series[i]);
        }
        Scene scene  = new Scene(lineChart,1000,1000);
        stage.setScene(scene);
        stage.show();

    }
}
