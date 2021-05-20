package fuctions;

public class FirstFunction implements Function {

    private double c;

    @Override
    public String getEquation() {
        return "y' + 2y = x^2";
    }

    @Override
    public double getSolveFunction(double x) {
        return c / Math.pow(Math.E, 2*x) + 0.5 * Math.pow(x, 2) - 0.5 * x + 0.25;
    }


    @Override
    public void countConstant(double x, double y) {
      c = (y - 0.5 * Math.pow(x, 2) + 0.5 * x  - 0.25) * Math.pow(Math.E, 2*x);
    }

    @Override
    public double getF(double x, double y) {
        return Math.pow(x, 2) - 2 * y;
    }

    @Override
    public String getSolveFunctionName() {
        return c + " / e^(2x) + x^2 / 2 - x/2 + 0.25";
    }

}
