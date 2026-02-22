// Write a program to convert Fahrenheit to Celsius.

import java.util.Scanner;

public class FahrenheitToCelcius {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double Fahrenheit = sc.nextDouble();
        double Celcius = (Fahrenheit - 32) * 5/9;

        System.out.println(Celcius);
    }
}
