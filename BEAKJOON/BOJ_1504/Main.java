package BEAKJOON.BOJ_1504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static final int INF = 200000000;
    public static List<List<int[]>> graph;
    public static boolean[] check;
    public static int[] distance;
    public static int N, E;

    public static int dijkstra(int start, int end) {
        Arrays.fill(distance, INF);
        Arrays.fill(check, false);

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        boolean[] check = new boolean[N+1];
        pq.offer(new int[]{start, 0});
        distance[start] = 0;

        while(!pq.isEmpty()) {
            int[] node = pq.poll();
            int current = node[0];

            if(check[current]) continue;
            check[current] = true;

            for(int[] next : graph.get(current)) {
                if(!check[next[0]] && distance[next[0]] > node[1] + next[1]) {
                    distance[next[0]] = node[1] + next[1];
                    pq.offer(new int[]{next[0], distance[next[0]]});
                }
            }
        }
        return distance[end];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        distance = new int[N+1];
        check = new boolean[N+1];
        for(int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new int[]{b, c});
            graph.get(b).add(new int[]{a, c});
        }
        st = new StringTokenizer(br.readLine(), " ");
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int res1 = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, N);
        int res2 = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, N);

        if(res1 >= INF && res2 >= INF) System.out.println(-1);
        else System.out.println(Math.min(res1, res2));
    }
}