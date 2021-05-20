package functions;

public class FirstFunction implements Function {

    @Override
    public double f(double x) {
        return Math.pow(x, -3);
    }

    @Override
    public String getTitle() {
        return "x^-3";
    }

}
