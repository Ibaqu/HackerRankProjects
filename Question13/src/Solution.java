import java.io.*;

import static java.util.stream.Collectors.joining;

class Result {

    /*
     * Complete the 'pageCount' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER p
     */

    public static int pageCount(int pages, int page) {
        int count = 0;

        // Reduce page number by 1 if odd
        if (page % 2 != 0) {
            page = page - 1;
        }

        // 0:1 2:3 4:5 6:
        // 0   1   2   3

        int leftCount = (page / 2);
        int rightCount = (pages - page) / 2;


        count =  (leftCount < rightCount) ? leftCount : rightCount;


        return count;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        int p = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.pageCount(n, p);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}