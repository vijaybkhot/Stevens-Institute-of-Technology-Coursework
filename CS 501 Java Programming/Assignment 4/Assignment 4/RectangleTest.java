public class RectangleTest {

    public static void main(String[] args) {

        Rectangle rectangle1 = new Rectangle();
        Rectangle rectangle2 = new Rectangle(22, 44);
        Rectangle rectangle3 = new Rectangle(2.2, 4.4);
        Rectangle rectangle4 = new Rectangle(0, 5000);
        Rectangle rectangle5 = new Rectangle(1213123333, 121231233);

        System.out.println("Input: rectangle1.height");
        System.out.println("Output: " + rectangle1.height);
        System.out.println("Input: rectangle1.width");
        System.out.println("Output: " + rectangle1.width);
        System.out.println("Input: rectangle1.area()");
        System.out.println("Output: " + rectangle1.area());
        System.out.println();

        System.out.println("Input: rectangle2.height");
        System.out.println("Output: " + rectangle2.height);
        System.out.println("Input: rectangle2.width");
        System.out.println("Output: " + rectangle2.width);
        System.out.println("Input: rectangle2.area()");
        System.out.println("Output: " + rectangle2.area());
        System.out.println();

        System.out.println("Input: rectangle3.height");
        System.out.println("Output: " + rectangle3.height);
        System.out.println("Input: rectangle3.width");
        System.out.println("Output: " + rectangle3.width);
        System.out.println("Input: rectangle3.area()");
        System.out.println("Output: " + rectangle3.area());
        System.out.println();

        System.out.println("Input: rectangle4.height");
        System.out.println("Output: " + rectangle4.height);
        System.out.println("Input: rectangle4.width");
        System.out.println("Output: " + rectangle4.width);
        System.out.println("Input: rectangle4.area()");
        System.out.println("Output: " + rectangle4.area());
        System.out.println();

        System.out.println("Input: rectangle5.height");
        System.out.println("Output: " + rectangle5.height);
        System.out.println("Input: rectangle5.width");
        System.out.println("Output: " + rectangle5.width);
        System.out.println("Input: rectangle5.area()");
        System.out.println("Output: " + rectangle5.area());
        System.out.println();

    }
}
