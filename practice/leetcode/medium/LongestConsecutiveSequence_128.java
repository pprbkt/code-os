class Solution {
    public int longestConsecutive(int[] nums) {
       
        Set<Integer> set = new HashSet<>();
        int size = 0;
        
        for(int num : nums){
            set.add(num);
        }

        for(int i : set){
            if(!set.contains(i - 1)){
                int current = i;
                int currentsize = 1;
                
                while(set.contains(current + 1)){
                    current++;
                    currentsize++;
                }
            size = Math.max(size, currentsize);
            }
        } return size;
    }
}