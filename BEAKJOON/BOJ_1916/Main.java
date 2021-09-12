package BEAKJOON.BOJ_1916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static final int INF = Integer.MAX_VALUE;

    public static List<List<int[]>> graph = new ArrayList<>();
    public static int[] d;

    public static void dijkstra(int res) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        pq.offer(new int[]{res, 0});
        d[res] = 0;

        while(!pq.isEmpty()) {
            int[] node = pq.poll();
            int now = node[0];
            int distance = node[1];

            if(d[now] < distance) continue;
            for(int[] next : graph.get(now)) {
                if(next[1] + distance < d[next[0]]) {
                    d[next[0]] = next[1] + distance;
                    pq.offer(new int[]{next[0], next[1] + distance});
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        d = new int[N+1];

        Arrays.fill(d, INF);

        for(int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new int[]{v, w});
        }

        st = new StringTokenizer(br.readLine(), " ");

        int res = Integer.parseInt(st.nextToken());
        int dst = Integer.parseInt(st.nextToken());

        dijkstra(res);

        System.out.println(d[dst]);
    }
}