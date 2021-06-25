package BEAKJOON.BOJ_11053;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int[] A = new int[N];
        int[] dp = new int[N];
        int answer = 1;

        for(int i=0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = 1;
        for(int i=1; i<N; i++) {
            dp[i] = 1;
            for(int j=0; j<i; j++) {
                if(A[i] > A[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }
}