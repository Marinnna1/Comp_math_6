package main;

import functions.*;
import methods.*;
import java.io.*;

public class Reader {

    private static String integralNumber;
    private static Function f;
    private static int n = 4;
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static String integral;
    private static double a;
    private static double b;

    public static void main(String[] args) throws IOException {
        getIntegral();
        getBounds();
        int e = getE();
        if (integralNumber.equals("2")) {
            a += Math.pow(10, -e);
            b -= Math.pow(10, -e);
        }
        else if(integralNumber.equals("3")){
            if(Math.abs(a) == Math.abs(b)) {
                a = 0.1;
            }
            else {
                System.out.println("�������� ������ ���� ������������");
                System.exit(0);
            }
        }
        String methodNumber = getMethodNumber();
        Method method;
        if(methodNumber.equals("1")) {
            method = new RectangleMethod(f);
        }
        else if(methodNumber.equals("2")) {
            method = new TrapezoidMethod(f);
        }
        else {
            method = new SimpsonMethod(f);
        }

        getN(methodNumber);
        System.out.println("��������� ��������: integral from " + a + " to " + b + " " + integral);
        System.out.println("����� ��������� ��������� ��������������: " + n);
        method.doMethod(a, b, n, e);
        System.out.println("����������� ���������� ��������������: " + method.getAccuracyOfNumericalIntegration(a, b, n));
    }

    private static void getBounds() throws IOException {
        String answer;
        do {
            System.out.println("������� 0, ���� ������ �������� ������� �� ���������: a = " + a + " b = " + b + ", ��� 1 - ������ ����");
            answer = reader.readLine();
        } while(!(answer.equals("0") || answer.equals("1")));
        if(answer.equals("1")) {
            getA();
            getB();
        }

    }

    private static void getIntegral() throws IOException {
        do {
            System.out.println("������� ����� ���������, ������� ������ ������");
            System.out.println("1) integral from 2 to 3 (3x^3 - 2x^2 - 7x - 8)dx");
            System.out.println("2) integral from 1 to 5 (sin(x)/x)dx");
            System.out.println("3) integral from 3 to 4 (1/x)dx");
            integralNumber = reader.readLine();
        }while(!(integralNumber.equals("1") || integralNumber.equals("2") || integralNumber.equals("3")));

        if(integralNumber.equals("1")) {
            integral = "(3x^3 - 2x^2 - 7x - 8)dx";
            a = 2;
            b = 3;
            f = new FirstFunction();
        }
        else if(integralNumber.equals("2")) {
            integral = "(sin(x)/x)dx";
            a = 1;
            b = 5;
            f = new SecondFunction();
        }
        else {
            integral = "(ln(x) + 5x - 6)dx";
            a = 3;
            b = 4;
            f = new ThirdFunction();
        }
    }

    private static void getA() {
        while(true) {
            System.out.println("������� ������ ������ ��������������");
            try {
                a = Double.parseDouble(reader.readLine());
                break;
            }
            catch (Exception ignored) {
            }
        }
    }

    private static void getB() {
        while(true) {
            System.out.println("������� ������� ������ ��������������");
            try {
                b = Double.parseDouble(reader.readLine());
                break;
            }
            catch (Exception ignored) {
            }
        }
    }

    private static int getE() {
        double e;
        while(true) {
            System.out.println("������� �������� ���������� (�������� 0.01)");
            try {
                e = Double.parseDouble(reader.readLine());
                if(e < 1 && e > 0) {
                    break;
                }
            }
            catch (Exception ignored) {
            }
        }
        int accuracy = (int) (1 / e);
        int result = 0;
        while(accuracy >= 10) {
            accuracy /= 10;
            result++;
        }
        return result;
    }

    private static void getN(String methodNumber) throws IOException {
        String answer;

        do {
            System.out.println("��������� ��������� n = 4, ������� 0, ���� �������� ���, 1- ���� ������ ���� �������");
            answer = reader.readLine();
        } while(!(answer.equals("0") || answer.equals("1")));

        if(answer.equals("1")) {
            while(true) {
                System.out.println("������� n");
                try {
                    n = Integer.parseInt(reader.readLine());
                    if (n > 0 && n < 1000000) {
                        if (methodNumber.equals("3")) {
                            if (n % 2 == 0) {
                                break;
                            }
                            else {
                                System.out.println("��� ������ �������� n ������ ���� ������");
                            }
                        }
                        else {
                            break;
                        }
                    }
                    else {
                        System.out.println("n �����������");
                    }
                }
                catch (Exception ignored) {
                }
            }
        }
    }

    private static String getMethodNumber() throws IOException {
        String methodNumber;
        do {
            System.out.println("������� ����� ������, ������� ������ ��� ������");
            System.out.println("1) ����� ��������������� (�����, ������, �������)");
            System.out.println("2) ����� ��������");
            System.out.println("3) ����� ��������");
            methodNumber = reader.readLine();
        }while(!(methodNumber.equals("1") || methodNumber.equals("2") || methodNumber.equals("3")));

        return methodNumber;
    }
}