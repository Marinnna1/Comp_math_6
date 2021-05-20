package methods;

public class LagrangePolynomial implements Method {

    private double[] l;

    @Override
    public double solve(double[] x, double[] y, double x0) {
        l = new double[x.length];
        double result = 0;
        double numerator = 1;
        double denominator = 1;
        for(int i = 0; i < x.length; i++) {
            for(int j = 0; j < x.length; j++) {
                if(i != j) {
                    numerator *= (x0 - x[j]);
                    denominator *= (x[i] - x[j]);
                }
            }
            l[i] = numerator / denominator * y[i];
            result += l[i];
            numerator = 1;
            denominator = 1;
        }
        return result;
    }

}
