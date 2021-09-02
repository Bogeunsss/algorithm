package BEAKJOON.BOJ_5086;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while(true) {
            st = new StringTokenizer(br.readLine(), " ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(x == 0 && y == 0) break;

            if(y % x == 0) sb.append("factor");
            else if(x % y == 0) sb.append("multiple");
            else sb.append("neither");

            sb.append("\n");
        }

        System.out.println(sb);
    }
}
