package functions;

import calculations.GaussMethod;

public class QuadraticFunction implements Approximation {

    private double a;
    private double b;
    private double c;
    private double s;
    private double deviation;

    @Override
    public String getTypeOfFunction() {
        return "ax^2 + bx + c";
    }

    @Override
    public String getTitle() {
        return "полиномиальная функция 2-й степени";
    }

    @Override
    public double calculateY(double x) {
        return a * Math.pow(x, 2) + b * x + c;
    }

    @Override
    public void calculate(double[] x, double[] y) {
        int n = x.length;
        double sum_x = 0;
        double sum_x2 = 0;
        double sum_x3 = 0;
        double sum_x4 = 0;
        double sum_y = 0;
        double sum_xy = 0;
        double sum_x2y = 0;

        for(int i = 0; i < x.length; i++) {
            sum_x += x[i];
            sum_x2 += Math.pow(x[i], 2);
            sum_x3 += Math.pow(x[i], 3);
            sum_x4 += Math.pow(x[i], 4);
            sum_y += y[i];
            sum_xy += x[i] * y[i];
            sum_x2y += Math.pow(x[i], 2) * y[i];
        }

        double[][] array = new double[3][4];

        array[0][0] = n;
        array[0][1] = sum_x;
        array[0][2] = sum_x2;
        array[0][3] = sum_y;

        array[1][0] = sum_x;
        array[1][1] = sum_x2;
        array[1][2] = sum_x3;
        array[1][3] = sum_xy;

        array[2][0] = sum_x2;
        array[2][1] = sum_x3;
        array[2][2] = sum_x4;
        array[2][3] = sum_x2y;

        double[] bounds = GaussMethod.solve(array);
        c = bounds[0];
        b = bounds[1];
        a = bounds[2];

        double e;
        s = 0;
        for(int i = 0; i < x.length; i++) {
            e = y[i] - (c + b * x[i] + a * Math.pow(x[i], 2));
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
        return c;
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
