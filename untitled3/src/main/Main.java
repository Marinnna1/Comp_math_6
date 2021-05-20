package main;

import graphics.*;
import java.text.DecimalFormat;
import java.util.Arrays;

public class Main {

    static double[] selection = {37, 49, 43, 31, 44, 38, 40, 31, 28, 43,
                                 32, 44, 47, 29, 51, 25, 43, 38, 41, 32,
                                 38, 24, 49, 40, 32, 34, 31, 28, 37, 46,
                                 41, 35, 43, 25, 37, 46, 38, 24, 41, 50,
                                 38, 29, 41, 32, 34, 49, 44, 37, 31, 47,
                                 50, 34, 25, 37, 40, 32, 35, 28, 44, 43,
                                 46, 37, 41, 35, 29, 43, 38, 31, 26, 34,
                                 49, 32, 46, 26, 38, 35, 40, 51, 37, 46,
                                 37, 25, 40, 34, 24, 44, 32, 28, 34, 38,
                                 44, 34, 29, 47, 37, 49, 43, 35, 47, 50 };

    public static double[] x;
    public static double[] y;
    public static double[] n;
    public static int count = 1;

    public static void main(String[] args) {

        Arrays.sort(selection);
        System.out.println("¬ариационный р€д:");
        for (int i = 0; i < selection.length; i++) {
            System.out.print(selection[i] + " ");
            if((i + 1) % 10 == 0) {
                System.out.println();
            }
        }
        System.out.println();
        System.out.println("Ёкстремальные значени€ выборки: " + selection[0] + ", " + selection[selection.length - 1]);
        double scope = selection[selection.length - 1] - selection[0];
        System.out.println("–азмах: " + scope);

        double sum = 0;

        for(int i = 0; i < selection.length; i++) {
            sum += selection[i];
            if(i != 0) {
                if(selection[i] != selection[i-1]) {
                    count++;
                }
            }
        }

        double sampleAverage = sum / selection.length;
        System.out.println("ќценка мат. ожидани€ (выборочное среднее): " + parse(sampleAverage));


        sum = 0;
        for(int i = 0; i < selection.length; i++) {
            sum += Math.pow((selection[i] - sampleAverage), 2);
        }

        double sampleVariance = sum / selection.length;
        System.out.println("ќценка дисперсии (выборачна€ дисперси€): " + parse(sampleVariance));

        double standardDeviation = Math.sqrt(sampleVariance);
        System.out.println("—реднеквадратическое отклонение: " + parse(standardDeviation));


        System.out.println("Ёмпирическа€ функци€ распределени€ : ");
        int currentN = 1;
        sum = 0;
        System.out.print("        ");
        System.out.println(0 + " при x <= " + selection[0]);

        int currentCount = 1;
        count += 2;
        x = new double[count];
        y = new double[count];
        x[0] = selection[0];
        y[0] = 0;

        for(int i = 1; i < selection.length; i++) {
            if(selection[i] != selection[i-1]) {
                sum += (double) currentN / selection.length;
                x[currentCount] = selection[i - 1];
                y[currentCount] = sum;
                currentCount++;
                if(currentCount == count/2) {
                    System.out.print("F(x) =  ");
                }
                else {
                    System.out.print("        ");
                }
                System.out.println(parse(sum) + " при " + selection[i-1] + " < x <= " + selection[i]);
                currentN = 1;
            }
            else {
                currentN++;
            }
            if(i == selection.length -1) {
                sum += (double) currentN / selection.length;
                x[currentCount] = selection[selection.length -1];
                y[currentCount] = sum;
                x[currentCount+1] = selection[selection.length -1] + 1;
                y[currentCount + 1] = sum;
                System.out.print("        ");
                System.out.println(parse(sum) + " при x > " + selection[i]);
            }
        }
     //   showGraphic(); Graphic

        n = new double[count];
        int index = 0;

        Arrays.fill(x, 0);
        Arrays.fill(y, 0);

        for(int i = 1; i < selection.length; i++) {
            if (selection[i] != selection[i - 1]) {
                n[index] = currentN;
                x[index] = selection[i - 1];
                index++;
                currentN = 1;
            } else {
                currentN++;
            }
            if(i == selection.length - 1) {
                n[index] = currentN;
                x[index] = selection[i];
                index++;
            }
        }


    //    showGraphic(); //Polygon

        double h = scope / (1 + (Math.log(selection.length) / Math.log(2)));

        for(int i = 0; i < n.length; i++) {
            y[i] = n[i] / (selection.length * h);
        }

        showGraphic();// Histogram

    }

    private static String parse(double number) {
        String pattern = "##0.0000";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        if(decimalFormat.format(number).equals("-0,0000")){
            return decimalFormat.format(number).replace("-", "");
        }
        return decimalFormat.format(number);
    }

    private static void showGraphic() {
        Histogram histogram = new Histogram();
        histogram.launch(Histogram.class, null);
    }

}
