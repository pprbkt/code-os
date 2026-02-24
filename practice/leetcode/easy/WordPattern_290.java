class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] words= s.split(" ");
        if(pattern.length() != words.length) return false;

        Map<Character,String> chrToWrd= new HashMap<>();
        Map<String, Character> wrdTochr = new HashMap<>();

        for(int i =0; i< pattern.length(); i++){
            char c= pattern.charAt(i);
            String w= words[i];

            if(chrToWrd.containsKey(c)){
                if (!chrToWrd.get(c).equals(w)) return false;
            }else{
                    chrToWrd.put(c,w);
            }

            if(wrdTochr.containsKey(w)){
                if(wrdTochr.get(w)!= c) return false;
            }
            else{
                wrdTochr.put(w,c);
            }
        }
        return true;

    }
}