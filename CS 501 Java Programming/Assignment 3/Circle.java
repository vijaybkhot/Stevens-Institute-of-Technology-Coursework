public class Circle {
    final double pi = Math.PI;
    double radius;

    public Circle() {
        this.radius = 1.0;
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    public double area() {
        return Math.round((this.radius * this.radius * pi)*100.0) / 100.0;
    }

    public double circumference() {
        return Math.round(2 * pi * this.radius * 100.0) / 100.0;
    }
}
