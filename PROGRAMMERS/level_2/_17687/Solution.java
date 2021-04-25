package PROGRAMMERS.level_2._17687;

public class Solution {
    public String solution(int n, int t, int m, int p) {
        char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        String game = "0";
        String answer = "";

        for(int i=1; answer.length()<t; i++) {
            String temp = "";
            int x = i;
            while(x > 0) {
                temp += numbers[x%n];
                x /= n;
            }
            for(int j=temp.length()-1; j>=0; j--) game += temp.charAt(j);
            if(game.length() < t * m) continue;
            for(int j=0; j<game.length(); j+=m) {
                answer += game.charAt(j + p - 1);
                if(answer.length() >= t) break;
            }
        }
        return answer;
    }
}