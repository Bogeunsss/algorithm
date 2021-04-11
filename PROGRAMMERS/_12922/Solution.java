package PROGRAMMERS._12922;

public class Solution {
    public static String solution(int n) {
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

    public static void main(String[] args) {
        int n = 2;

        System.out.println(solution(n));
    }
}
