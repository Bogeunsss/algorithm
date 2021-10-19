package BEAKJOON.BOJ_17825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static final int MAX = 10;

    public static int[] dice = new int[MAX];
    public static int[] pieces = new int[4];
    public static int[] map = new int[33];
    public static int[] turn = new int[33];
    public static int[] score = new int[33];
    public static boolean[] check = new boolean[33];
    public static int answer = 0;

    public static void initialize() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for(int i=0; i<MAX; i++) dice[i] = Integer.parseInt(st.nextToken());

        for(int i=0; i<21; i++) map[i] = i + 1;
        map[21] = 21;
        for(int i=22; i<27; i++) map[i] = i + 1;
        map[28] = 29; map[29] = 30; map[30] = 25;
        map[31] = 32; map[32] = 25; map[27] = 20;

        turn[5] = 22; turn[10] = 31; turn[15] = 28; turn[25] = 26;

        for(int i=0; i<21; i++) score[i] = i * 2;
        score[22] = 13; score[23] = 16; score[24] = 19;
        score[31] = 22; score[32] = 24; score[28] = 28;
        score[29] = 27; score[30] = 26; score[25] = 25;
        score[26] = 30; score[27] = 35;
    }

    public static void play(int depth, int sum) {
        if(depth >= 10) {
            answer = Math.max(answer, sum);
            return;
        }
        for(int i=0; i<4; i++) {
            int prev = pieces[i];
            int now = prev;
            int move = dice[depth];

            if(turn[now] > 0) {
                now = turn[now];
                move--;
            }
            while(move-- > 0) {
                now = map[now];
            }

            if(now != 21 && check[now]) continue;

            check[prev] = false;
            check[now] = true;
            pieces[i] = now;

            play(depth + 1, sum + score[now]);

            pieces[i] = prev;
            check[now] = false;
            check[prev] = true;
        }
    }

    public static void main(String[] args) throws IOException {

        initialize();
        play(0, 0);

        System.out.println(answer);
    }
}