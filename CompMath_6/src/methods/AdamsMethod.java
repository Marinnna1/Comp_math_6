package methods;

import fuctions.*;

public class AdamsMethod implements Method {

    public static double[] x;
    public static double[] y;

    public void solve(Function function, double a, double b, double y0, double h) {
        int count = (int) ((b - a) / h) + 1;
        x = new double[count];
        y = new double[count];
        x[0] = a;
        y[0] = y0;
        double fi;
        double delta1_fi;
        double delta2_fi;
        double delta3_fi;

        RungeKuttaMethod rungeKuttaMethod = new RungeKuttaMethod();
        rungeKuttaMethod.solve(function, a, b, y0, h);
        x[1] = rungeKuttaMethod.x[1];
        x[2] = rungeKuttaMethod.x[2];
        x[3] = rungeKuttaMethod.x[3];
        y[1] = rungeKuttaMethod.y[1];
        y[2] = rungeKuttaMethod.y[2];
        y[3] = rungeKuttaMethod.y[3];

        for (int i = 4; i < y.length; i++) {
            x[i] = x[0] + i * h;
            fi = function.getF(x[i - 1], y[i - 1]);
            delta1_fi = fi - function.getF(x[i - 2], y[i - 2]);
            delta2_fi = fi - 2 * function.getF(x[i - 2], y[i - 2]) + function.getF(x[i - 3], y[i - 3]);
            delta3_fi = fi - 3 * function.getF(x[i - 2], y[i - 2]) + 3 * function.getF(x[i - 3], y[i - 3]) - function.getF(x[i - 4], y[i - 4]);
            y[i] = y[i - 1] + h * fi + 0.5 * Math.pow(h, 2) * delta1_fi + (double) (5 / 12) * Math.pow(h, 3) * delta2_fi + (double) (3 / 8) * Math.pow(h, 4) * delta3_fi;
        }

    }
}
