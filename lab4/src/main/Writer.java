package main;

import functions.*;
import graphics.Graphics;

import java.text.DecimalFormat;

public class Writer {

    public static Approximation function;
    public static String bestFunction;

    static void printResults(Approximation[] functions) {
        makeTable();
        String c;
        for(int i = 0; i < functions.length; i++) {
            if(functions[i] != null) {
                if (i == 4) {
                    c = parse(functions[i].getC());
                } else {
                    c = "-";
                }
                System.out.format("|%17s| %7s | %7s | %7s |   %15s   |    %25s    |%n", functions[i].getTypeOfFunction(), parse(functions[i].getA()), parse(functions[i].getB()), c, parse(functions[i].getS()), parse(functions[i].getStandardDeviation()));
            }
        }

        LinearFunction linearFunction = (LinearFunction) functions[0];
        System.out.format("---------------------------------------------------------------------------------------------------------%n");
        System.out.println("Коэффициент корреляции Пирсона (для лин. зависимости): " + parse(linearFunction.getCorrelationCoefficient()));

        double minDeviation = functions[0].getStandardDeviation();
        int index = 0;
        for(int i = 1; i < functions.length; i++) {
            if (functions[i] != null) {
                if (functions[i].getStandardDeviation() < minDeviation) {
                    minDeviation = functions[i].getStandardDeviation();
                    index = i;
                }

            }
        }
        bestFunction = functions[index].getTypeOfFunction().replace("a", parse(functions[index].getA()));
        bestFunction = bestFunction.replace("b", parse(functions[index].getB()));
        bestFunction = bestFunction.replace("c", parse(functions[index].getC()));
        function = functions[index];
        System.out.println("Наилучшая аппроксимирующая функция: f(x) = " + bestFunction + " - " + functions[index].getTitle());
        showGraphic();

    }


    private static void makeTable() {
        System.out.format("---------------------------------------------------------------------------------------------------------%n");
        System.out.format("|   Вид функции   |    a    |    b    |    c    |  Мера отклонения S  |  Среднеквадратичное отклонение  |%n");
        System.out.format("---------------------------------------------------------------------------------------------------------%n");
    }

    private static void showGraphic() {
        Graphics graphic = new Graphics();
        graphic.launch(Graphics.class, null);
    }

    private static String parse(double number) {
        String pattern = "##0.000";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        if(decimalFormat.format(number).equals("-0,000")){
            return decimalFormat.format(number).replace("-", "");
        }
        return decimalFormat.format(number);
    }

}
