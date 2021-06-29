package BEAKJOON.BOJ_11401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    final static long P = 1000000007;

    public static long factorial(long N) {
        long ret = 1L;

        while(N > 1) ret = (ret * N--) % P;
        return ret;
    }

    public static long pow(long base, long exp) {
        if(exp == 1) return base % P;
        long ret = pow(base, exp/2);
        if(exp % 2 == 1) return (ret * ret % P) * base % P;
        return ret * ret % P;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        long N = Long.parseLong(st.nextToken());
        long K = Long.parseLong(st.nextToken());

        long numerator = factorial(N);
        long denominator = factorial(K) * factorial(N - K) % P;

        System.out.println(numerator * pow(denominator, P - 2) % P);
    }
}