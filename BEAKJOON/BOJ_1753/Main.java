package BEAKJOON.BOJ_1753;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static final int INF = Integer.MAX_VALUE;
    public static List<List<int[]>> graph;
    public static int[] distance;
    public static int V, E, K;

    public static void dijkstra(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        boolean[] check = new boolean[V+1];
        pq.offer(new int[]{start, 0});
        distance[start] = 0;

        while(!pq.isEmpty()) {
            int[] node = pq.poll();
            int current = node[0];

            if(check[current]) continue;
            check[current] = true;

            for(int[] next : graph.get(current)) {
                if(distance[next[0]] > distance[current] + next[1]) {
                    distance[next[0]] = distance[current] + next[1];
                    pq.offer(new int[]{next[0], distance[next[0]]});
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        distance = new int[V+1];
        for(int i=0; i<=V; i++) {
            graph.add(new ArrayList<>());
            distance[i] = INF;
        }

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new int[]{v, w});
        }

        dijkstra(K);

        for(int i=1; i<=V; i++) {
            if(distance[i] == INF) sb.append("INF").append("\n");
            else sb.append(distance[i]).append("\n");
        }
        System.out.println(sb);
    }
}