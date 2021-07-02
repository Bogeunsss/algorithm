package BEAKJOON.BOJ_1717;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int[] parent;
    public static int n;
    public static int m;

    public static int find(int x) {
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x != y) parent[y] = x;
    }

    public static boolean isSameParent(int x, int y) {
        x = find(x);
        y = find(y);

        return x == y;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n+1];

        for(int i=0; i<=n; i++) parent[i] = i;
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int command = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(command == 0) {
                union(a, b);
            }else if(command == 1) {
                String answer = isSameParent(a, b) ? "YES" : "NO";
                sb.append(answer).append("\n");
            }
        }
        System.out.println(sb);
    }
}