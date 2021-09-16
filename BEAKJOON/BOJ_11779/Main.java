package BEAKJOON.BOJ_11779;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static final int INF = Integer.MAX_VALUE;

    public static List<List<int[]>> bus;
    public static int[] d, route;

    public static void dijkstra(int src, int dst) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        pq.offer(new int[]{src, 0});
        d[src] = 0;

        while(!pq.isEmpty()) {
            int[] node = pq.poll();
            int now = node[0];
            int cost = node[1];

            if(d[now] < cost) continue;
            for(int[] nextNode : bus.get(now)) {
                int next = nextNode[0];
                int nextCost = nextNode[1] + cost;

                if(d[next] > nextCost) {
                    d[next] = nextCost;
                    route[next] = now;
                    pq.offer(new int[]{next, nextCost});
                }
            }
        }

        List<Integer> answer = new ArrayList<>();
        int v = dst;

        while(v != 0) {
            answer.add(v);
            v = route[v];
        }

        System.out.println(d[dst]);
        System.out.println(answer.size());
        for(int i=answer.size()-1; i>=0; i--) {
            System.out.print(answer.get(i) + " ");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        bus = new ArrayList<>();
        route = new int[n+1];
        d = new int[n+1];

        for(int i=0; i<=n; i++) {
            bus.add(new ArrayList<>());
            d[i] = INF;
        }
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            bus.get(u).add(new int[]{v, w});
        }

        st = new StringTokenizer(br.readLine(), " ");

        int src = Integer.parseInt(st.nextToken());
        int dst = Integer.parseInt(st.nextToken());

        dijkstra(src, dst);
    }
}