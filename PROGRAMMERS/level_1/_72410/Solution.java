package PROGRAMMERS.level_1._72410;

public class Solution {
    public String solution(String new_id) {
        String step1 = new_id.toLowerCase();

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<step1.length(); i++) {
            if (Character.isLowerCase(step1.charAt(i)) || Character.isDigit(step1.charAt(i)) || step1.charAt(i) == '-' || step1.charAt(i) == '_' || step1.charAt(i) == '.') {
                sb.append(step1.charAt(i));
            }
        }
        String step2 = sb.toString();

        sb = new StringBuilder();
        int pos = 0;
        while(pos < step2.length()) {
            if(step2.charAt(pos) == '.') {
                while(step2.charAt(pos) == '.') {
                    pos++;
                    if(pos > step2.length()-1) break;
                }
                sb.append('.');
            }else {
                sb.append(step2.charAt(pos));
                pos++;
            }
        }
        String step3 = sb.toString();

        if(step3.charAt(0) == '.' ) step3 = step3.substring(1);
        if(step3.length() > 0 && step3.charAt(step3.length()-1) == '.') step3 = step3.substring(0, step3.length()-1);
        String step4 = step3;

        if(step4.length() == 0) step4 = "a";
        String step5 = step4;

        if(step5.length() >= 16) {
            if(step5.charAt(14) == '.') step5 = step5.substring(0, 14);
            else step5 = step5.substring(0, 15);

        }
        if(step5.length() <= 2) {
            sb = new StringBuilder(step5);
            while(sb.length() < 3) {
                sb.append(step5.charAt(step5.length()-1));
            }
            step5 = sb.toString();
        }

        return step5;
    }

//    정규표현식 사용(더 느림)
//    public String solution(String new_id) {
//        String answer = new_id.toLowerCase();
//
//        answer = answer.replaceAll("[^-_.a-z0-9]", "");
//        answer = answer.replaceAll("[.]{2,}", ".");
//        answer = answer.replaceAll("^[.]|[.]$", "");
//        if(answer.equals("")) answer = "a";
//        if(answer.length() >= 16) {
//            answer = answer.substring(0, 15);
//            answer = answer.replaceAll("[.]$", "");
//        }
//        if(answer.length() <= 2) {
//            while(answer.length() < 3) answer += answer.charAt(answer.length()-1);
//        }
//
//        return answer;
//    }
}
