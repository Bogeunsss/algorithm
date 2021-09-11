package BEAKJOON.BOJ_2004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static long count(int n, int exp) {
        long ret = 0;

        for(long i=exp; i<=n; i*=exp) {
            ret += n / i;
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        long count5 = count(n, 5) - count(m, 5) - count(n - m, 5);
        long count2 = count(n, 2) - count(m, 2) - count(n - m, 2);

        System.out.println(Math.min(count5, count2));
    }
}
