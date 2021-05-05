package functions;

import calculations.GaussMethod;

public class LinearFunction implements Approximation {

    private double a;
    private double b;
    private double s;
    private double deviation;
    private double r;

    @Override
    public String getTypeOfFunction() {
        return "phi = ax + b";
    }

    @Override
    public String getTitle() {
        return "линейная функция";
    }

    @Override
    public double calculateY(double x) {
        return a * x + b;
    }

    public double getCorrelationCoefficient() {
        return r;
    }

    @Override
    public void calculate(double[] x, double[] y) {
        double[][] array = new double[2][3];
        double sx = 0;
        double sxx = 0;
        double sxy = 0;
        double sy = 0;
        double n = x.length;
        for(int i = 0; i < x.length; i++) {
            sx += x[i];
            sxx += Math.pow(x[i], 2);
            sy += y[i];
            sxy += x[i] * y[i];
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
            e = y[i] - (a * x[i] + b);
            s += Math.pow(e, 2);
        }

        deviation = Math.sqrt(s / n);

        double meanX = sx / n;
        double meanY = sy / n;
        double numerator = 0;
        double denominatorX = 0;
        double denominatorY = 0;
        double denominator;

        for(int i = 0; i < x.length; i++) {
            numerator += (x[i] - meanX) * (y[i] - meanY);
            denominatorX += Math.pow((x[i] - meanX), 2);
            denominatorY += Math.pow((y[i] - meanY), 2);
        }
        denominator = Math.sqrt(denominatorX * denominatorY);

        r = numerator / denominator;

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
