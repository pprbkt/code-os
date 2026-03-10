class Solution {
    public boolean binary_search(int[] nums, int target){
        int l = 0;
        int r = nums.length-1;
        while(l<=r){
            int mid = l+(r-l)/2;
            if(nums[mid]==target){
                return true;
            }else if(nums[mid]<target){
                l = mid+1;
            }else{
                r = mid-1;
            }
        }
        return false;
    }
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;

        // binary search
        int l = 0;
        int r = n-1;
        while(l<=r){ // find range using binary search i.e the row in which target lies
            int mid = l+(r-l)/2;
            if(matrix[mid][0]<=target && target<=matrix[mid][m-1]){
                return binary_search(matrix[mid], target); // then exact index in that row
            }else if(matrix[mid][0]>target){
                r = mid-1;
            }else{
                l = mid+1;
            }
        }
        return false;
    }
}