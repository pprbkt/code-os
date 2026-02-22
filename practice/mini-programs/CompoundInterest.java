// Write a program to calculate compound interest.

public class CompoundInterest {
    public static void main(String[] args) {
        int principal = 15000;
        float rate = 5.5f;
        rate = rate / 100; // Convert 5.5% to decimal 
        int time = 2;
        int period = 4;

        // CI = P * (1 + R/N)^(N*T) - P
        double compoundInterest = principal * Math.pow(1 + rate/period, period * time) - principal;
        
        System.out.println(compoundInterest);
    }
}