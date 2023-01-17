import java.io.*;

import static java.util.stream.Collectors.joining;

class Result {

    /*
     * Complete the 'dayOfProgrammer' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts INTEGER year as parameter.
     */

    public static String dayOfProgrammer(int year) {
        String date = "";

        // Day of the programmer : 256

        // Check if Gregorian or Julian
        // 1700 to 1917 : Julian
        // 1918 : transition from Julian to Gregorian
        // 1919 : Gregorian

        date = "13.09."; // Default

        if (year < 1917) {
            System.out.println("Julian Calender");
            // Check if year is a leap year
            // If divisible by 4, it's a leap year
            if ((year % 4) == 0) {
                date = "12.09.";
            }
        } else  if (year == 1918) {
            System.out.println("Transition Year");
            date = "26.09.";
        } else if (year > 1918) {
            System.out.println("Gregorian Calender");
            // Check if year is a leap year
            // If divisible by 400 OR divisible by 4 and not divisible by 100
            if ((year % 400) == 0 || ((year % 4 == 0) && (year % 100 != 0))) {
                // Total number of days 256 - 244 = 12
                date = "12.09.";
            }
        }

        date += year;

        return date;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int year = Integer.parseInt(bufferedReader.readLine().trim());

        String result = Result.dayOfProgrammer(year);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
