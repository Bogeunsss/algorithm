package PROGRAMMERS.level_3._12971;

public class Solution {

    public int solution(int[] sticker) {
        int N = sticker.length;
        if(N == 1) return sticker[0];

        int[][] dp = new int[2][N];

        dp[0][0] = dp[0][1] = sticker[0];
        dp[1][1] = sticker[1];

        for(int i=0; i<2; i++) {
            for(int j=2; j<N-1+i; j++) {
                dp[i][j] = Math.max(dp[i][j-1], dp[i][j-2] + sticker[j]);
            }
        }

        return Math.max(dp[0][N-2], dp[1][N-1]);
    }
}
