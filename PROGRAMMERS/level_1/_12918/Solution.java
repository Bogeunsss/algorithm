package PROGRAMMERS.level_1._12918;

public class Solution {
    public boolean solution(String s) {
        boolean answer = true;
        if(s.length() == 4 || s.length() == 6) {
            for(int i=0; i<s.length(); i++) {
                if(!Character.isDigit(s.charAt(i))) {
                    answer = false;
                    break;
                }
            }
        }else {
            answer = false;
        }
        return answer;
    }
//    정규표현식
//    public static boolean solution(String s) {
//        return s.matches("^[0-9]{4}|[0-9]{6}$");
//    }
}
