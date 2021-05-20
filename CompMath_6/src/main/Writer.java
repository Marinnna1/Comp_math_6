package main;

import fuctions.*;
import graphics.Graphics;
import methods.*;

import java.text.DecimalFormat;

public class Writer {

    public static double[] x;
    public static double[] y;
    public static double[] results;
    public static Function function;

    static void printResults(Function f, Method method, double e) {
        function = f;
        makeTable();
        addData(method, e);
        showGraphic();
    }

    private static void makeTable() {
        System.out.format("--------------------------------------------------------------------------------%n");
        System.out.format("| i |    x(i)    |    Вычисленное значение    | Точное решение |  Погрешность  |%n");
        System.out.format("--------------------------------------------------------------------------------%n");
    }

    private static void addData(Method method, double e) {

        if(method instanceof RungeKuttaMethod) {
            x = RungeKuttaMethod.x;
            y = RungeKuttaMethod.y;
        }
        else if(method instanceof AdamsMethod) {
            x= AdamsMethod.x;
            y = AdamsMethod.y;
        }
        results = new double[x.length];
        for(int i = 0; i < x.length; i++) {
            System.out.format("|%3s|  %8s  |    %20s    | %14s |  %11s  |%n" ,i, parse(x[i], e), parse(y[i], e), parse(function.getSolveFunction(x[i]),e), getAccuracy(Double.parseDouble(parse(y[i], e)), function.getSolveFunction(x[i])));
            x[i] = Double.parseDouble(parse(x[i], e));
            y[i] = Double.parseDouble(parse(y[i], e));
            results[i] = function.getSolveFunction(x[i]);
        }
        System.out.format("--------------------------------------------------------------------------------%n");

    }

    private static String parse(double number, double e) {
        int tmp = (int) (1 / e);
        int count = 0;
        while(tmp >= 10) {
            tmp /= 10;
            count++;
        }

        StringBuilder pattern = new StringBuilder("##0.");
        while(count > 0) {
            pattern.append("0");
            count--;
        }
        DecimalFormat decimalFormat = new DecimalFormat(pattern.toString());
        return decimalFormat.format(number).replace(",", ".");
    }


    private static void showGraphic() {
        Graphics graphics = new Graphics();
        graphics.launch(Graphics.class, null);
    }

    private static String getAccuracy(double y, double result) {
        String pattern = "##0.000000";
        double number = Math.abs(result - y);
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(number).replace(",", ".");
    }

}
