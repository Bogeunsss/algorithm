package PROGRAMMERS.level_3._12907;

public class Solution {
    public int solution(int n, int[] money) {
        long[] dp = new long[n+1];

        for(int i=0; i<=n; i++) {
            dp[i] = i % money[0] == 0 ? 1 : 0;
        }
        for(int i=1; i<money.length; i++) {
            for(int j=money[i]; j<=n; j++) {
                dp[j] += dp[j - money[i]];
            }
        }
        return (int)(dp[n] % 1000000007);
    }
}
