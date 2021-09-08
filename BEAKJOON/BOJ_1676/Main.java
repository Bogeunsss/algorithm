package BEAKJOON.BOJ_1676;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int count(int n) {
        int ret = 0;

        while(n > 0) {
            if(n % 5 == 0) ret++;
            n /= 5;
        }

        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] fives = new int[501];

        for(int i=1; i<=N; i++) {
            System.out.println(count(i));
            fives[i] = fives[i-1] + count(i);
        }

        System.out.println(fives[N]);
    }
}