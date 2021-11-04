package BEAKJOON.BOJ_11066;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder answer = new StringBuilder();
    public static int[][] dp;
    public static int[] files, sum;
    public static int k;

    public static void input() throws IOException {

        k = Integer.parseInt(br.readLine());
        dp = new int[k+1][k+1];
        files = new int[k+1];
        sum = new int[k+1];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i<=k; i++) {
            files[i] = Integer.parseInt(st.nextToken());
            sum[i] = sum[i-1] + files[i];
        }
    }

    public static void combine() {

        for(int z=1; z<k; z++) {
            for(int x=1; x+z<=k; x++) {
                int y = x + z;

                dp[x][y] = Integer.MAX_VALUE;
                for(int m=x; m<y; m++) {
                    dp[x][y] = Math.min(dp[x][y], dp[x][m] + dp[m+1][y] + sum[y] - sum[x - 1]);
                }
            }
        }

        answer.append(dp[1][k]).append("\n");
    }

    public static void combineFiles() throws IOException {

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0 ) {
            input();
            combine();
        }

        System.out.print(answer);
    }

    public static void main(String[] args) throws IOException {

        combineFiles();
    }
}