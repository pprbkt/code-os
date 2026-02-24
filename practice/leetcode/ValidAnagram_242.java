import java.util.Arrays;

class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        char[] SS = s.toCharArray();
        char[] ST = t.toCharArray();

        Arrays.sort(SS);
        Arrays.sort(ST);

        int i = 0;
        while (i < SS.length) {
            if (SS[i] != ST[i]) {
                return false;
            }
            i++;
        }

        return true;
    }
}