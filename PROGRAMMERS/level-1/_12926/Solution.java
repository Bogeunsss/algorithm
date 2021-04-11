package PROGRAMMERS._12926;

public class Solution {
    public String solution(String s, int n) {
        StringBuilder answer = new StringBuilder();

        for(int i=0; i<s.length(); i++) {
            int src = (int) s.charAt(i);
            if(s.charAt(i) == ' ') {
                answer.append(s.charAt(i));
            }else if(Character.isUpperCase(s.charAt(i))) {
                answer.append((char) ((src - 65 + n) % 26 + 65));
            }else {
                answer.append((char) ((src - 97 + n) % 26 + 97));
            }
        }
        return answer.toString();
    }
}
