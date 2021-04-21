package visualization;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import main.Reader;

import java.awt.*;

public class Graphic extends Application {
    static int HEIGHT = 720;
    static int WIDTH = 1080;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group group = new Group();

        group.getChildren().addAll(
                new Line(0,HEIGHT/2,WIDTH,HEIGHT/2),
                new Line(WIDTH/2,0,WIDTH/2,HEIGHT)
        );
        int count = 0;
        for(int i = -100; i <= 100; i+=10) {
            group.getChildren().add(new Line(40 + count*50, 355, 40 + count*50, 365));
            group.getChildren().add(new Line(535, 10 + count*35, 545, 10 + count*35));
            Text text = new Text(Integer.toString(i));
            text.setLayoutX(40 + count*50);
            text.setLayoutY(375);
            Text text1 = new Text(Integer.toString(-i));
            text1.setLayoutX(555);
            text1.setLayoutY(10 + count*35);
            group.getChildren().add(text);
            group.getChildren().add(text1);
            count++;
        }


        Operation operation = choseOperation();


       for(int i = -WIDTH/2; i < WIDTH/2 - 1; i++){
           Line line = new Line(i+WIDTH/2, -operation.execute(i/3.5)+HEIGHT/2, (i+1)+WIDTH/2, (-operation.execute((i+1)/3.5))+HEIGHT/2);
           group.getChildren().add(line);
       }

        Scene scene = new Scene(group,WIDTH, HEIGHT);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static Operation choseOperation() {
        Operation operation;
        if(Reader.equation.equals("1")) {
            operation = x -> Math.pow(x[0], 3) + 5*Math.pow(x[0], 2) + 6*x[0];
        }
        else if(Reader.equation.equals("2")) {
            operation = x -> (3*Math.pow(x[0],3)+ 1.7*Math.pow(x[0],2) - 15.42*x[0] + 6.89);
        }
        else {
            operation = x -> Math.sin(x[0]) + 5*x[0];
        }
        return operation;
    }

}