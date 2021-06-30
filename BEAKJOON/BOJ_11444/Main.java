package BEAKJOON.BOJ_11444;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final int MOD = 1000000007;
    static long[][] A = {{1, 1}, {1, 0}};

    public static long[][] fibonacci(long n) {
        if(n < 2) return A;
        long[][] ret = fibonacci(n/2);
        ret = multiply(ret, ret);
        if(n % 2 == 1) ret = multiply(ret, A);

        return ret;
    }

    public static long[][] multiply(long[][] A, long[][] B) {
        long[][] ret = new long[2][2];

        for(int i=0; i<2; i++) {
            for(int j=0; j<2; j++) {
                for(int k=0; k<2; k++) {
                    ret[i][j] += A[i][k] * B[k][j];
                    ret[i][j] %= MOD;
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());

        System.out.println(fibonacci(n-1)[0][0]);
    }
}