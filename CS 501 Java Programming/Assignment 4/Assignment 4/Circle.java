/*
Name: Vijay Khot
CWID: 20021838
*/

public class Circle implements Shape {
    //Data fields
    final double pi = Math.PI;
    double radius;

    //No-argument constructor
    public Circle() {
        this.radius = 1.0;
    }

    //Parameterized constructor
    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    //Method 1: area()
    public double area() {
        return (this.radius * this.radius * pi);
    }

    public static void main(String[] args) {

    }
}
