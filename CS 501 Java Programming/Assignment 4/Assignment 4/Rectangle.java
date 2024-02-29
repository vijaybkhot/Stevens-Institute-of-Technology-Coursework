/*
Name: Vijay Khot
CWID: 20021838
*/
public class Rectangle implements Shape{
    // Data fields
    double height;
    double width;

    //No-argument constructor
    public Rectangle() {
        height = 1.0;
        width = 1.0;
    }

    // Parameterized constructor
    public Rectangle(double height, double width) {
        this.height = height;
        this.width = width;
    }

    @Override
    //Method 1: area()
    public double area() {
        return this.height * this.width;
    }
}
