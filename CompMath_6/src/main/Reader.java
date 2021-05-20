package main;

import fuctions.*;

import java.io.BufferedReader;
import java.io.IOException;
import methods.*;
import java.io.InputStreamReader;

public class Reader {

    static BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        Function function = getFunction();

        Method method = getMethod();

        System.out.println("Введите интервал дифференцирования");
        double a = getA();
        double b = getB(a);

        double y0 = getY0(a);

        double h = getH(a, b);

        double e = getE();


        function.countConstant(a, y0);
        method.solve(function, a, b, y0, h);
        Writer.printResults(function, method, e);
    }

    private static Function getFunction() throws IOException {
        FirstFunction firstFunction = new FirstFunction();
        SecondFunction secondFunction = new SecondFunction();
        System.out.println("Напишите номер функции, которую ходите решить");
        System.out.println("1) " + firstFunction.getEquation());
        System.out.println("2) " + secondFunction.getEquation());


        String answer;
        do {
            answer = consoleReader.readLine();
        } while (!(answer.equals("1") || answer.equals("2")));

        if (answer.equals("1")) {
            return firstFunction;
        }
        return secondFunction;

    }

    private static double getA() {
        Double a = null;
        do {
            System.out.println("Введите a");
            try {
                a = Double.parseDouble(consoleReader.readLine());
            } catch (Exception ignored) {
            }
        } while (a == null);

        return a;
    }

    private static Method getMethod() throws IOException {
        System.out.println("Напишите номер функции, которую ходите решить");
        System.out.println("1) Метод Рунге-Кутта 4-го порядка");
        System.out.println("2) Метод Адамса");

        String answer;
        do {
            answer = consoleReader.readLine();
        } while (!(answer.equals("1") || answer.equals("2")));

        if (answer.equals("1")) {
            return new RungeKuttaMethod();
        }
        return new AdamsMethod();
    }

    private static double getB(double a) {
        Double b = null;

        do {
            System.out.println("Введите b");
            try {
                b = Double.parseDouble(consoleReader.readLine());
                if (b < a) {
                    b = null;
                }
            } catch (Exception ignored) {
            }
        } while (b == null);

        return b;
    }

    private static double getY0(double a) {
        Double y0 = null;
        do {
            System.out.println("Введите значение функции в точке a");
            try {
                y0 = Double.parseDouble(consoleReader.readLine());
            } catch (Exception ignored) {
            }
        } while (y0 == null);
        return y0;
    }

    private static double getH(double a, double b) {
        Double h = null;

        do {
            System.out.println("Введите шаг h");
            try {
                h = Double.parseDouble(consoleReader.readLine());
                if (h > (b - a) || h < 0) {
                    h = null;
                }
            } catch (Exception ignored) {
            }
        }
        while (h == null);
        return h;
    }

    private static double getE() throws IOException {
        Double e = null;
        do {
            System.out.println("Введите точность вычисления");
            try {
                e = Double.parseDouble(consoleReader.readLine());
                if (!(e > 0 && e < 1)) {
                    e = null;
                }
            }
            catch (Exception ignored) {
            }
        }
        while (e == null);
        return e;
    }
}
