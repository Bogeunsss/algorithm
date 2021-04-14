package PROGRAMMERS.level_1._12948;

public class Solution {
    public String solution(String phone_number) {
        StringBuilder answer = new StringBuilder();

        for(int i=phone_number.length()-1; i>=0; i--) {
            if(answer.length() >= 4) answer.append('*');
            else answer.append(phone_number.charAt(i));
        }
        return answer.reverse().toString();
    }
}
