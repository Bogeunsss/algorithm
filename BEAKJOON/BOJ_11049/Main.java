package BEAKJOON.BOJ_11049;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int n;
    public static int[][][] dp;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        dp = new int[n+1][n+1][3];

        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            dp[i][i][0] = x;
            dp[i][i][1] = y;
        }
    }

    public static void matrixMultiplication() {

        for(int k=1; k<n; k++) {
            for(int i=1; i+k<=n; i++) {
                int j = i + k;

                dp[i][j][2] = Integer.MAX_VALUE;
                for(int m=i; m<j; m++) {
                    if(dp[i][j][2] > dp[i][m][0] * dp[m+1][j][0] * dp[m+1][j][1] + dp[i][m][2] + dp[m+1][j][2]) {
                        dp[i][j][2] = dp[i][m][0] * dp[m+1][j][0] * dp[m+1][j][1] + dp[i][m][2] + dp[m+1][j][2];
                        dp[i][j][0] = dp[i][m][0];
                        dp[i][j][1] = dp[m+1][j][1];
                    }
                }
            }
        }

        System.out.println(dp[1][n][2]);
    }

    public static void main(String[] args) throws IOException {

        input();
        matrixMultiplication();
    }
}
