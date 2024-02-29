
/*
 * Assignment 1
 * Name : Vijay Khot
 * CWID : 20021838
 * */

public class Assignment1 {

    static double calculateHypotenuse(double side1, double side2) {
        // By pythagoras principle, hypotenuse is the square root of the sum the square of the remaining two sides
        // c^2 = a^2 = b^2; c = sqrt(a^2 = b^2)
        double hypotenuse = Math.sqrt((Math.pow(side1, 2)) + (Math.pow(side2, 2))); // Calculate using different methods in Math class
        hypotenuse = (Math.round(hypotenuse * 100.0))/ 100.0;
        return hypotenuse; //Return hypotenuse after calculation.
    }

    static char convertCharacterCase(char inputChar) {
        char o = inputChar; // Initialize a character 'o' with inputChar
        //Use different methods from Character class

        // If 'o' is lowercase, convert to uppercase:
        if (Character.isLowerCase(inputChar)) {
            o = Character.toUpperCase(inputChar);

            // If 'o' is uppercase convert to lowercase
        } else if (Character.isUpperCase(inputChar)) {
            o = Character.toLowerCase(inputChar);
        }

        return o; // Return 'o' either converted or as it is
    }

    static String reverseString(String input) {
        int length = input.length(); // declare an integer of length of the input string
        char[] temp = new char[length]; // Initialize a temporary array of size length
        //Copy the characters in the input string to the temp array in reverse order
        for (int i = 0; i < length ; i++) {
            temp[i] = input.charAt(length - 1 - i);
        }
        String reversed = new String(temp); // Create a new string from the temp array
        return reversed; // return the new string
    }

    static int sumFirstTenFibonacci() {
        // TODO
        int[] tenFibonacci = new int[10]; //Initialize an array to store the first ten Fibonacci numbers
        // Initialize the first two elements to 0 and 1
        tenFibonacci[0] = 0;
        tenFibonacci[1] = 1;
        int sum = 1; // Initialize a variable called sum to 1, by adding the first two Fibonacci numbers
        // Iterate over the subsequent array elements and calculate and store the respective Fibonacci values
        for (int i = 2; i < 10; i++) {
            tenFibonacci[i] = tenFibonacci[i-1] + tenFibonacci[i-2];
            sum = sum + tenFibonacci[i]; // add each Fibonacci value to sum and update
        }
        return sum; // Return sum
    }

    public static void main(String[] args) {

        // Call methods and test here with own test cases

        //Calling method `calculateHypotenuse(double side1, double side2)`
        double side1 = 49.22;
        double side2 = 12.78;
        System.out.println("The hypotenuse of a a triangle having sides "+ side1 + " and " + side2 + " is " + calculateHypotenuse(side1, side2));
        System.out.println();


        // Calling method `convertCharacterCase(char inputChar)`
        char inputChar = 't';
        System.out.println("The character '" + inputChar + "' in converted case is '" + convertCharacterCase(inputChar) + "'.");
        System.out.println();



        // Calling method `reverseString(String input)`
        String original = "OpenAI";
        System.out.println("The String \"" + original + "\" in reversed order is " + reverseString(original));
        System.out.println();


        // Calling method `sumFirstTenFibonacci()`
        System.out.println("The sum of first 10 numbers in Fibonacci sequence is " + sumFirstTenFibonacci());
    }
}
