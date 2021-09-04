package BEAKJOON.BOJ_3036;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int gcd(int x, int y) {
        if(y == 0) return x;
        if(x % y == 0) return y;
        return gcd(y, x % y);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] rings = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for(int i=0; i<N; i++) rings[i] = Integer.parseInt(st.nextToken());
        for(int i=1; i<N; i++) {
            int factor = gcd(rings[0], rings[i]);

            answer.append(rings[0] / factor).append("/");
            answer.append(rings[i] / factor).append("\n");
        }

        System.out.println(answer);
    }
}