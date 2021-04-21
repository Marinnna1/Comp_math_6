package com.company;

public class Determinant {

    static double[][] getMatrixWithoutRowAndCol(double[][] matrix, int size, int row, int col) {
        int offsetRow = 0; //Смещение индекса строки в матрице
        int offsetCol = 0; //Смещение индекса столбца в матрице
        double[][] newMatrix = new double[size][size];
        for(int i = 0; i < size-1; i++) {
            //Пропустить row-ую строку
            if(i == row) {
                offsetRow = 1; //Как только встретили строку, которую надо пропустить, делаем смещение для исходной матрицы
            }

            offsetCol = 0; //Обнулить смещение столбца
            for(int j = 0; j < size-1; j++) {
                //Пропустить col-ый столбец
                if(j == col) {
                    offsetCol = 1; //Встретили нужный столбец, проускаем его смещением
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
        int degree = 1; // (-1)^(1+j) из формулы определителя


        //Условие выхода из рекурсии
        if(size == 1) {
            return matrix[0][0];
        }
        //Условие выхода из рекурсии
        else if(size == 2) {
            return matrix[0][0]*matrix[1][1] - matrix[0][1]*matrix[1][0];
        }
        else {
            //Матрица без строки и столбца
            double [][] newMatrix = new double[size-1][size-1];
            for(int i = 0; i < size-1; i++) {
                newMatrix[i] = new double[size-1];
            }

            //Раскладываем по 0-ой строке, цикл бежит по столбцам
            for(int j = 0; j < size; j++) {
                //Удалить из матрицы i-ю строку и j-ый столбец
                //Результат в newMatrix
                newMatrix = getMatrixWithoutRowAndCol(matrix, size, 0, j);

                //Рекурсивный вызов
                //По формуле: сумма по j, (-1)^(1+j) * matrix[0][j] * minor_j (это и есть сумма из формулы)
                det = det + (degree * matrix[0][j] * matrixDet(newMatrix, size-1));
                //"Накручиваем" степень множителя
                degree = -degree;
            }
        }
        return det;
    }

}
