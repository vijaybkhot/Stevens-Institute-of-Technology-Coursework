public class StudentTest {
    public static void main(String[] args) {
        //Create a student object using first constructor
        Student student1 = new Student();

        //Assign parameters individually
        student1.name = "Jason";
        student1.rollNumber = 34;
        student1.marks1 = 123;
        student1.marks2 = 145;
        student1.marks3 = 144;

        //Create a student object using second constructor
        Student student2 = new Student("Eric", 26, 100, 100, 100);
        Student student3 = new Student("Chris", 4, 88, 104, 92);
        Student student4 = new Student("Jessica", 7, 80, 100, 60);
        Student student5 = new Student("Harish", 1001, 66, 100, 55);


        //Call methods and print
        System.out.println(student1.name + " with roll number " + student1.rollNumber + " scored " + student1.marks1 + ", " + student1.marks2 + " and " + student1.marks3 + " in three subjects.");
        System.out.println(student1.name + " received overall grade " + student1.grade() + " with an average score of " + student1.calculateAverage() + " in three subjects.");
        System.out.println();

        System.out.println(student2.name + " with roll number " + student2.rollNumber + " scored " + student2.marks1 + ", " + student2.marks2 + " and " + student2.marks3 + " in three subjects.");
        System.out.println(student2.name + " received overall grade " + student2.grade() + " with an average score of " + student2.calculateAverage() + " in three subjects.");
        System.out.println();

        System.out.println(student3.name + " with roll number " + student3.rollNumber + " scored " + student3.marks1 + ", " + student3.marks2 + " and " + student3.marks3 + " in three subjects.");
        System.out.println(student3.name + " received overall grade " + student3.grade() + " with an average score of " + student3.calculateAverage() + " in three subjects.");
        System.out.println();

        System.out.println(student4.name + " with roll number " + student4.rollNumber + " scored " + student4.marks1 + ", " + student4.marks2 + " and " + student4.marks3 + " in three subjects.");
        System.out.println(student4.name + " received overall grade " + student4.grade() + " with an average score of " + student4.calculateAverage() + " in three subjects.");
        System.out.println();

        System.out.println(student5.name + " with roll number " + student5.rollNumber + " scored " + student5.marks1 + ", " + student5.marks2 + " and " + student5.marks3 + " in three subjects.");
        System.out.println(student5.name + " received overall grade " + student5.grade() + " with an average score of " + student5.calculateAverage() + " in three subjects.");

    }
}
