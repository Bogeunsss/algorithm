package BEAKJOON.BOJ_10282;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static final int INF = Integer.MAX_VALUE;

    public static List<List<int[]>> computers;
    public static int[] w;
    public static int n, d, c;

    public static void infection(int src, int t) {
        PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        q.offer(new int[]{src, t});
        w[src] = t;

        while(!q.isEmpty()) {
            int now = q.peek()[0];
            int time = q.peek()[1];
            q.poll();

            if(w[now] > time) continue;
            for(int[] computer : computers.get(now)) {
                int next = computer[0];
                int sum = computer[1] + time;

                if(w[next] > sum) {
                    w[next] = sum;
                    q.offer(new int[]{next, sum});
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");

            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            computers = new ArrayList<>();
            w = new int[n+1];

            int infected = 0, time = 0;

            Arrays.fill(w, INF);

            for(int i=0; i<=n; i++) computers.add(new ArrayList<>());
            for(int i=0; i<d; i++) {
                st = new StringTokenizer(br.readLine(), " ");

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                computers.get(b).add(new int[]{a, s});
            }

            infection(c, 0);

            for(int i=1; i<=n; i++) {
                if(w[i] != INF) {
                    infected++;
                    time = Math.max(time, w[i]);
                }
            }

            System.out.println(infected + " " + time);
        }
    }
}
