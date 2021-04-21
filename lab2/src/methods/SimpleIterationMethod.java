package methods;

import functions.*;

import java.text.DecimalFormat;

public class SimpleIterationMethod {
    private static int it = 0;
    private static Function function;

    public static void start(double a, double b, double e, Function f) {
        function = f;
        check(a,b,e);
    }

    public static void check(double a, double b, double e) {
        double q = -1;
        double border = 0;
        double numberPhi = 0;
        if(Math.abs(function.getDerivativeFirstPhi(a)) < 1) {
            q = Math.abs(function.getDerivativeFirstPhi(a));
            border = a;
            numberPhi = 1;
            System.out.println(function.getDerivativeFirstPhi(a));
            System.out.println(function.getDerivativeFirstPhi(b));
        }
        else if(Math.abs(function.getDerivativeFirstPhi(b)) < 1) {
            q = Math.abs(function.getDerivativeFirstPhi(b));
            border = b;
            numberPhi = 1;
            System.out.println(function.getDerivativeFirstPhi(a));
            System.out.println(function.getDerivativeFirstPhi(b));
        }
        else if(Math.abs(function.getDerivativeSecondPhi(a)) < 1) {
            q = Math.abs(function.getDerivativeSecondPhi(a));
            border = a;
            numberPhi = 2;
            System.out.println(function.getDerivativeSecondPhi(a));
            System.out.println(function.getDerivativeSecondPhi(b));
        }
        else if(Math.abs(function.getDerivativeSecondPhi(b)) < 1) {
            q = Math.abs(function.getDerivativeSecondPhi(b));
            border = b;
            numberPhi = 2;
            System.out.println(function.getDerivativeSecondPhi(a));
            System.out.println(function.getDerivativeSecondPhi(b));
        }
        else if(Math.abs(function.getDerivativeThirdPhi(a)) < 1) {
            q = Math.abs(function.getDerivativeThirdPhi(a));
            border = a;
            numberPhi = 3;
            System.out.println(function.getDerivativeThirdPhi(a));
            System.out.println(function.getDerivativeThirdPhi(b));
        }
        else if(Math.abs(function.getDerivativeThirdPhi(b)) < 1) {
            q = Math.abs(function.getDerivativeThirdPhi(b));
            border = b;
            numberPhi = 3;
            System.out.println(function.getDerivativeThirdPhi(a));
            System.out.println(function.getDerivativeThirdPhi(b));

        }


        if(q == -1) {
            System.out.println("некорректно");
            System.exit(0);
        }
        else {
        //    System.out.println("q = " + q);
            makeTable();
            if(q <= 0.5) {
                System.out.println(parse(method_simple_iteration(border, e, numberPhi)));
            }
            else {
                System.out.println(parse(method_simple_iteration(border,(1-q)*e/q, numberPhi)));
            }
        }


    }

    public static double method_simple_iteration(double border, double e, double numberPhi) {
        double x_reserve = border;
        double x = x_reserve;
        double phiX;
        do {
            x_reserve = x;
            if (numberPhi == 1) {
                x = function.getFirstPhi(x_reserve);
                phiX = function.getFirstPhi(x);
            } else if (numberPhi == 2) {
                x = function.getSecondPhi(x_reserve);
                phiX = function.getSecondPhi(x);
            } else {
                x = function.getThirdPhi(x_reserve);
                phiX = function.getThirdPhi(x);
            }
            System.out.format("|%6s    |%12s|%14s|%16s|%13s|%17s|%n", it, parse(x_reserve), parse(x), parse(phiX),parse(function.f(x)), parse(Math.abs(x-x_reserve)));
            it++;
        }while (Math.abs(x - x_reserve) > e);
        System.out.format("-----------------------------------------------------------------------------------------%n");
        return x;
    }


    public static void makeTable() {
        System.out.format("-----------------------------------------------------------------------------------------%n");
        System.out.format("| Итерация |    x(i)    |    x(i+1)    |    ф(x(i+1))   |  f(x(i+1))  | |x(i+1) - x(i)| |%n");
        System.out.format("-----------------------------------------------------------------------------------------%n");
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