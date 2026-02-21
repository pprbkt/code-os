// Write a java program which asks the user to enter his/her name and greets them with "Hello <name>, Have a good day" text.

import java.util.Scanner;

public class Greeting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your name: ");
        String name = sc.nextLine();

        System.out.println("Hello " + name + ", Have a good day");
    }
}
