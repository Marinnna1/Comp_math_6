package com.company;

public class Determinant {

    static double[][] getMatrixWithoutRowAndCol(double[][] matrix, int size, int row, int col) {
        int offsetRow = 0; //�������� ������� ������ � �������
        int offsetCol = 0; //�������� ������� ������� � �������
        double[][] newMatrix = new double[size][size];
        for(int i = 0; i < size-1; i++) {
            //���������� row-�� ������
            if(i == row) {
                offsetRow = 1; //��� ������ ��������� ������, ������� ���� ����������, ������ �������� ��� �������� �������
            }

            offsetCol = 0; //�������� �������� �������
            for(int j = 0; j < size-1; j++) {
                //���������� col-�� �������
                if(j == col) {
                    offsetCol = 1; //��������� ������ �������, ��������� ��� ���������
                }

                newMatrix[i][j] = matrix[i + offsetRow][j + offsetCol];
            }
        }
        return newMatrix;
    }

    public static double getDeterm(double[][] matrix, int n) {
        double res = 1;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j< n; j++) {
                if(i == j) {
                    res *= matrix[i][j];
                }
            }
        }
        return res;
    }

    static double matrixDet(double[][] matrix, int size) {
        double det = 0d;
        int degree = 1; // (-1)^(1+j) �� ������� ������������


        //������� ������ �� ��������
        if(size == 1) {
            return matrix[0][0];
        }
        //������� ������ �� ��������
        else if(size == 2) {
            return matrix[0][0]*matrix[1][1] - matrix[0][1]*matrix[1][0];
        }
        else {
            //������� ��� ������ � �������
            double [][] newMatrix = new double[size-1][size-1];
            for(int i = 0; i < size-1; i++) {
                newMatrix[i] = new double[size-1];
            }

            //������������ �� 0-�� ������, ���� ����� �� ��������
            for(int j = 0; j < size; j++) {
                //������� �� ������� i-� ������ � j-�� �������
                //��������� � newMatrix
                newMatrix = getMatrixWithoutRowAndCol(matrix, size, 0, j);

                //����������� �����
                //�� �������: ����� �� j, (-1)^(1+j) * matrix[0][j] * minor_j (��� � ���� ����� �� �������)
                det = det + (degree * matrix[0][j] * matrixDet(newMatrix, size-1));
                //"�����������" ������� ���������
                degree = -degree;
            }
        }
        return det;
    }

}
