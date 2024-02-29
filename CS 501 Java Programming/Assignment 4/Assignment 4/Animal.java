/*
Name: Vijay Khot
CWID: 20021838
*/
public class Animal {
    //Data fields
    int age;
    double weight;

    //No-argument constructor
    public Animal() {
        age = 0;
        weight = 0;
    }

    // Parameterized constructor
    public Animal (int age, double weight) {
        this.age = age;
        this.weight = weight;
    }

    //Method 1: eat()
    public String eat() {
        return "The animal is eating";
    }

    //Method 2: move()
    public String move() {
        return "The animal is moving";
    }

}
