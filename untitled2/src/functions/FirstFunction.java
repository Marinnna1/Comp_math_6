package functions;

public class FirstFunction implements Function {

    @Override
    public double f(double x) {
        return 3 * Math.pow(x, 3) - 2 * Math.pow(x, 2) - 7 * x - 8;
    }

    @Override
    public double secondDerivative(double x) {
        return 18 * x - 4;
    }

    @Override
    public double fourthDerivative(double x) {
        return 0;
    }

}
