// Write a program to calculate simple interest given principal, rate, and time.

public class SimpleInterest {
    public static void main(String[] args) {
        int principal = 15000;
        float rate = 5.5f;
        rate = rate/100; // Convert 5.5% to decimal 
        int time = 2;

        double simpleInterest = (principal * rate * time)/100;
        
        System.out.println(simpleInterest);
    }
}
