package BEAKJOON.BOJ_13549;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static final int INF = Integer.MAX_VALUE;
    public static final int MAX = 100001;

    public static int[] d = new int[MAX];

    public static int[] move(int x, int now, int time) {
        if(x == 0) return new int[]{now + 1, time + 1};
        if(x == 1) return new int[]{now - 1, time + 1};
        return new int[]{now * 2, time};
    }

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
            int time = node[1];

            if(d[now] < time) continue;
            for(int i=0; i<3; i++) {
                int[] nextNode = move(i, now, time);
                int next = nextNode[0];
                int nextTime = nextNode[1];

                if(next < 0 || next > MAX - 1) continue;
                if(d[next] > nextTime) {
                    d[next] = nextTime;
                    pq.offer(new int[]{next, nextTime});
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Arrays.fill(d, INF);

        dijkstra(N);

        System.out.println(d[K]);
    }
}