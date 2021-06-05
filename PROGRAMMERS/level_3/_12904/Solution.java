package PROGRAMMERS.level_3._12904;

public class Solution {

    public static int palindrome(String s, int l, int h) {
        int low = l, high = h;

        while(0 <= low && high < s.length() && s.charAt(low) == s.charAt(high)) {
            low--;
            high++;
        }
        return high - low - 1;
    }

    public int solution(String s) {
        int answer = 1;

        for(int i=0; i<s.length()-1; i++) {
            answer = Math.max(answer, palindrome(s, i, i));
            answer = Math.max(answer, palindrome(s, i, i+1));
        }

        return answer;
    }
}
