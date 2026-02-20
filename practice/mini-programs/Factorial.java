public class Factorial{
    public static void main (String [] args){
        int num = 18;
        long factorial = 1;
        for(int i=1; i<=num; ++i){
            factorial = factorial*i;
        }
        System.out.println("Factorial of the number is: " + factorial);
    }
}