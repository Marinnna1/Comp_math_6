package methods;

import fuctions.*;

public class RungeKuttaMethod implements Method {

    public static double[] x;
    public static double[] y;

    public void solve(Function function, double a, double b, double y0, double h) {
        int count = (int) ((b - a) / h) + 1;
        x = new double[count];
        y = new double[count];
        x[0] = a;
        y[0] = y0;
        double k1;
        double k2;
        double k3;
        double k4;

        for (int i = 1; i < y.length; i++) {
            x[i] = a + i * h;
            k1 = function.getF(x[i - 1], y[i - 1]);
            k2 = function.getF(x[i - 1] + h / 2, y[i - 1] + h * k1 / 2);
            k3 = function.getF(x[i - 1] + h / 2, y[i - 1] + h * k2 / 2);
            k4 = function.getF(x[i - 1] + h, y[i - 1] + h * k3);
            y[i] = y[i - 1] + h / 6 * (k1 + 2 * k2 + 2 * k3 + k4);
        }

    }


}
