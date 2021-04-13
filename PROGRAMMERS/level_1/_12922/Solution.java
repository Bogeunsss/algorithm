package PROGRAMMERS.level_1._12922;

public class Solution {
    public String solution(int n) {
        StringBuilder answer = new StringBuilder();
        String waterMelon = "수박";
        if(n % 2 == 1) {
            for(int i=0; i<n-1; i+=2) answer.append(waterMelon);
            answer.append("수");
        }else {
            for(int i=0; i<n; i+=2) answer.append(waterMelon);
        }

        return answer.toString();
    }
}
