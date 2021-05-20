package methods;

import java.util.Arrays;

public class NewtonPolynomial implements Method {

    @Override
    public double solve(double[] x, double[] y, double x0) {
        if(!checkPoints(x)) {
            System.out.println("х должны быть неравноотстоящими");
            System.exit(0);
        }
        double result = y[0];
        double decimal;
        double multiplier = 1;
        for(int i = 1; i < x.length; i++) {
            multiplier *= (x0 - x[i-1]);
            decimal = getDecimal(x, y, 0, i, i+1);
            result += decimal * multiplier;
        }
        return result;
    }

    private boolean checkPoints(double[] x) {
        Arrays.sort(x);
        double tmp = x[1] - x[0];
        for(int i = 2; i < x.length; i++) {
            if(tmp != (x[i] - x[i-1])) {
                return true;
            }
        }
        return false;
    }

    private double getDecimal(double[] x, double[] y, int firstIndex, int lastIndex, int count) {
        if(count == 2) {
            return (y[lastIndex] - y[firstIndex]) / (x[lastIndex] - x[0]);
        }
        double result;
        if((lastIndex + 1) % 2 == 0) {
            result = (getDecimal(x, y, (int) Math.floor(lastIndex/2) + 1, lastIndex, (int) Math.floor(lastIndex/2) + 1) - getDecimal(x, y, 0, (int) Math.floor(lastIndex/2), (int) Math.floor(lastIndex/2) + 1)) / (x[lastIndex] - x[0]);
        }
        else {
            result = (getDecimal(x, y, (int) Math.ceil(lastIndex/2) + 1, lastIndex, (int) Math.ceil(lastIndex/2) + 1) - getDecimal(x, y, 0, (int) Math.ceil(lastIndex/2), (int) Math.ceil(lastIndex/2) + 1)) / (x[lastIndex] - x[0]);
        }

        return result;
    }

}
