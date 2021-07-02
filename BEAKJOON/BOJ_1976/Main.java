package BEAKJOON.BOJ_1976;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int[] parent;

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x != y) {
            if(x > y) parent[x] = y;
            else parent[y] = x;
        }
    }

    public static int find(int x) {
        if(x == parent[x]) return parent[x];
        return parent[x] = find(parent[x]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[][] map = new int[N+1][N+1];
        int[] route = new int[M+1];
        parent = new int[N+1];

        for(int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j=1; j<=N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i<=M; i++) {
            route[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=1; i<=N; i++) {
            parent[i] = i;
        }
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(map[i][j] == 1) {
                    if(i > j) {
                        union(i, j);
                    }else {
                        union(j, i);
                    }
                }
            }
        }

        int index = find(route[1]);
        boolean impossible = false;
        for(int i=2; i<=M; i++) {
            if(index != find(route[i])) {
                impossible = true;
                break;
            }
        }
        System.out.println(impossible ? "NO" : "YES");
    }
}