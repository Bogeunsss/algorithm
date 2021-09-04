package BEAKJOON.BOJ_11050;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int[] fact;

    public static int factorial(int n) {
        if(n <= 1) return fact[n] = 1;
        if(fact[n] != 0) return fact[n];
        return fact[n] = n * factorial(n - 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int answer = 0;

        fact = new int[N+1];

        factorial(N);

        fact[0] = 1;
        if(0 <= K && K <= N) {
            answer = fact[N] / fact[K] / fact[N-K];
        }

        System.out.println(answer);
    }
}