import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'migratoryBirds' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static int migratoryBirds(List<Integer> arr) {
        // create map
        Map<Integer, Integer> intMap = new HashMap<>();

        // Go through list
        for (int num : arr) {
            // If the key doesn't exist, add 1
            if (!intMap.containsKey(num)) {
                intMap.put(num, 1);
            } else {
                intMap.computeIfPresent(num, (k,v) -> v + 1);
            }
        }

        // Keep maxVal as tally
        int maxVal = 0;
        int maxValKey = 0;

        Iterator<Map.Entry<Integer, Integer>> iterator = intMap.entrySet().iterator();

        // Iterate thru every entry in the map
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            // If the values are equal we need to check the key
            if (entry.getValue() == maxVal) {
                // Replace key if its lower than the existing key
                if (entry.getKey() < maxValKey) {
                    maxValKey = entry.getKey();
                }
            } else if (entry.getValue() > maxVal) {
                // If the value is greater than the existing maxVal
                // Set the new maxVal
                maxVal = entry.getValue();
                // Set the maxValKey
                maxValKey = entry.getKey();
            }
        }

        return maxValKey;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = Result.migratoryBirds(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}