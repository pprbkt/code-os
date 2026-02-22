// Write a program to convert a temperature from Celsius to Fahrenheit using the formula: F = (C × 9/5) + 32

import java.util.Scanner;

public class CelciusToFahrenheit{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double Celcius = sc.nextDouble();
        double Fahrenheit = (Celcius * 9/5) + 32;

        System.out.println(Fahrenheit);
    }
}