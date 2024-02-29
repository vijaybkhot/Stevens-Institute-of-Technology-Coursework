
//Creating a Java class named `Student` for storing student information and calculating grades
public class Student {
    String name; //The name of the student
    int rollNumber; //The roll number of the student
    double marks1, marks2, marks3; //The marks in three subjects

    /* Creating a no-argument constructor that initializes the `name` to an empty string and
    `rollNumber`, `marks1`, `marks2`, `marks3` to zero */
    public Student() {
        this.name = "";
        this.rollNumber = 0;
        this.marks1 = 0;
        this.marks2 = 0;
        this.marks3 = 0;
    }

    //Creating a constructor that initializes all fields with given values
    public Student(String studentName, int roll, double M1, double M2, double M3) {
        this.name = studentName;
        this.rollNumber = roll;
        this.marks1 = M1;
        this.marks2 = M2;
        this.marks3 = M3;
    }

    // A method to calculate and return the average of `marks1`, `marks2`, and `marks3`
    public double calculateAverage() {
        return Math.round(((this.marks1 + this.marks2 + this.marks3)/3) * 100.0) / 100.0;
    }

    // A method to calculate and return the grade of a student based on the average marks
    public char grade() {
        if (this.calculateAverage() > 100) {
            return 'A';
        } else if (this.calculateAverage() > 80) {
            return 'B';
        }
        else return 'C';
    }

    public static void main (String[] args) {
        //Create a student object using first constructor
        Student Jason = new Student();

        //Assign parameters individually
        Jason.name = "Jason";
        Jason.rollNumber = 34;
        Jason.marks1 = 123;
        Jason.marks2 = 145;
        Jason.marks3 = 144;

        //Create a student object using second constructor
        Student Eric = new Student("Eric", 26, 79, 133, 112);


        //Call methods and print
        System.out.println(Jason.calculateAverage());
        System.out.println(Jason.grade());
        System.out.println(Eric.calculateAverage());
        System.out.println(Eric.grade());
    }

}
