/*
Name: Vijay Khot
CWID: 20021838
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DataProcessor {
    private final int NUM_INTEGERS = 100; // Constant value for a number of integers to generate
    private final int RANDOM_INT_RANGE_MAX = 15000; // Constant for range for generating random integers
    private final int RANDOM_INT_RANGE_MIN = 1; // Constant for minimum value for generating random integers

    //No-argument constructor
    public DataProcessor() {
    }


    //Method1: writeData()
    public String writeData() {
        //Creating a new file object referring to  pathname: Java_Program.txt
        File file = new File("Java_Program.txt");
        try {
            //Creating a new FileWriter object referring to  pathname: Java_Program.txt to write
            FileWriter writer = new FileWriter("Java_Program.txt");
            //Looping to generate 'NUM_INTEGERS' random integers and writing each to 'file'
            for (int i = 0; i < NUM_INTEGERS; i++) {
                int randomInteger = (int) Math.round((Math.random() * RANDOM_INT_RANGE_MAX + RANDOM_INT_RANGE_MIN));
                String integerToString = String.valueOf(randomInteger) + " ";
                writer.write(integerToString);
                //if( i < NUM_INTEGERS - 1) { writer.write(" "); }
            }
            writer.close();
            return "Data written to Java_Program.txt successfully";
        }
        catch (IOException ex){
                System.out.println(ex.getMessage());
                return "An error occurred while writing data";
        }
    }


    //Method2: readAndSortData()
    public int[] readAndSortData() {
        int[] integers = new int[NUM_INTEGERS];

        try {
            //Creating a new file object referring to  pathname: Java_Program.txt
            File file = new File("Java_Program.txt");
            //Creating a Scanner object for the file
            Scanner scan = new Scanner(file);

            //Looping over the file to scan for integers
            for (int i = 0; i < NUM_INTEGERS; i++) {
                if(scan.hasNext()) {
                    integers[i] = scan.nextInt();
                }
            }
            // Sorting the integers[] array  using bubble sort method
            boolean swapped;
            for (int i = 0; i < NUM_INTEGERS - 1; i++) {
                swapped = false;
                for(int j = 0; j < NUM_INTEGERS - i - 1; j++) {
                    if (integers[j] > integers[j+1]) {
                        int temp = integers[j];
                        integers[j] = integers[j + 1];
                        integers[j + 1] = temp;
                        swapped = true;
                    }
                }
                if (!swapped) {
                    break;
                }
            }
            //Closing scanner
            scan.close();
            //Returning the integers array
            return integers;
        }
        // Catching FileNotFoundException
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
