package PROGRAMMERS.level_2._84512;

public class Solution {
    private final StringBuilder sb = new StringBuilder();
    private boolean find = false;
    private int answer = 0;

    private void dictionary(String word) {
        if(word.equals(sb.toString())) {
            find = true;
            return;
        }
        if(sb.length() == 5) return;

        for(int i=0; i<5; i++) {
            answer++;
            sb.append("AEIOU".charAt(i));
            dictionary(word);
            sb.deleteCharAt(sb.length()-1);

            if(find) return;
        }
    }

    public int solution(String word) {
        dictionary(word);
        return answer;
    }
}
