package main;

import functions.*;

import java.io.*;

public class Reader {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static double[] x;
    public static double[] y;

    public static void main(String[] args) throws IOException {

        String source = getDataFrom();
        if(source.equals("0")) {
            getDataFromFile();
        }
        getData(source);

        LinearFunction linearFunction = new LinearFunction();
        PowerFunction powerFunction = new PowerFunction();
        ExponentialFunction exponentialFunction = new ExponentialFunction();
        LogarithmicFunction logarithmicFunction = new LogarithmicFunction();
        QuadraticFunction quadraticFunction = new QuadraticFunction();

        linearFunction.calculate(x, y);
        powerFunction.calculate(x, y);
        exponentialFunction.calculate(x, y);
        logarithmicFunction.calculate(x, y);
        quadraticFunction.calculate(x, y);

        Approximation[] functions = new Approximation[5];
        functions[0] = linearFunction;
        functions[1] = powerFunction;
        functions[2] = exponentialFunction;
        functions[3] = logarithmicFunction;
        functions[4] = quadraticFunction;

        Writer.printResults(functions);
    }


    private static void getDataFromFile() throws IOException {
        while (true) {
            System.out.println("Введите путь к файлу");
            String fileName = reader.readLine();
            try {
                File file = new File(fileName);
                FileReader fileReader = new FileReader(file);
                reader = new BufferedReader(fileReader);
                break;
            } catch (Exception e) {
                System.out.println("Файл не найден");
            }
        }
    }

    private static void getData(String source) throws IOException {
        int count = -1;
        do {
            System.out.println("Введите количество строк данных x и y (минимум 12)");
            try {
                count = Integer.parseInt(reader.readLine());
                if(source.equals("0")) {
                    System.out.println(count);
                }
            }
            catch (NullPointerException e){
                System.err.println("Не удалось обработать запрос");
                System.exit(1);
            }
            catch (Exception ignored) {
            }
        } while (count < 7);

        String answer;
        String[] data;
        x = new double[count];
        y = new double[count];
        for(int i = 0; i < count; i++) {
            System.out.println("Введите x и y (через пробел)");
            try {
                answer = reader.readLine();
                if (source.equals("0")) {
                    System.out.println(answer);
                }
                data = answer.split(" ");
                try {
                    x[i] = Double.parseDouble(data[0]);
                    y[i] = Double.parseDouble(data[1]);
                } catch (Exception e) {
                    System.out.println("Данная строка некорректно введена");
                    i -= 1;
                }
            } catch(NullPointerException e) {
                System.err.println("Не удалось обработать запрос");
                System.exit(1);
            }
        }
    }

    private static String getDataFrom() throws IOException {
        String answer;
        do {
            System.out.println("Введите 1, если вводите данные с консоли, 0 - из файла");
            answer = reader.readLine();
        } while(!(answer.equals("1") || answer.equals("0")));

        return answer;
    }

}
