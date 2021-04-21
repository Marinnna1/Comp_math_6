package methods;

import functions.Function;
import functions.SecondFunction;
import functions.ThirdFunction;

public class TrapezoidMethod implements Method {

    private Function function;

    public TrapezoidMethod(Function function) {
        this.function = function;
    }

    @Override
    public void doMethod(double a, double b, int n, int accuracy) {
        System.out.print("Значение интеграла по методу трапеций: ");
        double h = (b - a) / n;
        double result = (function.f(a) + function.f(b)) / 2;
        double count = 1;
        boolean isChanged = false;
        while(n > 1) {
            if(a + h * count == 0 && (function instanceof SecondFunction || function instanceof ThirdFunction)) {
                result += parse((function.f(a + count * h + 0.001) + function.f(a + count * h - 0.001))/2, accuracy);
            }
            else {
                result += parse(function.f(a + count * h), accuracy);
            }
            count++;
            n--;
        }
        result *= h;
        System.out.println(parse(result, accuracy));
    }

    @Override
    public double getAccuracyOfNumericalIntegration(double a, double b, int n) {
        return Math.abs(Math.max(function.secondDerivative(a),function.secondDerivative(b))) * Math.pow((b - a), 3) / (12 * Math.pow(n, 2));
    }

    private double parse(double number, int accuracy) {
        String result = String.format("%." + accuracy + "f",number);
        return Double.parseDouble(result.replace(",", "."));
    }
}
