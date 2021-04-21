package graphics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import main.Reader;
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

        lineChart.setTitle("points and f(x) = " + Writer.bestFunction);
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("points");
        //populating the series with data
        XYChart.Data data;
        for(int i = 0; i < Reader.x.length; i++) {
            data = new XYChart.Data(Reader.x[i], Reader.y[i]);
            series.getData().add(data);
        }

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("f(x) = " + Writer.bestFunction);
        //populating the series with data

        double currentX = getMinX();
        double maxX = getMaxX();

        while((currentX - maxX) < 1) {
            data = new XYChart.Data(currentX, Writer.function.calculateY(currentX));
            series2.getData().add(data);
            currentX++;
        }


       // for(int i = 0; i < Reader.x.length; i++) {
    //        data = new XYChart.Data(Reader.x[i], Writer.function.calculateY(Reader.x[i]));
    //        series2.getData().add(data);
    //    }


        Scene scene  = new Scene(lineChart,1000,1000);
        lineChart.getData().add(series);
        lineChart.getData().add(series2);
        series.getNode().setVisible(false);

        stage.setScene(scene);
        stage.show();

    }

    private double getMinX() {
        double minX = Reader.x[0];
        for(int i = 1; i < Reader.x.length; i++) {
            if(minX > Reader.x[i]) {
                minX = Reader.x[i];
            }
        }
        return minX;
    }

    private double getMaxX() {
        double maxX = Reader.x[0];
        for(int i = 1; i < Reader.x.length; i++) {
            if(maxX < Reader.x[i]) {
                maxX = Reader.x[i];
            }
        }
        return maxX;
    }



}
