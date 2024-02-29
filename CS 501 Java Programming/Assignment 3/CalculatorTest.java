public class CalculatorTest {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        //Declare and initialize variable to use for methods
        double x1 = 4.677;
        double y1 = 2.988;

        double x2 = 0.23333333333;
        double y2 = -49.6611111111;

        double x3 = 56;
        double y3 = 56;

        double x4 = 0;
        double y4 = 2.988;

        double x5 = 100;
        double y5 = 1;

        //Call methods and display results
        System.out.println("The sum of " + x1 + " & " + y1 + " is " + Math.round(calculator.add(x1, y1) * 100.0)/100.0);
        System.out.println("The difference  between " + x1 + " & " + y1 + " is " + Math.round(calculator.subtract(x1, y1) * 100.0)/100.0);
        System.out.println("The product of " + x1 + " & " + y1 + " is " + Math.round(calculator.multiply(x1, y1) * 100.0)/100.0);
        System.out.println("The quotient after dividing " + x1 + " by " + y1 + " is " + Math.round(calculator.divide(x1, y1) * 100.0)/100.0);

        System.out.println("The sum of " + x2 + " & " + y2 + " is " + Math.round(calculator.add(x2, y2) * 100.0)/100.0);
        System.out.println("The difference  between " + x2 + " & " + y2+ " is " + Math.round(calculator.subtract(x2, y2) * 100.0)/100.0);
        System.out.println("The product of " + x2 + " & " + y2 + " is " + Math.round(calculator.multiply(x2, y2) * 100.0)/100.0);
        System.out.println("The quotient after dividing " + x2 + " by " + y2 + " is " + Math.round(calculator.divide(x2, y2) * 100.0)/100.0);

        System.out.println("The sum of " + x3 + " & " + y3 + " is " + Math.round(calculator.add(x3, y3) * 100.0)/100.0);
        System.out.println("The difference  between " + x3 + " & " + y3 + " is " + Math.round(calculator.subtract(x3, y3) * 100.0)/100.0);
        System.out.println("The product of " + x3 + " & " + y3 + " is " + Math.round(calculator.multiply(x3, y3) * 100.0)/100.0);
        System.out.println("The quotient after dividing " + x3 + " by " + y3 + " is " + Math.round(calculator.divide(x3, y3) * 100.0)/100.0);

        System.out.println("The sum of " + x4 + " & " + y4 + " is " + Math.round(calculator.add(x4, y4) * 100.0)/100.0);
        System.out.println("The difference  between " + x4 + " & " + y4 + " is " + Math.round(calculator.subtract(x4, y4) * 100.0)/100.0);
        System.out.println("The product of " + x4 + " & " + y4 + " is " + Math.round(calculator.multiply(x4, y4) * 100.0)/100.0);
        System.out.println("The quotient after dividing " + x4 + " by " + y4 + " is " + Math.round(calculator.divide(x4, y4) * 100.0)/100.0);

        System.out.println("The sum of " + x5 + " & " + y5 + " is " + Math.round(calculator.add(x5, y5) * 100.0)/100.0);
        System.out.println("The difference  between " + x5 + " & " + y5 + " is " + Math.round(calculator.subtract(x5, y5) * 100.0)/100.0);
        System.out.println("The product of " + x5 + " & " + y5+ " is " + Math.round(calculator.multiply(x5, y5) * 100.0)/100.0);
        System.out.println("The quotient after dividing " + x5 + " by " + y5 + " is " + Math.round(calculator.divide(x5, y5) * 100.0)/100.0);


    }
}
