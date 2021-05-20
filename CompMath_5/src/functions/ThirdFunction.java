package functions;

public class ThirdFunction implements Function {

    @Override
    public double f(double x) {
        return 3 * Math.pow(x, 3) + 4;
    }

    @Override
    public String getTitle() {
        return "3 * x^3 + 4";
    }

}
