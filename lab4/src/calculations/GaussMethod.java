package calculations;


public class GaussMethod {

    private static double[][] array;
    private static double[][] arr;
    private static int n;
    private static int swap = 0;

    private static int[] order;

   public static double[] solve(double[][] arr) {
       int currentRow = 0;
       array = arr;
       int maxElementIndex;
       n = array[0].length - 1;
       order = new int[n];
        for(int i = 0; i < n ; i++) {
            order[i] = i;
        }
        for (int i = 0; i < n - 1; i++) {
            maxElementIndex = getMaxElementIndex(currentRow);
            swapColumns(currentRow, maxElementIndex);
            changeLines(currentRow);
            currentRow++;
        }

        double [] x = getResult();

        double[] results = new double[n];
        for(int i = 0; i < n; i++) {
            results[i] = x[order[i]];
        }

        return results;

}

    private static int getMaxElementIndex(int row) {
        double max = Math.abs(array[row][row]);
        int maxIndex = row;

        for (int i = row + 1; i < n; i++) {  //���� ������� ������� 1 �� �������
            if (Math.abs(array[row][i]) > max) {
                max = Math.abs(array[row][i]);
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private static void swapColumns(int firstColumn, int secondColumn) {
        double[] tmp = new double[n + 1];
        if(firstColumn != secondColumn) {
            swap++;
        }
        for (int i = 0; i < n ; i++) {
            tmp[i] = array[i][firstColumn];
            array[i][firstColumn] = array[i][secondColumn];
        }

        for (int i = 0; i < n; i++) {
            array[i][secondColumn] = tmp[i];
        }

        int temp = order[firstColumn];
        order[firstColumn] = order[secondColumn];
        order[secondColumn] = temp;
    }

    private static void changeLines(int row) {
        double maxElement = array[row][row];
        double[] multipliers = new double[n - row - 1];
        for (int i = row + 1; i < n; i++) {
            multipliers[i - row - 1] = array[i][row] / maxElement;
        }
        for (int i = row + 1; i < n; i++) {
            for (int j = 0; j < n + 1; j++) {
                array[i][j] -= multipliers[i - row - 1] * array[row][j];
            }
        }
    }


    private static double [] getResult() {

        double[] [] tmp = new double[n][n+1];

        for(int i = 0; i < n ; i++) {
            if (n + 1 >= 0) System.arraycopy(array[i], 0, tmp[i], 0, n + 1);
        }
        double[] results = new double[n];

        results[n-1] = tmp[n-1][n] / tmp[n-1][n-1];
        for(int i = n-2; i >= 0; i--) {         // ������ �� �������
            for(int j = i+1; j < n ; j++) {       // ������ �� ��������
                tmp[i][n] -= tmp[i][j] * results[j];
            }
            results[i] = tmp[i][n] / tmp[i][i];
        }
        return results;
    }





}
