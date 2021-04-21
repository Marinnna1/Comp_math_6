package methods;

import functions.*;


public class RectangleMethod implements Method {

    private Function function;

    public RectangleMethod(Function function) {
        this.function = function;
    }

    @Override
    public void doMethod(double a, double b, int n, int accuracy) {
        System.out.print("Значение интеграла по методу левых прямоугольников: ");
        left_rectangle_method(a, b, n, accuracy);
        System.out.print("Значение интеграла по методу правых прямоугольников: ");
        right_rectangle_method(a, b, n, accuracy);
        System.out.print("Значение интеграла по методу средних прямоугольников: ");
        middle_rectangle_method(a, b, n, accuracy);
    }

    @Override
    public double getAccuracyOfNumericalIntegration(double a, double b, int n) {
        return Math.abs(Math.max(function.secondDerivative(a), function.secondDerivative(b))) * Math.pow((b - a), 3) / (24 * Math.pow(n, 2));
    }


    private void left_rectangle_method(double a, double b, int n, int accuracy) {
            double h = (b - a) / n;
            int count = 0;
            double result = 0;
            while(n > 0) {
                if(a + h * count == 0 && (function instanceof  SecondFunction || function instanceof  ThirdFunction)) {
                    result += parse((function.f(a + h * count + 0.001) + function.f(a + h * count - 0.001))/2, accuracy);
                }
                else {
                    result += parse(function.f(a + h * count), accuracy);
                }
                count++;
                n--;

            }
            result *= h;
            System.out.println(parse(result, accuracy));
        }

        private void right_rectangle_method(double a, double b, int n, int accuracy) {
            double h = (b - a) / n;
            int count = 1;
            double result = 0;
            while(n > 0) {
                if(a + h * count == 0 && (function instanceof  SecondFunction || function instanceof  ThirdFunction)) {
                    result += parse((function.f(a + h * count + 0.001)+function.f(a + h * count - 0.001))/2, accuracy);
                }
                else {
                    result += parse(function.f(a + h * count), accuracy);
                }
                count++;
                n--;
            }
            result *= h;
            System.out.println(parse(result, accuracy));
        }

        private void middle_rectangle_method(double a, double b, int n, int accuracy) {
            double h = (b - a) / n;
            int count = 0;
            double result = 0;
            double previous_result = a + h/2;
        while(n > 0) {
            if(previous_result + h * count == 0 && (function instanceof  SecondFunction || function instanceof  ThirdFunction)) {
                result += parse((function.f(previous_result + h * count + 0.001) + function.f(previous_result + h * count - 0.001))/2, accuracy);
            }
            else {
                result += parse(function.f(previous_result + h * count), accuracy);
            }
            count++;
            n--;
        }
        result *= h;
        System.out.println(parse(result, accuracy));
    }

    private double parse(double number, int accuracy) {
        String result = String.format("%." + accuracy + "f",number);
        return Double.parseDouble(result.replace(",", "."));
    }

}