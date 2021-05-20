package functions;

public class SecondFunction implements Function {

    @Override
    public double f(double x) {
        return Math.pow(x, (double) 1/3);
    }

    @Override
    public String getTitle() {
        return "x^(1/3)";
    }

}
