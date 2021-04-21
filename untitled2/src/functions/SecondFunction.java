package functions;

public class SecondFunction implements Function {

    @Override
    public double f(double x) {
        return Math.sin(x) / x;
    }

    @Override
    public double secondDerivative(double x) {
        return (-Math.sin(x) - 2*Math.cos(x) / x + 2 * Math.sin(x) / (Math.pow(x, 2))) / x;
    }

    @Override
    public double fourthDerivative(double x) {
        return (Math.sin(x) + 4 * Math.cos(x) / x - 12*Math.sin(x)/Math.pow(x, 2) - 24 * Math.cos(x) / Math.pow(x, 3) + 24 * Math.sin(x)/Math.pow(x, 4)) / x;
    }
}
