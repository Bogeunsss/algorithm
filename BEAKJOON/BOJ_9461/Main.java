package BEAKJOON.BOJ_9461;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static long[] memo = new long[101];

    public static long p(int n) {
        if(memo[n] != 0) return memo[n];
        return memo[n] = p(n-1) + p(n-5);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        memo[1] = memo[2] = memo[3] = 1;
        memo[4] = memo[5] = 2;

        for(int i=0; i<T; i++) {
            int N = Integer.parseInt(br.readLine());
            sb.append(p(N)).append("\n");
        }
        System.out.println(sb);
    }
}