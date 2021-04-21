package main;

import functions.*;
import graphics.Graphics;

public class Writer {

    public static Approximation function;
    public static String bestFunction;

    static void printResults(Approximation[] functions) {
        makeTable();

        for(Approximation function : functions) {
            System.out.format("|%17s| %7s | %7s | %7s |   %15s   |    %25s    |%n" , function.getTypeOfFunction(), function.getA(), function.getB(), function.getC(), function.getS(), function.getStandardDeviation());
        }

        LinearFunction linearFunction = (LinearFunction) functions[0];
        System.out.format("---------------------------------------------------------------------------------------------------------%n");
        System.out.println("Коэффициент корреляции Пирсона (для лин. зависимости): " + linearFunction.getCorrelationCoefficient());

        double minDeviation = Double.parseDouble(functions[0].getStandardDeviation().replace(",", "."));
        int index = 0;
        for(int i = 1; i < functions.length; i++) {
            if(Double.parseDouble(functions[i].getStandardDeviation().replace(",", ".")) < minDeviation) {
                minDeviation = Double.parseDouble(functions[i].getStandardDeviation().replace(",", "."));
                index = i;
            }
        }
        bestFunction = functions[index].getTypeOfFunction().replace("a", functions[index].getA());
        bestFunction = bestFunction.replace("b", functions[index].getB());
        bestFunction = bestFunction.replace("c", functions[index].getC());
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

}
