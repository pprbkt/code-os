// Write a program that checks whether a given number is even or odd.

import java.util.Scanner;

public class EvenOrOdd {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        if (num%2==0){
            System.out.println("even");
        }else{
            System.out.println("odd");
        }
    }
}
