package BEAKJOON.BOJ_12865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] goods = new int[N+1][2];
        int[] dp = new int[K+1];

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            goods[i][0] = Integer.parseInt(st.nextToken());
            goods[i][1] = Integer.parseInt(st.nextToken());
        }
        for(int i=1; i<=N; i++) {
            for(int j=K; j-goods[i][0]>=0; j--) {
                dp[j] = Math.max(dp[j], dp[j - goods[i][0]] + goods[i][1]);
            }
        }
        System.out.println(dp[K]);
    }
}