/*
Name: Vijay Khot
CWID: 20021838
*/
/**
 * A class named `Rectangle` that extends `Shape` and implements `Colorable`
 */
public class Rectangle extends Shape implements Colorable{

    // Data fields
    private double length;
    private double width;

    /**
     * No-argument constructor: Initializes the `length` and `width` to 1.0 and `color` to "white"
     */
    public Rectangle() {
        super("white");
        length = 1.0;
        width = 1.0;
    }

    /**
     * Constructor with parameters
     * @param length: Initializes the `length`
     * @param width: Initializes the `width`
     * @param color: Initializes the `color`
     */
    public Rectangle(double length, double width, String color) {
        super(color);
        this.length = length;
        this.width = width;
    }

    /**
     *
     * @param color: Assign the color to the rectangle
     * @return: The color of the rectangle
     */
    @Override
    public String fillColor(String color) {
        this.color = color;
        return color;
    }

    /**
     *Calculates the area of the rectangle
     * @return: The area of the rectangle
     */
    @Override
    public double area() {
        if (length < 0 || width < 0) {
            throw new IllegalArgumentException("Length or width of a rectangle cannot be negative");
        }
        return (length * width);
    }


}
