/*
Name: Vijay Khot
CWID: 20021838
*/
public class DataProcessorTest {

    public static void main(String[] args) {

        DataProcessor dataProcessor = new DataProcessor();

        // Write data to the file
        String writeResult = dataProcessor.writeData();
        System.out.println(writeResult);

        // Read and sort data from the file
        int[] sortedIntegers = dataProcessor.readAndSortData();

        // Display the sorted integers
        System.out.print("Sorted Integers: ");
        for (int num : sortedIntegers) {
            System.out.print(num + " ");
        }

    }

}
