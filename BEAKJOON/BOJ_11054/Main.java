package BEAKJOON.BOJ_11054;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] A = new int[N];
        int[] dp1 = new int[N];
        int[] dp2 = new int[N];
        int answer = 0;

        for(int i=0; i<N; i++) A[i] = Integer.parseInt(st.nextToken());

        dp1[0] = dp2[N-1] = 1;
        for(int i=1; i<N; i++) {
            dp1[i] = 1;
            for(int j=0; j<i; j++) {
                if(A[i] > A[j]) {
                    dp1[i] = Math.max(dp1[i], dp1[j] + 1);
                }
            }
        }
        for(int i=N-2; i>=0; i--) {
            dp2[i] = 1;
            for(int j=N-1; j>i; j--) {
                if(A[i] > A[j]) {
                    dp2[i] = Math.max(dp2[i], dp2[j] + 1);
                }
            }
        }
        for(int i=0; i<N; i++) {
            answer = Math.max(answer, dp1[i] + dp2[i]);
        }
        System.out.println(answer - 1);
    }
}