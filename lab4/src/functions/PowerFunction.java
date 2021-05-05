package functions;

import calculations.GaussMethod;

public class PowerFunction implements Approximation {

    private double a;
    private double b;
    private double s;
    private double deviation;

    @Override
    public String getTypeOfFunction() {
        return "phi = a*x^b";
    }

    @Override
    public String getTitle() {
        return "степенная функция";
    }

    @Override
    public double calculateY(double x) {
        return a * Math.pow(x, b);
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
        double currentY;
        for(int i = 0; i < x.length; i++) {
            currentX = Math.log(x[i]);
            currentY = Math.log(y[i]);
            sx += currentX;
            sxx += Math.pow(currentX, 2);
            sy += currentY;
            sxy += currentX * currentY;
        }
        array[0][0] = sxx;
        array[0][1] = sx;
        array[0][2] = sxy;
        array[1][0] = sx;
        array[1][1] = n;
        array[1][2] = sy;

        double[] bounds = GaussMethod.solve(array);
        bounds[1] = Math.pow(Math.E, bounds[1]);
        a = bounds[1];
        b = bounds[0];

        double e;
        s = 0;
        for(int i = 0; i < x.length; i++) {
            e = y[i] - (a * Math.pow(x[i], b));
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
