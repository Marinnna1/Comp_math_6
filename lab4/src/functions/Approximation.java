package functions;

import java.text.DecimalFormat;

public interface Approximation {

    String getTypeOfFunction();

    String getTitle();

    double calculateY(double x);

    void calculate(double[] x, double[] y);

    String getA();

    String getB();

    String getC();

    String getS();

    String getStandardDeviation();

    default String parse(double number) {
        String pattern = "##0.000";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        if(decimalFormat.format(number).equals("-0,000")){
            return decimalFormat.format(number).replace("-", "");
        }
        return decimalFormat.format(number);
    }

}
