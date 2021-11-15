package BEAKJOON.BOJ_1774;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int n, m;
    public static boolean[] visited;
    public static double[][] routes;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean[n+1];
        routes = new double[n+1][n+1];

        long[][] locations = new long[n+1][2];

        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            locations[i][0] = Long.parseLong(st.nextToken());
            locations[i][1] = Long.parseLong(st.nextToken());
        }
        for(int i=1; i<=n; i++) {
            for(int j=i+1; j<=n; j++) {
                long x = locations[i][0] - locations[j][0];
                long y = locations[i][1] - locations[j][1];
                double d = Math.sqrt(x * x + y * y);

                routes[i][j] = routes[j][i] = d;
            }
        }
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            routes[x][y] = routes[y][x] = 0;
        }
    }

    public static double conversation(int src) {

        PriorityQueue<double[]> q = new PriorityQueue<>(new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                return Double.compare(o1[1], o2[1]);
            }
        });
        double ret = 0;

        for(int i=1; i<routes[src].length; i++) {
            if(i == src) continue;
            q.offer(new double[]{i, routes[src][i]});
        }
        visited[src] = true;

        while(!q.isEmpty()) {
            int now = (int) q.peek()[0];
            double distance = q.peek()[1];
            q.poll();

            if(visited[now]) continue;

            visited[now] = true;
            ret += distance;

            for(int i=1; i<routes[now].length; i++) {
                if(i == now) continue;
                q.offer(new double[]{i, routes[now][i]});
            }
        }

        return ret;
    }

    public static void conversationWithTheUniverseGod() {

        double answer = conversation(1);

        System.out.format("%.2f", answer);
    }

    public static void main(String[] args) throws IOException {

        input();
        conversationWithTheUniverseGod();
    }
}