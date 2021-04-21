package main;

import functions.FirstFunction;
import functions.Function;
import functions.SecondFunction;
import functions.ThirdFunction;
import methods.ChordMethod;
import methods.NewtonMethod;
import methods.SimpleIterationMethod;
import visualization.Graphic;

import java.io.*;

public class Reader {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static String equation;
    static Function f;

    public static void main(String[] args) throws Exception {
        System.out.println("Напишите 0, если вводите данные из файла, или 1 если с консоли");
        String answer = reader.readLine();
        while (!(answer.trim().equals("0") || answer.trim().equals("1"))) {
            System.out.println("Напишите 0, если вводите данные из файла или 1 если с консоли");
            answer = reader.readLine();
        }
        if (answer.trim().equals("0")) {
            readFromFile();
        } else {
            readFromConsole();
        }
    }

    public static void readFromConsole() throws Exception {
        reader = new BufferedReader(new InputStreamReader(System.in));
        equation = getEquation();

        Function f = getFunction(equation);

        String method = getMethod();

        double a = getA();
        double b = getB(a);
        double e = getE();

        getXCount(a,b);
        chooseMethod(method, a, b, e, f);
        showGraphic();
    }

    public static void readFromFile() throws IOException {
        System.out.println("Введите путь к файлу");

        String fileName = reader.readLine();
        try {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            reader = new BufferedReader(fileReader);
        }
        catch (Exception e) {
        }
        try {
            equation = getEquation();

            Function f = getFunction(equation);

            String method = getMethod();

            double a = getA();
            double b = getB(a);
            double e = getE();
            chooseMethod(method, a, b, e, f);
            showGraphic();
        }
        catch (Exception e) {
            System.out.println("Не удалось обработать файл");
        }

    }



    public static void chooseMethod(String method, double a, double b, double e, Function f) throws Exception {
        if(method.equals("1")) {
            ChordMethod.start(a, b, e, f);
        }
        else if(method.equals("2")) {
            NewtonMethod.start(a, b, e, f);
        }
        else {
            SimpleIterationMethod.start(a, b, e, f);
        }
    }

    public static String getEquation() throws IOException {
        do {
            System.out.println("Введите номер уравнения");
            System.out.println("1) x3 + 5x2 + 6x");
            System.out.println("2) 3x3 + 1.7x2 - 15.42x + 6.89");
            System.out.println("3) sin(x) + 5x");
            equation = reader.readLine();
        }while(!(equation.equals("1") || equation.equals("2") || equation.equals("3")));
        return equation;
    }

    public static Function getFunction(String equation) {
        if(equation.equals("1")) {
            f = new FirstFunction();
        }
        else if(equation.equals("2")) {
            f = new SecondFunction();
        }
        else {
            f = new ThirdFunction();
        }
        return f;
    }

    public static String getMethod() throws IOException {
        String method;
        do {
            System.out.println("Введите номер метода, которым хотите его решить");
            System.out.println("1) Метод хорд");
            System.out.println("2) Метод Ньютона (касательных)");
            System.out.println("3) Метод простых итераций");
            method = reader.readLine();
        }while(!(method.equals("1") || method.equals("2") || method.equals("3")));
        return method;
    }

    public static double getA() {
        double a;
        while(true) {
            System.out.println("Введите левую границу интервала a");
            try {
                a = Double.parseDouble(reader.readLine());
                break;
            }
            catch (Exception ignored) {
            }
        }
        return a;
    }

    public static double getB(double a) {
        double b;
        while(true) {
            System.out.println("Введите правую границу интервала b");
            try {
                b = Double.parseDouble(reader.readLine());
                if(a < b) {
                    break;
                }
                System.out.println("Левая граница должна быть меньше правой");
            }
            catch (Exception ignored) {
            }
        }
        return b;
    }

    public static double getE() {
        double e;
        while(true) {
            System.out.println("Введите приближение e (например 0.01)");
            try {
                e = Double.parseDouble(reader.readLine());
                if(e < 1 && e > 0) {
                    break;
                }
            }
            catch (Exception ignored) {
            }
        }
        return e;
    }

    public static void getXCount(double a, double b) {
        int realCount = 0;
        double step = 0.01 * (b-a);
        for(double i = a; i <= b - step; i+=step) {
            if(f.f(i) * f.f(i + step) < 0) {
                realCount++;
            }
        }
        System.out.println("Количество корней на промежутке: " + realCount);
    }



    public static void showGraphic() {
        Graphic graphic = new Graphic();
        graphic.launch(Graphic.class, null);
    }
}