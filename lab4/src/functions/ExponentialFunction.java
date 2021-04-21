package functions;

import calculations.GaussMethod;

public class ExponentialFunction implements Approximation {

    private String a;
    private String b;
    private String s;
    private String deviation;

    @Override
    public String getTypeOfFunction() {
        return "phi = a*e^(bx)";
    }

    @Override
    public String getTitle() {
        return "экспоненциальная функция";
    }

    @Override
    public double calculateY(double x) {
        double coefficientA = Double.parseDouble(a.replace(",", "."));
        double coefficientB = Double.parseDouble(b.replace(",", "."));
        return coefficientA * Math.pow(Math.E, (coefficientB * x));
    }

    @Override
    public void calculate(double[] x, double[] y) {
        double[][] array = new double[2][3];
        double sx = 0;
        double sxx = 0;
        double sxy = 0;
        double sy = 0;
        double n = x.length;
        double currentY;
        for(int i = 0; i < x.length; i++) {
            currentY = Math.log(y[i]);
            sx += x[i];
            sxx += Math.pow(x[i], 2);
            sy += currentY;
            sxy += x[i] * currentY;
        }
        array[0][0] = sxx;
        array[0][1] = sx;
        array[0][2] = sxy;
        array[1][0] = sx;
        array[1][1] = n;
        array[1][2] = sy;

        double[] bounds = GaussMethod.solve(array);
        bounds[1] = Math.pow(Math.E, bounds[1]);
        a = parse(bounds[1]);
        b = parse(bounds[0]);

        double e;
        double sum = 0;
        for(int i = 0; i < x.length; i++) {
            e = y[i] - (bounds[1] * Math.pow(Math.E, (bounds[0] * x[i])));
            sum += Math.pow(e, 2);
        }

        s = parse(sum);

        deviation = parse(Math.sqrt(sum / n));
    }


    @Override
    public String getA() {
        return a;
    }

    @Override
    public String getB() {
        return b;
    }

    @Override
    public String getC() {
        return "-";
    }

    @Override
    public String getS() {
        return s;
    }

    @Override
    public String getStandardDeviation() {
        return deviation;
    }
}
