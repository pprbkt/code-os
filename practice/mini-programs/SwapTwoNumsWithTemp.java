// Write a program to swap two numbers using a temporary variable and display the result.

public class SwapTwoNumsWithTemp {
    public static void main(String[] args) {
        int num1 = 5;
        int num2 = 10;
        System.out.println("num1: " + num1 + ", num2: " + num2);


        int temp = num1;
        num1 = num2;
        num2 = temp;

        System.out.println("num1: " + num1 + ", num2: " + num2);
    }
}
