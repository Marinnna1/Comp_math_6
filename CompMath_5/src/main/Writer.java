package main;

import graphics.Graphics;

import java.text.DecimalFormat;

public class Writer {

    public static double[] x;
    public static double[] y;
    public static double x_0;
    public static double result;

    static void printResults(double res, double[] inputX, double[] inputY, double x0) {
        x = inputX;
        y = inputY;
        x_0 = x0;
        result = res;
        System.out.println("Приближенное значение функции в данной точке: " + parse(res));
        showGraphic();
    }

    private static void showGraphic() {
        Graphics graphics = new Graphics();
        graphics.launch(Graphics.class, null);
    }

    private static String parse(double number) {
        String pattern = "##0.0000";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        if(decimalFormat.format(number).equals("-0,0000")){
            return decimalFormat.format(number).replace("-", "");
        }
        return decimalFormat.format(number);
    }
}
