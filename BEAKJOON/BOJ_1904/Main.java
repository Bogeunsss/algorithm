package BEAKJOON.BOJ_1904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] memo = new int[1000001];

    public static int tile(int n) {
        if(n <= 3) return n;
        if(memo[n] != 0) return memo[n];
        return memo[n] = (tile(n-1) + tile(n-2)) % 15746;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println(tile(N) % 15746);
    }
}