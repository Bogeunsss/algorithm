package BEAKJOON.BOJ_1238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static final int INF = Integer.MAX_VALUE;

    public static List<List<int[]>> graph = new ArrayList<>();
    public static int[] d;

    public static int dijkstra(int res, int dst) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        pq.offer(new int[]{res, 0});

        Arrays.fill(d, INF);
        d[res] = 0;

        while(!pq.isEmpty()) {
            int[] node = pq.poll();
            int now = node[0];
            int distance = node[1];

            if(d[now] < distance) continue;
            for(int[] next : graph.get(now)) {
                int nextNode = next[0];
                int nextDistance = next[1] + distance;

                if(d[nextNode] > nextDistance) {
                    d[nextNode] = nextDistance;
                    pq.offer(new int[]{nextNode, nextDistance});
                }
            }
        }

        return d[dst];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int answer = -1;

        d = new int[N+1];

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

        for(int i=1; i<=N; i++) {
            int time = dijkstra(i, X) + dijkstra(X, i);

            answer = Math.max(answer, time);
        }

        System.out.println(answer);
    }
}