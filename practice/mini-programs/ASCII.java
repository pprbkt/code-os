// Write a program to display the ASCII value of a given character.

import java.util.Scanner;

public class ASCII {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char character = sc.next().charAt(0);
        int asciiValue = (int)character;
        System.out.println(asciiValue);
    }
}
