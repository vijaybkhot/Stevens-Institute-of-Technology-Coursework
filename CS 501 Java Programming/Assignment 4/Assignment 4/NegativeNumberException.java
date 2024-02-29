/*
Name: Vijay Khot
CWID: 20021838
*/
public class NegativeNumberException extends Exception{


    public NegativeNumberException() {
        super("Negative numbers are not allowed");
    }

    public NegativeNumberException(String message) {
        super(message);
    }

}
