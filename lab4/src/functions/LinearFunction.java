package functions;

import calculations.GaussMethod;

public class LinearFunction implements Approximation {

    private String a;
    private String b;
    private String s;
    private String deviation;
    private String r;

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
        double coefficientA = Double.parseDouble(a.replace(",", "."));
        double coefficientB = Double.parseDouble(b.replace(",", "."));
        return coefficientA * x + coefficientB;
    }

    public String getCorrelationCoefficient() {
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
        a = parse(bounds[0]);
        b = parse(bounds[1]);

        double e;
        double sum = 0;
        for(int i = 0; i < x.length; i++) {
            e = y[i] - (bounds[0] * x[i] + bounds[1]);
            sum += Math.pow(e, 2);
        }
        s = parse(sum);

        deviation = parse(Math.sqrt(sum / n));

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

        r = parse(numerator / denominator);

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
