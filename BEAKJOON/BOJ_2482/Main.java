package BEAKJOON.BOJ_2482;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static final int MOD = 1000000003;

    public static int n, k;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
    }

    public static void colorCircle() {

        int[][] dp = new int[n+1][k+1];

        for(int i=0; i<=n; i++) {
            dp[i][1] = i;
            dp[i][0] = 1;
        }
        for(int i=2; i<=n; i++) {
            for(int j=2; j<=k; j++) {
                dp[i][j] = (dp[i-2][j-1] + dp[i-1][j]) % MOD;
            }
        }

        System.out.println((dp[n-1][k] + dp[n-3][k-1]) % MOD);
    }

    public static void main(String[] args) throws IOException {

        input();
        colorCircle();
    }
}