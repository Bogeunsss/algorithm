package BEAKJOON.BOJ_1197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int v, e;
    public static boolean[] visited;
    public static List<List<int[]>> trees;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        trees = new ArrayList<>();
        visited = new boolean[v+1];

        for(int i=0; i<=v; i++) trees.add(new ArrayList<>());
        for(int i=0; i<e; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            trees.get(a).add(new int[]{b, c});
            trees.get(b).add(new int[]{a, c});
        }
    }

    public static int prim(int src) {

        PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        for(int[] tree : trees.get(src)) {
            int next = tree[0];
            int nextWeight = tree[1];

            q.offer(new int[]{next, nextWeight});
        }

        int ret = 0;
        visited[src] = true;

        while(!q.isEmpty()) {
            int now = q.peek()[0];
            int weight = q.peek()[1];
            q.poll();

            if(visited[now]) continue;

            visited[now] = true;
            ret += weight;

            for(int[] tree : trees.get(now)) {
                int next = tree[0];
                int nextWeight = tree[1];

                q.offer(new int[]{next, nextWeight});
            }
        }

        return ret;
    }

    public static void MST() {

        System.out.println(prim(1));
    }

    public static void main(String[] args) throws IOException {

        input();
        MST();
    }
}