package BEAKJOON.BOJ_11725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static int n;
    public static List<List<Integer>> tree;
    public static int[] parents;
    public static boolean[] visited;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        tree = new ArrayList<>();
        parents = new int[n+1];
        visited = new boolean[n+1];

        for(int i=0; i<=n; i++) {
            tree.add(new ArrayList<>());
        }
        for(int i=0; i<n-1; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            tree.get(n1).add(n2);
            tree.get(n2).add(n1);
        }
    }

    public static void find(int now) {

        visited[now] = true;

        for(int next : tree.get(now)) {
            if(visited[next]) continue;

            parents[next] = now;
            find(next);
        }
    }

    public static void findParent() {

        find(1);

        for(int i=2; i<=n; i++) {
            System.out.println(parents[i]);
        }
    }

    public static void main(String[] args) throws IOException {

        input();
        findParent();
    }
}