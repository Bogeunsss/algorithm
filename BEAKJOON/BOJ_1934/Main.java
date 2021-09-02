package BEAKJOON.BOJ_1934;

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

    public static int lcm(int x, int y) {
        return x * y / gcd(x, y);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            sb.append(lcm(x, y)).append("\n");
        }

        System.out.println(sb);
    }
}
