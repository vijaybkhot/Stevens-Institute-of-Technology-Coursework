/*
Name: Vijay Khot
CWID: 20021838
*/
public class Bird extends Animal{

    //No-argument constructor
    public Bird () {
        super(); // Calls the no-argument constructor of the animal class
    }

    //Method 1: fly()
    public String fly() {
        return "The bird is flying";
    }

    @Override
    //Method 2: move()
    public String move() {
        return "The bird is flying";
    }
}
