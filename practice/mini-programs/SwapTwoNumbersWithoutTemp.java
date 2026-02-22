// Write a program to swap two numbers without using a temporary variable.

public class SwapTwoNumbersWithoutTemp {
    public static void main(String[] args) {
        int num1 = 5;
        int num2 = 10;
        System.out.println("num1: " + num1 + ", num2: " + num2);

        num1 = num1-num2;
        num2 = num1+num2;
        num1 = num2-num1;

        System.out.println("num1: " + num1 + ", num2: " + num2);

    }
}
