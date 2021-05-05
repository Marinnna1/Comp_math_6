package functions;

import calculations.GaussMethod;


public class LogarithmicFunction implements Approximation {

    private double a;
    private double b;
    private double s;
    private double deviation;

    @Override
    public String getTypeOfFunction() {
        return "phi = a*ln(x) + b";
    }

    @Override
    public String getTitle() {
        return "логарифмическая функция";
    }

    @Override
    public double calculateY(double x) {
        return a * Math.log(x) + b;
    }

    @Override
    public void calculate(double[] x, double[] y) {
        double[][] array = new double[2][3];
        double sx = 0;
        double sxx = 0;
        double sxy = 0;
        double sy = 0;
        double n = x.length;
        double currentX;
        for(int i = 0; i < x.length; i++) {
            currentX = Math.log(x[i]);
            sx += currentX;
            sxx += Math.pow(currentX, 2);
            sy += y[i];
            sxy += currentX * y[i];
        }
        array[0][0] = sxx;
        array[0][1] = sx;
        array[0][2] = sxy;
        array[1][0] = sx;
        array[1][1] = n;
        array[1][2] = sy;

        double[] bounds = GaussMethod.solve(array);
        a = bounds[0];
        b = bounds[1];

        double e;
        s = 0;
        for(int i = 0; i < x.length; i++) {
            e = y[i] - (a * Math.log(x[i]) + b);
            s += Math.pow(e, 2);
        }


        deviation = Math.sqrt(s / n);
    }

    @Override
    public double getA() {
        return a;
    }

    @Override
    public double getB() {
        return b;
    }

    @Override
    public double getC() {
        return 0;
    }

    @Override
    public double getS() {
        return s;
    }

    @Override
    public double getStandardDeviation() {
        return deviation;
    }
}
