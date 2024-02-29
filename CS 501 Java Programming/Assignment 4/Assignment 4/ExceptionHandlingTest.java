/*
Name: Vijay Khot
CWID: 20021838
*/
public class ExceptionHandlingTest {

    public static void main(String[] args) {

        ExceptionHandling handler = new ExceptionHandling();

        System.out.println("Input: handler.validateNumber(\"433\")");
        System.out.println("Output: " + handler.validateNumber("433"));
        System.out.println();
        System.out.println("Input: handler.validateNumber(\"-433\")");
        System.out.println("Output: " + handler.validateNumber("-433"));
        System.out.println();
        System.out.println("Input: handler.validateNumber(\"AA433\")");
        System.out.println("Output: " + handler.validateNumber("AA433"));
        System.out.println();
        System.out.println("Input: handler.validateNumber(\"0\")");
        System.out.println("Output: " + handler.validateNumber("0"));
        System.out.println();
        System.out.println("Input: handler.validateNumber(\"12414141243123124124234125125254535\")");
        System.out.println("Output: " + handler.validateNumber("12414141243123124124234125125254535"));



    }
}
