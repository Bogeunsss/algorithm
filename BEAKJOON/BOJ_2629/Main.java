package BEAKJOON.BOJ_2629;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static final int MAX = 40000;

    public static int n, m;
    public static int[] weights, beads;
    public static boolean[][] dp;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        weights = new int[n+1];
        dp = new boolean[n+1][MAX+1];
        dp[0][0] = true;

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i<=n; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        beads = new int[m];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<m; i++) {
            beads[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static void measure() {

        for(int i=1; i<=n; i++) {

            int w = weights[i];
            for(int j=0; j<=MAX; j++) {
                dp[i][j] = dp[i-1][j] || dp[i-1][Math.abs(j-w)];
                if(j + w <= MAX) dp[i][j] = dp[i][j] || dp[i-1][j + w];
            }
        }
        for(int i=0; i<m; i++) {
            if(dp[n][beads[i]]) System.out.print("Y ");
            else System.out.print("N ");
        }
    }

    public static void main(String[] args) throws IOException{

        input();
        measure();
    }
}