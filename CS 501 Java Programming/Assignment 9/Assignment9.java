import java.util.*;
/*
 * Assignment 9
 * Name : Vijay Khot
 * CWID : 20021838
 * */
public class Assignment9 {

    // Question 1:
    /**
     * Find the maximum length of a subarray with exactly ` k` distinct integers,
     * @param elements the list of integers to find subarray from
     * @param k the maximum number of distinct elements in the subarray
     * @return the maximum length of the subarray with distinct elements
     */
    public static int maxSubarrayLengthWithDistinctElements(List<Integer> elements, int k) {
        // Check for edge cases: empty list, null list, or invalid k
        if (elements == null || elements.isEmpty() || k <= 0) {
            return 0;
        }

        // HashSet to keep track of distinct elements in the current subarray
        Set<Integer> set = new HashSet<>();
        // Perform operations on each element


        // Variable to store the maximum size of subarray
        int maxSize = 0;

        // Iterate through each element in the input list
        for (int i = 0; i < elements.size(); i++) {
            int count = 0;      // Count of distinct elements in the current subarray
            int index = i;

            // Continue adding elements to the subarray until k distinct elements are reached
            while (index < elements.size() && count < k) {
                if (!set.contains(elements.get(index))) {
                    count++;
                    set.add(elements.get(index));
                }

                index++;
            }

            // Update maxSize with the length of the current subarray
            maxSize = Math.max(maxSize, index - i);

            // Clear the set and subArray for the next iteration
            set.clear();
        }
        return maxSize;
    }

    // Question 2:
    /**
     * Sort characters in a string by frequency in descending order using a Map,
     * with same-frequency characters sorted by ascending ASCII values
     * @param s The String to be sorted.
     * @return : The sorted string.
     */
    public static String frequencySort(String s) {
        // Create a HashMap to store the frequency of each character
        HashMap<Character, Integer> charFrequency = new HashMap<>();

        // Count the frequency of each character in the input string
        for (int i = 0; i < s.length(); i++) {
            if (!charFrequency.containsKey(s.charAt(i))) {
                charFrequency.put(s.charAt(i), 1);
            }
            else {
                charFrequency.put(s.charAt(i), charFrequency.get(s.charAt(i))+1);
            }
        }

        // Create a TreeMap to sort characters based on ASCII values
        TreeMap<Character, Integer> sortedCharFrequency = new TreeMap<>((a, b) -> {
            // If frequencies are equal, sort by ASCII values
            if (charFrequency.get(a).equals(charFrequency.get(b))) {
                return a - b;
            }
            // Sort by frequency in descending order
            return charFrequency.get(b) - charFrequency.get(a);
        });
        sortedCharFrequency.putAll(charFrequency);

        // Populate the sortedCharFrequency TreeMap with the contents of charFrequency
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : sortedCharFrequency.entrySet()) {
            // Append the character to the result based on its frequency
            for (int i = 0; i < entry.getValue(); i++) {
                result.append(entry.getKey());
            }
        }
        // Return the final sorted string
        return result.toString();
    }

    // Question 3:
    /**
     * Traverses a 2D matrix in a spiral order and returns the elements in an ArrayList.
     * @param matrix The 2D matrix to be traversed.
     * @return An ArrayList containing the elements of the matrix in a spiral order.
     */
    public static ArrayList<Integer> spiralOrder(int[][] matrix) {
        int column = matrix[0].length;
        int row = matrix.length;

        int left = 0;
        int right = column - 1;
        int top = 0;
        int bottom = row - 1;

        // A new array list to store the matrix values as we traverse
        ArrayList<Integer> spiralList = new ArrayList<>();

        // Traverse through the matrix in spiral order
        while (top <= bottom && left <= right) {
            // Traverse from left to right in the top row
            for (int i = left; i <= right; i++) {
                spiralList.add(matrix[top][i]);
            }
            top++;

            // Traverse from top to bottom in the rightmost column
            for (int i = top; i <= bottom; i++) {
                spiralList.add(matrix[i][right]);
            }
            right--;

            // Traverse from left to  right in the bottommost row
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    spiralList.add(matrix[bottom][i]);
                }
                bottom--;
            }

            // Traverse from bottom to  top in the left-most column
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    spiralList.add(matrix[i][left]);
                }
                left++;
            }
        }
        // return the list
        return spiralList;
    }

    // Question 4:
    /**
     * Returns a list of prime factors of the given number in ascending order.
     * @param number The number to find prime factors for.
     * @return List of prime factors.
     */
    public static List<Long> efficientPrimeFactors(long number) {
        // List to store prime factors
        List<Long> primeFactors = new ArrayList<>();

        // Handle the case of 1
        if (number == 1) {
            return primeFactors;
        }

        // Divide by 2 repeatedly to get rid of even factors
        while (number % 2 == 0) {
            // Add 2 as a prime factor
            primeFactors.add(2L);
            // Update the number by dividing it by 2
            number /= 2;
        }

        // Trial division for odd factors starting from 3
        for (long factor = 3; factor * factor <= number; factor += 2) {
            // Continue dividing by odd factors until it's no longer divisible
            while (number % factor == 0) {
                // Add the current factor as a prime factor
                primeFactors.add(factor);
                // Update the number by dividing it by the current factor
                number /= factor;
            }
        }

        // If the remaining number is greater than 1, it is a prime factor
        if (number > 1) {
            primeFactors.add(number);
        }

        return primeFactors;
    }

    public static void main(String[] args) {
        // Test the implementations
        System.out.println(maxSubarrayLengthWithDistinctElements(Arrays.asList(1, 2, 1, 3, 4, 2, 3), 3));

        System.out.println(maxSubarrayLengthWithDistinctElements(Arrays.asList(5, 1, 3, 5, 2, 3, 4), 2));

        System.out.println(frequencySort("programming"));

        System.out.println(frequencySort("occurrence"));

        List<Long> factors = efficientPrimeFactors(600851475143L);
        System.out.println(factors); // Output: [2, 2, 3, 5]

        List<Long> factors1 = efficientPrimeFactors((long) Math.pow(5, 20));
        System.out.println(factors1); // Output: [2, 2, 5, 5]

        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(spiralOrder(matrix)); // Output: [1, 2, 3, 6, 9, 8, 7, 4, 5]

        int[][] matrix1 = {{1, 2}, {3, 4}, {5, 6}};
        System.out.println(spiralOrder(matrix1)); // Output: [1, 2, 4, 6, 5, 3]
    }
}

