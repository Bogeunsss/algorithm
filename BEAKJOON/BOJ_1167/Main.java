package BEAKJOON.BOJ_1167;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int n, farthest, answer;
    public static boolean[] visited;
    public static List<List<int[]>> tree;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        tree = new ArrayList<>();
        answer = farthest = -1;

        for(int i=0; i<=n; i++) tree.add(new ArrayList<>());
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int u = Integer.parseInt(st.nextToken());
            while(true) {
                int v = Integer.parseInt(st.nextToken());
                if(v == -1) break;

                int w = Integer.parseInt(st.nextToken());
                tree.get(u).add(new int[]{v, w});
            }
        }
    }

    public static void getDistance(int now, int distance) {

        if(visited[now]) return;
        visited[now] = true;

        if(answer < distance) {
            answer = distance;
            farthest = now;
        }
        for(int[] node : tree.get(now)) {
            int next = node[0];
            int nextDistance = node[1] + distance;

            getDistance(next, nextDistance);
        }
    }

    public static void getDiameter() {

        visited = new boolean[n+1];
        getDistance(1, 0);

        visited = new boolean[n+1];
        getDistance(farthest, 0);

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {

        input();
        getDiameter();
    }
}