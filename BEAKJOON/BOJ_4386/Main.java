package BEAKJOON.BOJ_4386;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int n;
    public static List<List<double[]>> stars;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        stars = new ArrayList<>();
        double[][] locations = new double[n][2];

        for(int i=0; i<=n; i++) stars.add(new ArrayList<>());
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            locations[i][0] = Double.parseDouble(st.nextToken());
            locations[i][1] = Double.parseDouble(st.nextToken());
        }
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(i == j) continue;

                double x = locations[i][0] - locations[j][0];
                double y = locations[i][1] - locations[j][1];
                double distance = Math.sqrt(x * x + y * y);

                stars.get(i).add(new double[]{j, distance});
                stars.get(j).add(new double[]{i, distance});
            }
        }
    }

    public static double prim(int src) {

        PriorityQueue<double[]> q = new PriorityQueue<>(new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                return (int) (o1[1] - o2[1]);
            }
        });
        boolean[] visited = new boolean[n+1];
        double ret = 0;

        for(double[] star : stars.get(src)) {
            double next = star[0];
            double nextDistance = star[1];

            q.offer(new double[]{next, nextDistance});
        }
        visited[src] = true;

        while(!q.isEmpty()) {
            int now = (int) q.peek()[0];
            double distance = q.peek()[1];
            q.poll();

            if(visited[now]) continue;

            visited[now] = true;
            ret += distance;

            for(double[] star : stars.get(now)) {
                double next = star[0];
                double nextDistance = star[1];

                q.offer(new double[]{next, nextDistance});
            }
        }

        return ret;
    }

    public static void MST() {

        System.out.format("%.2f", prim(1));
    }

    public static void main(String[] args) throws IOException {

        input();
        MST();
    }
}