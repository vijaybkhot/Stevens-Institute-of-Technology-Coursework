/*
Name: Vijay Khot
CWID: 20021838
*/
/**
 * A class named `Circle` that extends `Shape` and implements `Colorable` :
 */
public class Circle extends Shape implements Colorable{
    private double radius; // The radius of the circle

    /**
     * No-argument constructor: Initializes the `radius` to 1.0 and ` color` to "white".
     */
    public Circle(){
        super("white");
        radius = 1.0;
    }

    /**
     * Constructor with parameters: Initializes the `radius` and `color` with given values
     * @param color: The color of the circle
     * @param radius: The radius of the circle
     */
    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    /**
     * Calculates the area of the circle
     * @return: The area of the circle
     */
    @Override
    public double area() {
        double pi = 3.14;
        if (radius < 0) {
            throw new IllegalArgumentException("Radius cannot be negative");
        }
        return pi * radius * radius;
    }

    /**
     *
     * @param color: Assign the color to the circle
     * @return: The color of the circle
     */
    @Override
    public String fillColor(String color) {
        this.color = color;
        return color;
    }
}
