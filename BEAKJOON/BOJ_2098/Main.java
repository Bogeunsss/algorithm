package BEAKJOON.BOJ_2098;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static final int INF = 1000001;

    public static int n;
    public static int[][] map, memo;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        memo = new int[n][(1 << n)-1];

        for(int i=0; i<n; i++) {
            Arrays.fill(memo[i], INF);

            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static int TSP(int visited, int now) {

        if(visited == (1 << n) - 1) {
            if(map[now][0] == 0) return INF;
            return map[now][0];
        }

        if(memo[now][visited] != INF) return memo[now][visited];

        for(int i=0; i<n; i++) {
            int next = visited | (1 << i);

            if((visited & (1 << i)) == 0 && map[now][i] != 0) {
                memo[now][visited] = Math.min(memo[now][visited], TSP(next, i) + map[now][i]);
            }
        }

        return memo[now][visited];
    }

    public static void solution() {

        int answer = TSP(1, 0);

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {

        input();
        solution();
    }
}