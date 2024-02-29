/*
Name: Vijay Khot
CWID: 20021838
*/
public class ExceptionHandling {
    int number;

    //No-argument constructor
    public ExceptionHandling() {
        this.number = 0;
    }

    //Method 1: validateNumber()
    public String validateNumber(String input) {
        try {
            int integer = Integer.parseInt(input);
            if (integer < 0) {
                throw new NegativeNumberException("Negative numbers are not allowed");
            }
            this.number = integer;
            return "Number accepted: " + integer;
        }

        catch (NumberFormatException ex) {
            return "Please enter a valid integer";
        }

        catch (NegativeNumberException ex) {
                return "Negative numbers are not allowed";
        }

        }

    }
