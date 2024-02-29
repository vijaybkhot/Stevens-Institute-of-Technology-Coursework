/*
Name: Vijay Khot
CWID: 20021838
*/
public class ComputerTest {

    public static void main(String[] args) {
        // Test Computer
        Computer computer = new Computer("Windows");
        System.out.println("Start Computer: " + computer.start()); // Start
        System.out.println("Start Computer Again: " + computer.start()); // Start Again
        System.out.println("Stop Computer: " + computer.stop()); // Stop
        System.out.println("Stop Computer Again: " + computer.stop()); // Stop Again
    }
}
