package BEAKJOON.BOJ_5719;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static final int INF = Integer.MAX_VALUE;

    public static List<List<int[]>> graph, trace;
    public static boolean[][] visited;
    public static int[] d;

    public static void dijkstra(int src) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        pq.offer(new int[]{src, 0});

        Arrays.fill(d, INF);
        d[src] = 0;

        while(!pq.isEmpty()) {
            int[] node = pq.poll();
            int now = node[0];
            int distance = node[1];

            if(d[now] < distance) continue;
            for(int[] nextNode : graph.get(now)) {
                int next = nextNode[0];
                int nextDistance = nextNode[1] + distance;

                if(nextNode[1] == -1) continue;
                if(d[next] > nextDistance) {
                    d[next] = nextDistance;
                    pq.offer(new int[]{next, nextDistance});

                    trace.get(next).clear();
                    trace.get(next).add(new int[]{now, nextDistance});

                }else if(d[next] == nextDistance) {
                    trace.get(next).add(new int[]{now, nextDistance});
                }
            }
        }
    }

    public static void approximate(int dst) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(dst);

        while(!q.isEmpty()) {
            int now = q.poll();

            for(int[] node : trace.get(now)) {
                int next = node[0];

                if(visited[now][next]) continue;

                visited[now][next] = true;
                for(int[] nextNode : graph.get(next)) {
                    if(nextNode[0] == now) {
                        nextNode[1] = -1;
                    }
                }

                q.offer(next);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        StringTokenizer st;

        while(true) {
            st = new StringTokenizer(br.readLine(), " ");

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if(n == 0 && m == 0) break;

            st = new StringTokenizer(br.readLine(), " ");

            int src = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            trace = new ArrayList<>();
            visited = new boolean[n][n];
            d = new int[n];

            for(int i=0; i<n; i++) {
                graph.add(new ArrayList<>());
                trace.add(new ArrayList<>());
            }
            for(int i=0; i<m; i++) {
                st = new StringTokenizer(br.readLine(), " ");

                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());

                graph.get(u).add(new int[]{v, p});
            }

            dijkstra(src);

            approximate(dst);

            dijkstra(src);

            answer.append(d[dst] == INF ? -1 : d[dst]).append("\n");
        }

        System.out.println(answer);
    }
}