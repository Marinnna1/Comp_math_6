package methods;

public interface Method {

    void doMethod(double a, double b, int n, int accuracy);

    double getAccuracyOfNumericalIntegration(double a, double b, int n);
}
