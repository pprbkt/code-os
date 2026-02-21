// Write a java program to detect whether a number entered by the user is integer or not.

import java.util.Scanner;

public class IntegerOrNot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        if (num % 1 == 0){
            System.out.println("The number is an integer");
        }else{
            System.out.println("The number is not an integer");
        }
    }
}
