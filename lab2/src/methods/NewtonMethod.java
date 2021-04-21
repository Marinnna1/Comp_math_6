package methods;

import functions.*;

import java.text.DecimalFormat;

public class NewtonMethod {

    private static Function function;
    private static int it = 0;

    public static void start(double a, double b, double e, Function f) {
        function = f;
        check(a, b, e);
    }

    public static void check(double a, double b, double e) {
        double x = 0;
        double x0 = 0;
        double i = a;
        boolean sign1 = function.firstDerivative(a) > 0;
        boolean sign2 = function.secondDerivative(a) > 0;
        while(i < b) {
            i += 0.01;
            if(function.firstDerivative(i) > 0 != sign1 || function.secondDerivative(i) > 0 != sign2){
                if(function.f(a) * function.f(b) >= 0) {
                    System.out.println("Функция на концах отрезка должна иметь разные знаки");
                    System.exit(0);
                }
            }
        }
        if(function.secondDerivative(a) * function.f(a) > 0) {
            makeTable();
            x0 = a;
            x = method_newton(x0, e);
        }
        else if(function.secondDerivative(b) * function.f(b) > 0){
            makeTable();
            x0 = b;
            x = method_newton(x0, e);
        }
        else {
            System.out.println("a b некорректны");
            System.exit(0);
        }
        System.out.println(parse(x));
    }

    public static void makeTable() {
        System.out.format("----------------------------------------------------------------------------------------%n");
        System.out.format("| Итерация |    x(n)    |    f(x(n))    |    f'(x(n))   |  (x(n+1))  | |x(n+1) - x(n)| |%n");
        System.out.format("----------------------------------------------------------------------------------------%n");
    }

    public static double method_newton(double x0, double e) {
        double x_current = x0;
        double x_next;
        x_next = x_current - function.f(x_current)/function.firstDerivative(x_current);
        System.out.format("|%6s    |%12s|%15s|%15s|%12s|%17s|%n", it, parse(x_current), parse(function.f(x_current)), parse(function.firstDerivative(x_current)), parse(x_next), "-");
        it++;
        do {
            x_current = x_next;
            x_next = x_current - function.f(x_current)/function.firstDerivative(x_current);
            System.out.format("|%6s    |%12s|%15s|%15s|%12s|%17s|%n", it, parse(x_current), parse(function.f(x_current)), parse(function.firstDerivative(x_current)), parse(x_next), parse(Math.abs(x_current - x_next)));
            it++;
        } while (Math.abs(x_current - x_next) > e && Math.abs(function.f(x_next)) > e);
        System.out.format("----------------------------------------------------------------------------------------%n");
      //  System.out.println(Math.abs(function.f(x_next)));
     //   System.out.println(it);
        return x_next;
    }


    public static String parse(double number) {
        String pattern = "##0.000";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        if(decimalFormat.format(number).equals("-0,000")){
            return decimalFormat.format(number).replace("-", "");
        }
        return decimalFormat.format(number);
    }

}