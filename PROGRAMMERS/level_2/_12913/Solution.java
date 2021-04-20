package PROGRAMMERS.level_2._12913;

public class Solution {
    public int solution(int[][] land) {
        int answer = 0;
        int size = land.length;
        int[][] dp = new int[size][4];

        for(int i=0; i<4; i++) {
            dp[size-1][i] = land[size-1][i];
        }
        for(int i=size-1; i>0; i--) {
            for(int j=0; j<4; j++) {
                for(int k=0; k<4; k++) {
                    if(j != k) {
                         dp[i-1][j] = Math.max(dp[i-1][j], dp[i][k] + land[i-1][j]);
                    }
                }
            }
        }
        for(int i=0; i<4; i++) {
            answer = Math.max(answer, dp[0][i]);
        }
        return answer;
    }
}
