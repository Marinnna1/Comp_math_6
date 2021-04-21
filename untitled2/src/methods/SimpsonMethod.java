package methods;

import functions.*;

public class SimpsonMethod implements Method {

    private Function function;

    public SimpsonMethod(Function function) {
        this.function = function;
    }

    @Override
    public void doMethod(double a, double b, int n, int accuracy) {
        System.out.print("Значение интеграла по методу Симпсона: ");
        double h = (b - a) / n;
        double result = parse((function.f(a) + function.f(b)), accuracy);
        double evenIndexesSum = 0;
        double oddIndexesSum = 0;
        double current;
        for(int i = 1; i <= n - 1; i++) {
            if(a + h * i == 0 && (function instanceof  SecondFunction || function instanceof  ThirdFunction)) {
                current = parse((function.f(a + h * i + 0.001) + function.f(a + h * i - 0.001))/2, accuracy);
            }
            else {
                current = parse(function.f(a + h * i), accuracy);
            }
            if(i % 2 == 0) {
                evenIndexesSum += 2 * current;
            }
            else {
                oddIndexesSum += 4 * current;
            }
        }
        result += evenIndexesSum + oddIndexesSum;
        result *= h / 3;
        System.out.println(parse(result, accuracy));
    }

    @Override
    public double getAccuracyOfNumericalIntegration(double a, double b, int n) {
        return Math.abs(Math.max(function.fourthDerivative(a),function.fourthDerivative(b))) * Math.pow((b - a), 5) / (180 * Math.pow(n, 4));
    }


    private double parse(double number, int accuracy) {
        String result = String.format("%." + accuracy + "f",number);
        return Double.parseDouble(result.replace(",", "."));
    }
}