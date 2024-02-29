public class CircleTest {
    public static void main(String[] args) {

        Circle circle = new Circle();
        double expectedRadius = 1.0;
        double expectedArea = Math.round(Math.PI * 100.0) / 100.0;
        double expectedCircumference = Math.round(2 * Math.PI * 100.0) / 100.0;

        if (expectedRadius == circle.radius && expectedArea == circle.area() && expectedCircumference == circle.circumference()) {
            // Test passed
            System.out.println("Test passed successfully.");
        } else {
            // Test failed
            System.err.println("Test failed: Values do not match!");
        }


        Circle circle2 = new Circle(72.5);
        double expectedRadius2 = 72.5;
        double expectedArea2 = Math.round(Math.PI * 72.5 * 72.5 * 100.0) / 100.0;
        double expectedCircumference2 = Math.round(2 * Math.PI * 72.5 * 100.0) / 100.0;

        if (expectedRadius2 == circle2.radius && expectedArea2 == circle2.area() && expectedCircumference2 == circle2.circumference()) {
            // Test passed
            System.out.println("Test passed successfully.");
        } else {
            // Test failed
            System.err.println("Test failed: Values do not match!");
        }


        Circle circle3 = new Circle(0);
        double expectedRadius3 = 0;
        double expectedArea3 = Math.round(Math.PI * 0 * 0 * 100.0) / 100.0;
        double expectedCircumference3 = Math.round(2 * Math.PI * 0 * 100.0) / 100.0;

        if (expectedRadius3 == circle3.radius && expectedArea3 == circle3.area() && expectedCircumference3 == circle3.circumference()) {
            // Test passed
            System.out.println("Test passed successfully.");
        } else {
            // Test failed
            System.err.println("Test failed: Values do not match!");
        }


        Circle circle4 = new Circle(-3.0);
        double expectedRadius4 = -3.0;
        double expectedArea4 = Math.round(Math.PI * (-3.0) * (-3.0) * 100.0) / 100.0;
        double expectedCircumference4 = Math.round(2 * Math.PI * (-3.0) * 100.0) / 100.0;

        if (expectedRadius4 == circle4.radius && expectedArea4 == circle4.area() && expectedCircumference4 == circle4.circumference()) {
            // Test passed
            System.out.println("Test passed successfully.");
        } else {
            // Test failed
            System.err.println("Test failed: Values do not match!");

        }


        Circle circle5 = new Circle(1000.0);
        double expectedRadius5 = 1000.0;
        double expectedArea5 = Math.round(Math.PI * 1000.0 * 1000.0 * 100.0) / 100.0;
        double expectedCircumference5 = Math.round(2 * Math.PI * 1000.0 * 100.0) / 100.0;

        if (expectedRadius5 == circle5.radius && expectedArea5 == circle5.area() && expectedCircumference5 == circle5.circumference()) {
            // Test passed
            System.out.println("Test passed successfully.");
        } else {
            // Test failed
            System.err.println("Test failed: Values do not match!");

        }


        //System.out.println(expectedRadius5 + " " +circle5.radius);
        //System.out.println(expectedArea5 + " " +circle5.area());
        //System.out.println(expectedCircumference5 + " " +circle5.circumference());

        //System.out.println(expectedRadius3 + " " +circle3.radius);
        //System.out.println(expectedArea3 + " " +circle3.area());
        //System.out.println(expectedCircumference3 + " " +circle3.circumference());

    }

}
