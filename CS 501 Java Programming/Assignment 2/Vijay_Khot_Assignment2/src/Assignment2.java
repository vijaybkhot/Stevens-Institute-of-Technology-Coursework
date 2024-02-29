
/*
 * Assignment 2
 * Name : Vijay Khot
 * CWID : 20021838
 * */

public class Assignment2 {

    // Methods


    //Finding the minimum and maximum values in a given array
    public static int[] findMinMax(int[] arr) {
        // Throw an illegal argument exception error if the input array is empty
        if (arr.length == 0) {
            throw new IllegalArgumentException("Array is empty.");
        }
        // Create two integer variables 'min' and 'max' to store respective values and assign them with the first element of the array
        int min = arr[0];
        int max = arr[0];
        // Iterate through all the elements of the array from the second element
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) { //If arr[i] is greater than max, reassign max with arr[i]
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i]; //If arr[i] is less than min, reassign min with arr[i]
            }
        }
        int[] finalMinMax = {min, max};// Create an array which holds 'min' and 'max' to return
        System.out.println("[" +  finalMinMax[0] + ", " + finalMinMax[1] + "]" ); //Print the minimum value
        return finalMinMax; // Return the final array of min and max
    }


    //Reversing the elements within the same array (in-place) without creating any new arrays
    public static int[] reverseInPlace(int[] arr) {
        // We need to iterate only through half of the elements of the array to interchange the position of each element
        for (int i = 0; i < (arr.length/2); i++) {
            int temp = arr[i]; //Make a copy of the 'i' element in the array
            arr[i] = arr[arr.length-i-1]; // assign the 'i' th element with 'length-i-1'th element
            arr[arr.length-i-1] = temp; // assign the 'length-i-1'th element with the copy value of the 'i'th element
        }
        System.out.print("[");
        for (int i = 0; i < arr.length-1; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println(arr[arr.length-1] + "]");
        return arr; //Return the reversed array
    }


    // Calculate and return the sum of the diagonal elements of the matrix
    public static int sumOfDiagonal(int[][] matrix) {
        int sum = 0; //Initialize the 'sum' integer with zero
        // Iterate through each element of the input matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                // As the input is a 3x3 matrix, diagonal elements have 'i' and 'j' equal. eg: 11, 22, 33
                if (i == j) {
                    sum += matrix[i][j]; // add the value of the diagonal element to the sum variable
                }
            }
        }
        System.out.println(sum);
        return sum; // return the sum value
    }


    // Compute the transpose of the matrix and return it
    public static int[][] transposeMatrix(int[][] matrix) {
        // Transpose of a matrix means that an element with position [i][j] in one matrix changes to [j][i]
        // Initialize a transpose matrix. By interchanging the numbers of rows with columns and vice versa
        int[][] transpose = new int[matrix[0].length][matrix.length];
        //Iterate through each element of the matrix array
        for (int i= 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                transpose[j][i] = matrix[i][j]; // Reverse the position of 'i' and 'j' in transpose
            }
        }
        // Print the transpose matrix
        System.out.println("[");
        System.out.println();
        for (int i = 0; i < transpose.length; i++) {
            System.out.print("\t[");
            for (int j = 0; j < transpose[0].length-1; j++) {
                System.out.print(" " + transpose[i][j] + ", ");
            }
            System.out.print(transpose[i][transpose[i].length-1]);
            System.out.println("],");
        }
        System.out.println();
        System.out.println("]");
        return transpose; // Return the transpose matrix
    }



    // Main function
    public static void main(String[] args) {

        // Tests for findMinMax
        findMinMax(new int[] {9, 45, 21, 56, 78, 34, 25, 67, 99, 12});
        findMinMax(new int[] {10, 5, 14, 3, 7});
        System.out.println();


        // Tests for reverseInPlace
        reverseInPlace(new int[] {1, 2, 3, 4, 5});
        reverseInPlace(new int[] {6, 7, 8});
        System.out.println();


        // Tests for sumOfDiagonal
        sumOfDiagonal(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        sumOfDiagonal(new int[][] {{10, 11, 12}, {13, 14, 15}, {16, 17, 18}});
        System.out.println();


        // Tests for transposeMatrix
        transposeMatrix(new int[][] {{1, 2, 3}, {4, 5, 6}});
        transposeMatrix(new int[][] {{7, 8, 9}, {10, 11, 12}});


    }
}
