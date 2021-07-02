package BEAKJOON.BOJ_20040;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int[] parent;

    public static boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) return true;
        parent[y] = x;
        return false;
    }

    public static int find(int x) {
        if(x == parent[x]) return parent[x];
        return parent[x] = find(parent[x]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int answer = 0;

        parent = new int[n];
        for(int i=0; i<n; i++) parent[i] = i;
        for(int i=1; i<=m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if(union(x, y)) {
                answer = i;
                break;
            }
        }
        System.out.println(answer);
    }
}