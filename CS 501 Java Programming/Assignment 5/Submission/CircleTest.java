/*
Name: Vijay Khot
CWID: 20021838
*/
public class CircleTest {
    public static void main(String[] args) {

        // Create a new circle with no argument constructor
        Circle circle1 = new Circle();
        System.out.println("Circle 1 Area: " + circle1.area());
        System.out.println("Circle 1 Color: " + circle1.fillColor("red"));

        // Create a new circle with parameters
        Circle circle2 = new Circle("blue", 2.5);
        System.out.println("Circle 2 Area: " + circle2.area());
        System.out.println("Circle 2 Color: " + circle2.fillColor("green"));

        // Create a new circle with parameters
        Circle circle3 = new Circle("pink", 5.0);
        System.out.println("Circle 3 Area: " + circle3.area());
        System.out.println("Circle 3 Color: " + circle3.fillColor("purple"));

        // Create a new circle with parameters
        Circle circle4 = new Circle("blue", 0.0);
        System.out.println("Circle 4 Area: " + circle4.area());
        System.out.println("Circle 4 Color: " + circle4.fillColor("green"));

        // Create a new circle with parameters
        Circle circle5 = new Circle("yellow", -2.0);
        System.out.println("Circle 5 Area: " + circle5.area());
        System.out.println("Circle 5 Color: " + circle5.fillColor("orange"));



    }
}
