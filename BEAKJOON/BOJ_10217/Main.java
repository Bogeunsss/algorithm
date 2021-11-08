package BEAKJOON.BOJ_10217;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int n, m, k;
    public static List<List<int[]>> countries;

    public static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        countries = new ArrayList<>();

        for(int i=0; i<=n; i++) countries.add(new ArrayList<>());
        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            countries.get(u).add(new int[]{v, c, d});
        }
    }

    public static String travel() {

        PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        int[][] d = new int[n+1][m+1];
        int min = Integer.MAX_VALUE;

        for(int i=0; i<=n; i++) {
            Arrays.fill(d[i], Integer.MAX_VALUE);
        }

        q.offer(new int[]{1, 0, 0});
        d[1][0] = 0;

        while(!q.isEmpty()) {
            int now = q.peek()[0];
            int cost = q.peek()[1];
            int time = q.peek()[2];
            q.poll();

            if(d[now][cost] < time) continue;
            for(int[] country : countries.get(now)) {
                int next = country[0];
                int nextCost = country[1] + cost;
                int nextTime = country[2] + time;

                if(nextCost > m) continue;
                if(d[next][nextCost] > nextTime) {
                    d[next][nextCost] = nextTime;
                    q.offer(new int[]{next, nextCost, nextTime});
                }
            }
        }
        for(int i=0; i<=m; i++) {
            min = Math.min(min, d[n][i]);
        }

        if(min != Integer.MAX_VALUE) return Integer.toString(min);
        return "Poor KCM";
    }

    public static void KCMTravel() throws IOException {

        StringBuilder answer = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            input();
            answer.append(travel()).append("\n");
        }

        System.out.print(answer);
    }

    public static void main(String[] args) throws IOException {

        KCMTravel();
    }
}