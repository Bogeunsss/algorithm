package BEAKJOON.BOJ_2749;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static final long[][] base = {{1, 1}, {1, 0}};
    public static final int MOD = 1000000;

    public static long n;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Long.parseLong(br.readLine());
    }

    public static long[][] getFibonacci(long m) {

        if(m < 2) return base;

        long[][] ret = getFibonacci(m / 2);

        ret = multiply(ret, ret);
        if(m % 2 == 1) ret = multiply(ret, base);

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

    public static void fibonacci() {

        long[][] answer = getFibonacci(n - 1);

        System.out.println(answer[0][0]);
    }

    public static void main(String[] args) throws IOException {

        input();
        fibonacci();
    }
}