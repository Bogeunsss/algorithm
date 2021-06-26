package BEAKJOON.BOJ_1541;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer minus = new StringTokenizer(br.readLine(), "-");
        int answer = Integer.MAX_VALUE;

        while(minus.hasMoreTokens()) {
            int sum = 0;
            StringTokenizer plus = new StringTokenizer(minus.nextToken(), "+");
            while(plus.hasMoreTokens()) {
                sum += Integer.parseInt(plus.nextToken());
            }
            if(answer == Integer.MAX_VALUE) {
                answer = sum;
            }else {
                answer -= sum;
            }
        }
        System.out.println(answer);
    }
}