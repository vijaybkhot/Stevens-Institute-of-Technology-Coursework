/*
 * Assignment 8
 * Name : Vijay Khot
 * CWID : 20021838
 * */
import java.util.*;

public class Assignment8 {

    // Question 1:
    /**
     * Finds the maximum object in the specified range of an array.
     * @param dataArray: The array of objects from which the maximum object is to be found
     * @param fromIndex The starting index of the array range to find maximum object. It is inclusive.
     * @param toIndex The ending index of the array range to find maximum object. It is exclusive.
     * @param <T>  The type of object to be compared. T is for generic type.
     * @return: The maximum object in the given range.
     */
    public static <T extends Comparable<T>> T findMaxInRange(T[] dataArray, int fromIndex, int toIndex) {
        // Solution
        // Check if indices are valid
        if (fromIndex > toIndex || fromIndex < 0 || toIndex > dataArray.length) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        // Initialize the maximum value with the first element in the range
        T max = dataArray[fromIndex];

        // Iterate through the array range to find the maximum object
        for( int i = fromIndex + 1; i < toIndex; i++) {
            // Compare the current element with the current maximum
            if (dataArray[i].compareTo(max) > 0) {
                // Update the maximum if the current element is greater
                max = dataArray[i];
            }
        }
        // Return the maximum object in the given range
        return max;
    }

    // Question 3:
    /**
     * @param queue : The queue to be interleaved.
     * @param <T> : Generic type.
     */
    public static <T> void interleaveQueue(Queue<T> queue) {
        int length = queue.size();      // Variable of length of queue
        int half = length / 2;          // Half of the length

        Queue<T> lastHalf = new LinkedList<>();     //Initialize a temporary queue to hold elements from the last half

        // Rotate the first half of the queue
        if (length % 2 == 0) {
            for (int i = 0; i < half; i++) {
                T hold = queue.poll();
                queue.offer(hold);
            }
        }
        else {
            for (int i = 0; i < half ; i++) {
                T hold = queue.poll();
                queue.offer(hold);
            }
        }
        T mid = queue.peek();       // Hold the middle element in the variable "mid"
        // Populate the lastHalf queue with last half elements of the queue
        if (length % 2 == 0) {      // If the length of queue is even
            for (int i = 0; i < half; i++) {
                T hold = queue.poll();
                lastHalf.offer(hold);
            }
        }
        else {                      // If the length of queue is odd
            queue.poll();           // Push the first element, which is the middle element
            for (int i = 0; i < half; i++) {     // Iterate the rest of the queue to store last half
                T hold = queue.poll();
                lastHalf.offer(hold);
            }
        }
        // Interleave the elements back into the initial queue
        if (length % 2 == 0) {/* If the length of queue is even, interleave elements from
        lastHalf to original queue alternately*/
            for (int i = 0; i < half; i++) {
                T holdFromQueue = queue.poll();
                queue.offer(holdFromQueue);

                T holdFromLastHalf = lastHalf.poll();
                queue.offer(holdFromLastHalf);
            }
        }
        else {                          // If the length of the queue is odd
            int count = 0;              // Interleave for the special case to keep the middle element position unchanged
            while (count < length / 2) {
                if (count % 2 == 0) {
                    queue.offer(queue.poll());
                } else {
                    queue.offer(lastHalf.poll());
                }
                count++;
            }
            queue.offer(mid);
            count++;
            while (count < length) {
                if (count % 2 == 0) {
                    queue.offer(lastHalf.poll());
                } else {
                    queue.offer(queue.poll());
                }
                count++;
            }
        }
    }


    // Question 4:
    /**
     * Finds the maximum element in a specified range of an array using a PriorityQueue.
     * @param dataArray:The array of objects from which the maximum object is to be found.
     * @param fromIndex The starting index of the array range to find the maximum object. It is inclusive.
     * @param toIndex The ending index of the array range to find the maximum object. It is exclusive.
     * @param <T> The type of object to be compared. T is for generic type.
     * @return: The maximum object in the given range.
     * @throws: IllegalArgumentException if the indices are invalid.
     */
    public static <T extends Comparable<T>> T findMaxInRangeUsingPriorityQueue(T[] dataArray, int fromIndex, int toIndex) {
        // Solution
        // Check if indices are valid
        if (fromIndex > toIndex || fromIndex < 0 || toIndex > dataArray.length) {
            throw new IllegalArgumentException("Index out of bounds");
        }

        // Create a PriorityQueue with reverse order comparator to get maximum elements first
        PriorityQueue<T> dataQueue = new PriorityQueue<>(Collections.reverseOrder());

        // Add elements from the specified range to the PriorityQueue
        for (int i = fromIndex; i < toIndex; i++) {
            dataQueue.offer(dataArray[i]);
        }

        // Retrieve and return the maximum element from the PriorityQueue
        return dataQueue.poll();
    }

    // Main method for testing
    public static void main(String[] args) {
        // Call methods and test here with own test cases

        Integer[] numbers = {1, 3, 5, 6, 2, 9};
        Integer max = findMaxInRange(numbers, 1, 5);
        System.out.println(max); // Output: 6

        String[] words = {"apple", "orange", "banana", "pear"};
        String maxWord = findMaxInRange(words, 0, 3);
        System.out.println(maxWord); // Output: "orange"

        Queue<Integer> queue = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5));
        interleaveQueue(queue);
        System.out.println(queue); // Output: [1, 4, 3, 2, 5]

        Integer[] numbers1 = {1, 3, 5, 6, 2, 9};
        Integer max1 = findMaxInRange(numbers1, 1, 5);
        System.out.println(max1); // Should print "6"

    }
}

