public class ArrayMaxMin {
    public static void main (String [] args){
        int[] arr = {5, 2, 7, 4, 8, 5, 9, 6};
        int min = arr[0], max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) min = arr[i];
            if (arr[i] > max) max = arr[i];
        }
        System.out.println("Array Min: " + min);
        System.out.println("Array Max: " + max);
    }
}
