package BEAKJOON.BOJ_2887;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int n;
    public static List<List<int[]>> planets;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        planets = new ArrayList<>();

        int[][] p = new int[n][4];

        for(int i=0; i<n; i++) planets.add(new ArrayList<>());
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            p[i][0] = Integer.parseInt(st.nextToken());
            p[i][1] = Integer.parseInt(st.nextToken());
            p[i][2] = Integer.parseInt(st.nextToken());
            p[i][3] = i;
        }

        Arrays.sort(p, Comparator.comparingInt(o -> o[0]));
        for(int i=0; i<n-1; i++) {
            planets.get(p[i][3]).add(new int[]{p[i+1][3], Math.abs(p[i][0] - p[i+1][0])});
            planets.get(p[i+1][3]).add(new int[]{p[i][3], Math.abs(p[i][0] - p[i+1][0])});
        }

        Arrays.sort(p, Comparator.comparingInt(o -> o[1]));
        for(int i=0; i<n-1; i++) {
            planets.get(p[i][3]).add(new int[]{p[i+1][3], Math.abs(p[i][1] - p[i+1][1])});
            planets.get(p[i+1][3]).add(new int[]{p[i][3], Math.abs(p[i][1] - p[i+1][1])});
        }

        Arrays.sort(p, Comparator.comparingInt(o -> o[2]));
        for(int i=0; i<n-1; i++) {
            planets.get(p[i][3]).add(new int[]{p[i+1][3], Math.abs(p[i][2] - p[i+1][2])});
            planets.get(p[i+1][3]).add(new int[]{p[i][3], Math.abs(p[i][2] - p[i+1][2])});
        }
    }

    public static int tunnel(int src) {

        PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        boolean[] visited = new boolean[n];
        int ret = 0;

        for(int[] planet : planets.get(src)) {
            int next = planet[0];
            int nextDistance = planet[1];

            q.offer(new int[]{next, nextDistance});
        }
        visited[src] = true;

        while(!q.isEmpty()) {
            int now = q.peek()[0];
            int distance = q.peek()[1];
            q.poll();

            if(visited[now]) continue;

            visited[now] = true;
            ret += distance;

            for(int[] planet : planets.get(now)) {
                int next = planet[0];
                int nextDistance = planet[1];

                q.offer(new int[]{next, nextDistance});
            }
        }
        return ret;
    }

    public static void planetTunnel() {

        System.out.println(tunnel(0));
    }

    public static void main(String[] args) throws IOException {

        input();
        planetTunnel();
    }
}