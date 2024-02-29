/*
Name: Vijay Khot
CWID: 20021838
*/
public class RefrigeratorTest {

    public static void main(String[] args) {

        // Test Refrigerator
        Refrigerator refrigerator = new Refrigerator(4.5);
        System.out.println("Start Refrigerator: " + refrigerator.start()); // Start
        System.out.println("Start Refrigerator Again: " + refrigerator.start()); // Start Again
        System.out.println("Stop Refrigerator: " + refrigerator.stop()); // Stop
        System.out.println("Stop Refrigerator Again: " + refrigerator.stop()); // Stop Again

    }
}
