package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import functions.FirstFunction;
import functions.Function;
import functions.SecondFunction;
import functions.ThirdFunction;
import methods.*;

public class Reader {

    private static BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
    private static double[] x;
    private static double[] y;

    public static void main(String[] args) throws IOException {
        Function function = null;
        String inputOption = getInputOption();
        if(inputOption.equals("1")) {
            getPoints();
        }
        else {
            function = getFunction();
        }
        double x0 = getX0();
        Method method = getMethod();
        if(inputOption.equals("0")) {
            fillPoints(function, x0);
        }
        double result = method.solve(x, y, x0);
        Writer.printResults(result, x, y, x0);
    }

    private static String getInputOption() throws IOException {
        String answer;
        do {
            System.out.println("¬ведите 1, если будете вводить значени€ x и y, 0 - если будете выбирать функцию");
            answer = consoleReader.readLine();
        } while (!(answer.equals("0") || answer.equals("1")));
        return answer;
    }

    private static Method getMethod() throws IOException {
        LagrangePolynomial lagrangePolynomial = new LagrangePolynomial();
        NewtonPolynomial newtonPolynomial = new NewtonPolynomial();
        String answer;
        do {
            System.out.println("¬ведите номер метода, которым хотите решить");
            System.out.println("1) ћногочлен Ћагранжа");
            System.out.println("2) ћногочлен Ќьютона с разделенными разност€ми");
            answer = consoleReader.readLine();
        } while (!(answer.equals("1") || answer.equals("2")));
        if(answer.equals("1")) {
            return lagrangePolynomial;
        }
        return newtonPolynomial;
    }

    private static void getPoints() throws IOException {
        int pointsCount = 0;
        do {
            System.out.println("¬ведите количество точек");
            try {
                pointsCount = Integer.parseInt(consoleReader.readLine());
            }
            catch (Exception ignored) {
            }
        } while (!(pointsCount > 0));

        String answer;
        x = new double[pointsCount];
        y = new double[pointsCount];
        int index = 0;
        while(pointsCount > 0) {
            System.out.println("¬ведите x и y через пробел");
            answer = consoleReader.readLine();
            String[] coords = answer.split(" ");
            try {
                x[index] = Double.parseDouble(coords[0]);
                y[index] = Double.parseDouble(coords[1]);
            }catch (Exception e) {
                continue;
            }
            index++;
            pointsCount--;
        }
    }

    private static Function getFunction() throws IOException {
        String answer;
        FirstFunction firstFunction = new FirstFunction();
        SecondFunction secondFunction = new SecondFunction();
        ThirdFunction thirdFunction = new ThirdFunction();
        do {
            System.out.println("¬ведите номер выбранной вами функции");
            System.out.println("1) " + firstFunction.getTitle());
            System.out.println("2) " + secondFunction.getTitle());
            System.out.println("3) " + thirdFunction.getTitle());
            answer = consoleReader.readLine();
        } while (!(answer.equals("1") || answer.equals("2") || answer.equals("3")));
        if(answer.equals("1")) {
            return firstFunction;
        }
        else if(answer.equals("2")) {
            return secondFunction;
        }
        return thirdFunction;
    }

    private static double getX0() throws IOException {
        Double x0 = null;
        do {
            System.out.println("¬ведите точку в которой нужно найти приближенное значение функции");
            try {
                x0 = Double.parseDouble(consoleReader.readLine());
            }
            catch (Exception ignored) {
            }
        } while (x0 == null);
        return x0;
    }

    private static void fillPoints(Function function, double x0) {
        x = new double[4];
        y = new double[4];
        x[0] = x0 - 2;
        x[1] = x0 - 1;
        x[2] = x0 + 1;
        x[3] = x0 + 2;

        y[0] = function.f(x0 - 2);
        y[1] = function.f(x0 - 1);
        y[2] = function.f(x0 + 1);
        y[3] = function.f(x0 + 2);
    }
}
