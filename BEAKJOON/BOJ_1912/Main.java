package BEAKJOON.BOJ_1912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] dp = new int[n];
        int answer = -Integer.MAX_VALUE;

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());

        answer = dp[0] = arr[0];
        for(int i=1; i<n; i++) {
            dp[i] = arr[i];
            dp[i] = Math.max(dp[i], dp[i] + dp[i-1]);
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }
}