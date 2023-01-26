import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'formingMagicSquare' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY s as parameter.
     */

    public static int formingMagicSquare(List<List<Integer>> s) {
        int cost = 0;

        // Convert List<List<Integer>> into array matrix
        int[][] matrix = getIntegerMatrix(s);

        // Find the largest number sum in the matrix
        int maxSum = getMaxSum(matrix);

        // Iterate through each value but check the row, column and diagonal
        // Compare each value and get the lowest difference
        // Make the change

        int[][] newMatrix = changeMatrix(matrix, maxSum);

        return maxSum;
    }

    private static int[][] changeMatrix(int[][] matrix, int sum) {
        int[][] newMatrix = new int[][];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {

                // [0][0]
                // [0][0] - [0][1] - [0][2]
                // [1][0] - [1][1] -
                // [2][0] - [ ][ ] - [2][2]

                int rowSum = getRowSum(matrix, i);
                int columnSum = getColumnSum(matrix, j);
                int leftDiagSum = getLeftDiagSum(matrix);
                int rightDiagSum = getRightDiagSum(matrix);

                List<Integer> sumList = new ArrayList<>();
                sumList.add(rowSum);
                sumList.add(columnSum);
                sumList.add(leftDiagSum);
                sumList.add(rightDiagSum);


            }
        }

    }

    private static int getRowSum(int[][] row, int rowIndex) {
        int sum = 0;

        // Row means the [][j] value iterates
        for (int j = 0; j < row.length; j++) {
            sum += row[rowIndex][j];
        }

        return sum;
    }

    private static int getColumnSum(int[][] column, int columnIndex) {
        int sum = 0;

        // Column means [i][] value iterates
        for (int i = 0; i < column.length; i++) {
            sum += column[i][columnIndex];
        }

        return sum;
    }

    private static int getLeftDiagSum(int[][] matrix) {
        int sum = 0;

        for (int i = 0; i < matrix.length; i++) {
            sum += matrix[i][i];
        }

        return sum;
    }

    private static int getRightDiagSum(int[][] matrix) {
        int sum = 0;

        for (int i = 0; i < matrix.length; i++) {
            sum += matrix[i][(matrix.length - 1) - i];
        }

        return sum;
    }



    private static int[][] getIntegerMatrix(List<List<Integer>> listMatrix) {

        int[][] matrix = new int[listMatrix.size()][listMatrix.size()];

        // Iterate through rows of the list matrix
        for (int i = 0;  i < listMatrix.size(); i++) {

            List<Integer> row = listMatrix.get(i);

            // Iterate through row
            for (int j = 0; j < row.size(); j++) {
                matrix[i][j] = row.get(j);
            }
        }

        return matrix;
    }

    private static int getMaxSum(int[][] matrix) {
        int maxSum = 0;

        // Iterate through rows
        for (int i = 0; i < matrix.length; i++) {
            int sumRow = 0;
            int sumColumn = 0;
            // Iterate through values of the row and sum it up
            for (int j = 0; j < matrix.length; j++) {
                sumRow += matrix[i][j];
                sumColumn += matrix[j][i];
            }

            if (sumRow > maxSum) {
                maxSum = sumRow;
            } else if (sumColumn > maxSum) {
                maxSum = sumRow;
            }
        }

        // Check diagonals
        // [0][0] [1][1] [2][2]

        int diagonal1 = 0;
        int diagonal2 = 0;

        for (int i = 0; i < matrix.length; i++) {
            diagonal1 += matrix[i][i];
            diagonal2 += matrix[i][(matrix.length-1) - i];
        }

        if (diagonal1 > maxSum) {
            maxSum = diagonal1;
        } else if (diagonal2 > maxSum) {
            maxSum = diagonal2;
        }

        return maxSum;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        List<List<Integer>> s = new ArrayList<>();

        IntStream.range(0, 3).forEach(i -> {
            try {
                s.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.formingMagicSquare(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
