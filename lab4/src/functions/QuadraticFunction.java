package functions;

import calculations.GaussMethod;

public class QuadraticFunction implements Approximation {

    private String a;
    private String b;
    private String c;
    private String s;
    private String deviation;

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
        double coefficientA = Double.parseDouble(a.replace(",", "."));
        double coefficientB = Double.parseDouble(b.replace(",", "."));
        double coefficientC = Double.parseDouble(c.replace(",", "."));
        return coefficientA * Math.pow(x, 2) + coefficientB * x + coefficientC;
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
        c = parse(bounds[0]);
        b = parse(bounds[1]);
        a = parse(bounds[2]);

        double e;
        double sum = 0;
        for(int i = 0; i < x.length; i++) {
            e = y[i] - (bounds[0] + bounds[1] * x[i] + bounds[2] * Math.pow(x[i], 2));
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
        return c;
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
