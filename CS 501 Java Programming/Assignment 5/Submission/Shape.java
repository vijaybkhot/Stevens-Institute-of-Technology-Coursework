/*
Name: Vijay Khot
CWID: 20021838
*/

/**
 * An Abstract  class named `Shape` :
 */
public abstract class Shape {
    // Data field
    String color;

    /**
     * Constructor with parameter: Initializes the ` color` with the given value.
     * @param color: The color of the shape
     */
    public Shape(String color){
        this.color = color;
    }

    /**
     * Abstract method area()
     * Calculates and returns the area of the shape
     * @return: The area of the shape
     */
    public abstract double area();
}
