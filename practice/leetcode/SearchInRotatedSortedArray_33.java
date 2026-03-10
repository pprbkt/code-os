class Solution {

    public static int search(int[] nums, int target){
        return tSearch(nums, target, 0, nums.length - 1);
    }

    public static int tSearch(int[] nums, int target, int si, int ei) {
        if (si > ei) {
            return -1;
        }
        int mid = si + (ei - si) / 2;

        if (nums[mid] == target) {
            return mid;
        }

        if (nums[si] <= nums[mid]) {
            if (nums[si] <= target && target <= nums[mid]) {
                return tSearch(nums, target, si, mid - 1);
            } else {
                return tSearch(nums, target, mid + 1, ei);
            }
        } else {
            if (nums[mid] <= target && target <= nums[ei]) {
                return tSearch(nums, target, mid + 1, ei);
            } else {
                return tSearch(nums, target, si, mid - 1);
            }
        }
    }
}