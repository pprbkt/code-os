// Write a program to calculate CGPA using marks of three subjects.

public class CalculateCGPA {
    public static void main(String [] args){
        double Subject1Marks = 98.7;
        double Subject2Marks = 90.66;
        double Subject3Marks = 99.94;

        double Subject1GradePoints = Subject1Marks/10;
        double Subject2GradePoints = Subject2Marks/10;
        double Subject3GradePoints = Subject3Marks/10;

        double CGPA = (Subject1GradePoints + Subject2GradePoints + Subject3GradePoints)/3;
        
        System.out.println("CGPA = " + CGPA);
    }
}
