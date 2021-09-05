package BEAKJOON.BOJ_1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        while(T-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[][] dp = new int[M+1][M+1];

            if(N == M) answer.append(1).append("\n");
            else {
                for(int i=1; i<=M; i++) {
                    dp[i][0] = 1;
                    for(int j=1; j<i; j++) {
                        dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                    }
                    dp[i][i] = 1;
                }
                answer.append(dp[M][N]).append("\n");
            }
        }
        System.out.println(answer);
    }
}
