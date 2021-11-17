package BEAKJOON.BOJ_1022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Math.*;

public class Main {

    public static int r1, c1, r2, c2;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        r1 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());
    }

    public static int getLength(int value) {
        return value > 0 ? getLength(value / 10) + 1 : 0;
    }

    public static int getValue(int x, int y) {

        int size = max(abs(x), abs(y));
        int d = size * 2;
        int value = d + 1;

        value *= value;

        if(x == size) return value - (size - y);
        value -= d;
        if(y == -size) return value - (size - x);
        value -= d;
        if(x == - size) return value - (size + y);
        value -= d;
        return value - (size + x);
    }

    public static void whirlPool() {

        StringBuilder answer = new StringBuilder();
        int len = 0;

        for(int i=r1; i<=r2; i++) {
            for(int j=c1; j<=c2; j++) {
                len = max(len, getLength(getValue(i, j)));
            }
        }
        for(int i=r1; i<=r2; i++) {
            for(int j=c1; j<=c2; j++) {
                answer.append(String.format("%" + len + "d ", getValue(i, j)));
            }
            answer.append("\n");
        }

        System.out.print(answer);
    }

    public static void main(String[] args) throws IOException {

        input();
        whirlPool();
    }
}