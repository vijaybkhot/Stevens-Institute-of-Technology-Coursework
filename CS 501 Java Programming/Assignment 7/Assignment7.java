import java.io.*;

/*
 * Assignment 7
 * Name : Vijay Khot
 * CWID : 20021838
 * */
public class Assignment7 {

    // Question 1:
    /**
     *Copies a binary file from the specified source path to the specified destination path
     * @param sourcePath: The source path of the file to be copied
     * @param destinationPath: The destination where file is to be copied
     */
    public static void copyBinaryFile(String sourcePath, String destinationPath) throws IOException {
        // Solution code
        //Create File objects from the arguments "sourcePath" and "destinationPath"
        File destination = new File(destinationPath);
        File source = new File(sourcePath);

        // Create FileOutputStream and FileInputStream objects from above created. Use a try catch black
        try (FileOutputStream output = new FileOutputStream(destination);
             FileInputStream input = new FileInputStream(source)){
            byte[] buffer = new byte[1024];  // Create a buffer to store the data which is to be read
            int readData;   // Initiate an int to assign the read binary number from input
            while ((readData = input.read(buffer)) != -1) {     //Iterate through the input file using while loop
                output.write(buffer, 0, readData);          // Write the data to 'output'
            }
            input.close();      // close the input stream
            output.flush();     // flush the output stream
            output.close();     // close the output stream

        } catch (IOException e) {       // Catch input output exception
            throw new RuntimeException(e);
        }
    }

    // Question 2:
    /**
     * A method to convert a text file to a binary file
     * @param textFilePath: The source text file
     * @param binaryFilePath: The destination binary file
     */
    public static void textToBinaryFile(String textFilePath, String binaryFilePath)  {
        // Solution code
        //Create File objects from the arguments "sourcePath" and "destinationPath"
        File destination = new File(binaryFilePath);
        File source = new File(textFilePath);
        // Create FileInputStream object to read text from the texFilePath
        // Create DataOutputStream object to store the binary data read from text file
        try(
                BufferedReader input = new BufferedReader(new FileReader(source));
                FileOutputStream output = new FileOutputStream(destination);
                DataOutputStream binaryOutput = new DataOutputStream(output);
                ){
            int data;
            while ((data = input.read()) != -1) {
                binaryOutput.writeChar(data);
            }
            input.close();      // close the input stream
            output.flush();     // flush the output stream
            output.close();     // close the output stream
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * A method to convert a Binary file to a text file
     * @param binaryFilePath: The source Binary file
     * @param textFilePath: The destination text file
     */
    public static void binaryToTextFile(String binaryFilePath, String textFilePath)  {
        // Solution code
        //Create File objects from the arguments "sourcePath" and "destinationPath"
        File source = new File(binaryFilePath);
        File destination = new File(textFilePath);
        // Create FileInputStream object to read text from the texFilePath
        // Create DataOutputStream object to store the binary data read from text file
        try(
                FileInputStream input = new FileInputStream(source);
                DataInputStream binaryInput = new DataInputStream(input);
                FileWriter output = new FileWriter(destination);

        ){
            while((binaryInput.available()) > 0) {        // Iterate over the binary file to read data
                char character = binaryInput.readChar();
                output.write(character);       // Write the binary data to text output file
            }
            input.close();      // close the input stream
            output.flush();     // flush the output stream
            output.close();     // close the output stream
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // Question 3:
    /**
     *Calculates the power of a given number using recursion
     * @param base: Base of the given number
     * @param exponent: exponent of the give number
     * @return: The value of the exponential equation
     */
    public static double power(double base, int exponent) {
        // Solution code]
        if (exponent < 0) {     // Return 0 if exponent is negative
            return 0;
        } else if (exponent == 0) {     // Base case
            return 1;
        }
        return base * (power(base, exponent-1));        // Recursive case
    }
    // Question 4:
    /**
     *Checks if a given string is a palindrome using recursion
     * @param inputString: The string to be checked as Palindrome.
     * @return: Boolean value
     */
    public static boolean isPalindrome(String inputString) {
        // Solution code
        // Convert the string to ignore spaces, punctuation and case
        String input = inputString.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        if (input.length() <= 1) {      // Base case
            return true;
        }
        else if (input.charAt(0) != input.charAt(input.length()-1)) { // Return false if the end characters are not equal
            return false;
        }
        else {
            return isPalindrome(input.substring(1, (input.length() - 1)));      // Recursive case
        }

    }

    public static void main(String[] args) throws IOException {

    }
}

