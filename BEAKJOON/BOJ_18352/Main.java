package BEAKJOON.BOJ_18352;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static final int INF = Integer.MAX_VALUE;

    public static List<List<Integer>> graph = new ArrayList<>();
    public static int[] d;

    public static void dijkstra(int src) {
        Queue<Integer> q = new LinkedList<>();

        q.offer(src);
        d[src] = 0;

        while(!q.isEmpty()) {
            int now = q.poll();

            for(int next : graph.get(now)) {
                if(d[next] > d[now] + 1) {
                    d[next] = d[now] + 1;
                    q.offer(next);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder answer = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        d = new int[n+1];

        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
            d[i] = INF;
        }
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v);
        }

        dijkstra(x);

        for(int i=1; i<=n; i++) {
            if(d[i] == k) {
                answer.append(i).append("\n");
            }
        }

        System.out.println(answer.length() == 0 ? -1 : answer);
    }
}