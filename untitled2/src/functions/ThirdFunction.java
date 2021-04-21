package functions;

public class ThirdFunction implements Function {

    @Override
    public double f(double x) {
        return 1/x;
    }

    @Override
    public double secondDerivative(double x) {
        return 2 / Math.pow(x, 3);
    }

    @Override
    public double fourthDerivative(double x) {
        return 24 / Math.pow(x, 5);
    }
}
