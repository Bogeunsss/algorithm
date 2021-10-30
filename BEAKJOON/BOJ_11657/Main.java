package BEAKJOON.BOJ_11657;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static final int INF = Integer.MAX_VALUE;

    public static int n, m;
    public static List<int[]> buses;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        buses = new ArrayList<>();

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            buses.add(new int[]{a, b, c});
        }
    }

    public static boolean bellmanford(long[] d, int src) {

        Arrays.fill(d, INF);
        d[src] = 0;

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                int now = buses.get(j)[0];
                int next = buses.get(j)[1];
                int time = buses.get(j)[2];

                if(d[now] == INF) continue;
                if(d[next] > d[now] + time) {
                    d[next] = d[now] + time;
                    if(i == n - 1) return true;
                }
            }
        }
        return false;
    }

    public static void timeMachine() {

        long[] d = new long[n+1];

        if(bellmanford(d, 1)) System.out.println(-1);
        else {
            for(int i=2; i<=n; i++) {
                System.out.println(d[i] == INF ? -1 : d[i]);
            }
        }
    }

    public static void main(String[] args) throws IOException {

        input();
        timeMachine();
    }
}