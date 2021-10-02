package BEAKJOON.BOJ_5567;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static List<List<Integer>> list = new ArrayList<>();
    public static Set<Integer> answer = new HashSet<>();
    public static boolean[] visited;
    public static int n, m;

    public static void classMate(int now, int depth) {
        if(depth >= 2) return;

        for(int next : list.get(now)) {
            if(visited[next]) continue;

            visited[next] = true;
            answer.add(next);
            classMate(next, depth + 1);
            visited[next] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        visited = new boolean[n+1];
        visited[1] = true;

        for(int i=0; i<=n; i++) list.add(new ArrayList<>());
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }

        classMate(1, 0);

        System.out.println(answer.size());
    }
}