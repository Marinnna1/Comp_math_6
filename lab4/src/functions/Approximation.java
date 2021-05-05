package functions;


public interface Approximation {

    String getTypeOfFunction();

    String getTitle();

    double calculateY(double x);

    void calculate(double[] x, double[] y);

    double getA();

    double getB();

    double getC();

    double getS();

    double getStandardDeviation();

}
