package BEAKJOON.BOJ_2747;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static long[] memo;

    public static long fibonacci(int n) {
        if(n <= 2) return memo[n] = 1;
        if(memo[n] != 0) return memo[n];
        return memo[n] = fibonacci(n-1) + fibonacci(n-2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        memo = new long[n+1];

        System.out.println(fibonacci(n));
    }
}
