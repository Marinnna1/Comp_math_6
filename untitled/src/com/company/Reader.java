package com.company;

import java.io.*;

public class Reader {
    static BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
    static double[][] matrix;

    public static double[][] readMatrix() throws IOException {
        System.out.println("Напишите 0, если вводите данные из файла, или 1 если с консоли");
        String answer = consoleReader.readLine();
        while (!(answer.trim().equals("0") || answer.trim().equals("1"))) {
            System.out.println("Напишите 0, если вводите данные из файла или 1 если с консоли");
            answer = consoleReader.readLine();
        }
        if (answer.trim().equals("0")) {
            matrix = readFromFile();
        } else {
            matrix = readFromConsole();
        }
        return matrix;
    }

    public static double[][] readFromConsole() throws IOException {
        System.out.println("Введите строки матрица(коэфффициенты разделяйте пробелом)");
        String line = consoleReader.readLine();
        int rang = readMatrixLine(line).length - 1;
        matrix = new double[rang][rang + 1];
        double[] reserve_matrix;
        try {
            for(int i = 0; i < rang; i++) {
                reserve_matrix = readMatrixLine(line);
                for (int j = 0; j < rang + 1; j++) {
                    matrix[i][j] = reserve_matrix[j];
                }
                if(i != rang-1) {
                    line = consoleReader.readLine();
                }
            }
            return matrix;
        }
        catch (Exception e) {
            System.out.println("Неверный ввод данных");
            System.exit(-1);

        }
        return null;
    }

        public static double[][] readFromFile() throws IOException {
            String fileName = null;
            try {
                System.out.println("Введите название файла");
                fileName = consoleReader.readLine();
                File file = new File(fileName);
                FileReader fileReader = new FileReader(file);
                BufferedReader reader = new BufferedReader(fileReader);
                String line = reader.readLine();
                int rang = readMatrixLine(line).length - 1;
                matrix = new double[rang][rang + 1];
                double[] reserve_matrix;
                try {
                    for (int i = 0; i < rang; i++) {
                        reserve_matrix = readMatrixLine(line);
                        for (int j = 0; j < rang + 1; j++) {
                            matrix[i][j] = reserve_matrix[j];
                        }
                        if(i != rang-1) {
                            line = reader.readLine();
                        }
                    }
                } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                    System.err.println("Ошибка с вводом данных");
                    System.exit(-1);
                }
                return matrix;
            } catch (FileNotFoundException e) {
                System.out.println("Файл " + fileName + " не найден");
                System.exit(-1);
            } catch (IOException ignore) {

            } catch (Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }
            return null;
        }

        static double[] readMatrixLine (String line){
            try {
                String[] str = line.split(" ");
                double[] dbl = new double[str.length];
                for (int i = 0; i < str.length; i++) {
                    dbl[i] = Double.parseDouble(str[i]);
                }
                return dbl;
            } catch (Exception e) {
                System.err.println("Ошибка с вводом данных");
                System.exit(-1);
            }
            return null;
        }

    }

