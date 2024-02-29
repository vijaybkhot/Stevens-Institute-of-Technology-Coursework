// Java class called `Calculator` for performing basic arithmetic operations
public class Calculator {

    //A No-argument constructor
    public Calculator() {

    }

    //A method that takes two double values a and b as parameters and returns their sum
    public double add(double a, double b) {
        return a + b;
    }

    //A method that takes two double values a and b as parameters and returns the result of a - b
    public double subtract(double a, double b) {
        return a - b;
    }


    //A method that takes two double values a and b as parameters and returns their product
    public double multiply(double a, double b) {
        return a * b;
    }

    //A method that takes two double values a and b as parameters and returns the result of a / b
    public double divide(double a, double b) {
        return a / b;
    }
    public static void main(String[] args) {

        //Create a Calculator object
        Calculator calculator = new Calculator();

        //Declare and initialize variable to use for methods
        double x = 4.677;
        double y = 2.988;

        //Call methods and display results
        System.out.println("The sum of " + x + " & " + y + " is " + Math.round(calculator.add(x, y) * 100.0)/100.0);
        System.out.println("The difference  between " + x + " & " + y + " is " + Math.round(calculator.subtract(x, y) * 100.0)/100.0);
        System.out.println("The product of " + x + " & " + y + " is " + Math.round(calculator.multiply(x, y) * 100.0)/100.0);
        System.out.println("The quotient after dividing " + x + " by " + y + " is " + Math.round(calculator.divide(x, y) * 100.0)/100.0);


    }
}
