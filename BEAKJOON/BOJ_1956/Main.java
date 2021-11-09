package BEAKJOON.BOJ_1956;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static final int INF = 10001;

    public static int V, E;
    public static int[][] villages;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        villages = new int[V+1][V+1];

        for(int i=0; i<=V; i++) {
            Arrays.fill(villages[i], INF);
        }
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            villages[a][b] = c;
        }
    }

    public static void getDistance() {

        for(int k=1; k<=V; k++) {
            for(int i=1; i<=V; i++) {
                if(i == k) continue;
                for(int j=1; j<=V; j++) {
                    villages[i][j] = Math.min(villages[i][j], villages[i][k] + villages[k][j]);
                }
            }
        }
    }

    public static void exercise() {

        int answer = INF;

        getDistance();

        for(int i=1; i<=V; i++) {
            answer = Math.min(answer, villages[i][i]);
        }

        System.out.println(answer == INF ? -1 : answer);
    }

    public static void main(String[] args) throws IOException {

        input();
        exercise();
    }
}