class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int freq[] = new int[26];
        for(char c : magazine.toCharArray()){
            int i = (int) c - 'a';
            freq[i]++;
        }

        for(char c : ransomNote.toCharArray()){
            int i = (int) c - 'a';
            if(freq[i] > 0)
                freq[i]--;
            else
                return false;
        }
        return true;
    }
}