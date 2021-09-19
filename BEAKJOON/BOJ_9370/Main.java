package BEAKJOON.BOJ_9370;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static final int INF = Integer.MAX_VALUE;

    public static List<List<int[]>> graph;
    public static int[] S, G, H, candidates;

    public static void dijkstra(int src, int[] d) {
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

                if(d[next] > nextDistance) {
                    d[next] = nextDistance;
                    pq.offer(new int[]{next, nextDistance});
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        List<Integer> answer;

        while(T-- > 0) {
            answer = new ArrayList<>();

            st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            S = new int[n+1];
            G = new int[n+1];
            H = new int[n+1];
            candidates = new int[t];

            for(int i=0; i<=n; i++) {
                graph.add(new ArrayList<>());
            }
            for(int i=0; i<m; i++) {
                st = new StringTokenizer(br.readLine(), " ");

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                graph.get(a).add(new int[]{b, d});
                graph.get(b).add(new int[]{a, d});
            }

            dijkstra(s, S);
            dijkstra(g, G);
            dijkstra(h, H);

            for(int i=0; i<t; i++) {
                candidates[i] = Integer.parseInt(br.readLine());

                if(S[candidates[i]] == (S[g] + G[h] + H[candidates[i]])) answer.add(candidates[i]);
                else if(S[candidates[i]] == (S[h] + G[h] + G[candidates[i]])) answer.add(candidates[i]);
            }

            Collections.sort(answer);

            for(int i=0; i<answer.size(); i++) {
                System.out.print(answer.get(i) + " ");
            }
            System.out.println();
        }
    }
}