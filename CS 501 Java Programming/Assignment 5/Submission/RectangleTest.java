/*
Name: Vijay Khot
CWID: 20021838
*/
public class RectangleTest {
    public static void main(String[] args) {

        // Create a new rectangle using no-argument constructor
        Rectangle rectangle1 = new Rectangle();
        System.out.println("Rectangle 1 Area: " + rectangle1.area());
        System.out.println("Rectangle 1 Color: " + rectangle1.fillColor("yellow"));

        // Create a new rectangle with parameters
        Rectangle rectangle2 = new Rectangle(4.0, 5.0, "purple");
        System.out.println("Rectangle 2 Area: "  + rectangle2.area());
        System.out.println("Rectangle 2 Color: " + rectangle2.fillColor("blue"));

        // Create a new rectangle with parameters
        Rectangle rectangle3 = new Rectangle(20.0, 25.5, "black");
        System.out.println("Rectangle 3 Area: "  + rectangle3.area());
        System.out.println("Rectangle 3 Color: " + rectangle3.fillColor("pink"));

        // Create a new rectangle with parameters
        Rectangle rectangle4 = new Rectangle(0.0, 0.0, "orange");
        System.out.println("Rectangle 4 Area: "  + rectangle4.area());
        System.out.println("Rectangle 4 Color: " + rectangle4.fillColor("green"));

        // Create a new rectangle with parameters
        Rectangle rectangle5 = new Rectangle(-2.0, -3.0, "maroon");
        System.out.println("Rectangle 5 Area: "  + rectangle5.area());
        System.out.println("Rectangle 5 Color: " + rectangle5.fillColor("olive green"));

    }
}
