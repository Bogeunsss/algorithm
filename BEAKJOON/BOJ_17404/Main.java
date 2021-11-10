package BEAKJOON.BOJ_17404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int n;
    public static int[][] houses, dp;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        houses = new int[n][3];
        dp = new int[n][3];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<3; j++) {
                houses[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void RGBStreets() {

        int answer = Integer.MAX_VALUE;

        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(i == j) dp[0][j] = houses[0][j];
                else dp[0][j] = 1001;
            }
            for(int j=1; j<n; j++) {
                dp[j][0] = houses[j][0] + Math.min(dp[j-1][1], dp[j-1][2]);
                dp[j][1] = houses[j][1] + Math.min(dp[j-1][0], dp[j-1][2]);
                dp[j][2] = houses[j][2] + Math.min(dp[j-1][0], dp[j-1][1]);
            }
            for(int j=0; j<3; j++) {
                if(i == j) continue;
                answer = Math.min(answer, dp[n-1][j]);
            }
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {

        input();
        RGBStreets();
    }
}
