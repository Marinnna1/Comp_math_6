package fuctions;

public class SecondFunction implements Function{

    private double c;

    @Override
    public String getEquation() {
        return "y' = y + (1 + x) * y^2";
    }

    @Override
    public double getSolveFunction(double x) {
        return -( Math.pow(Math.E, x) / (c + Math.pow(Math.E, x) * x));
    }

    @Override
    public void countConstant(double x, double y) {
        c = -Math.pow(Math.E, x) / y - Math.pow(Math.E, x);
    }

    @Override
    public double getF(double x, double y) {
        return y + (1 + x) * Math.pow(y, 2);
    }

    @Override
    public String getSolveFunctionName() {
        return "-e^x / " + "(" + c + " + e^x * x" + ")";
    }
}
