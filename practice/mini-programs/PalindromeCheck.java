public class PalindromeCheck {
    public static void main (String [] args){
        String text = "paperbukit";
        int flag = 0;
        for(int i=0; i<text.length()/2; i++){
            if(text.charAt(i) != text.charAt(text.length()-1-i)){
                flag = 0;
            }else{
                flag = 1;
            }
        }
        if (flag == 0){
            System.out.println("The text is not a Palindrome");
        }else{
            System.out.println("The text is a palindrome");
        }
    }
}
