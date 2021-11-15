package BEAKJOON.BOJ_4803;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static int n;
    public static List<Integer> ui, vi;
    public static List<List<List<Integer>>> graph;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        graph = new ArrayList<>();
        ui = new ArrayList<>();
        vi = new ArrayList<>();
        n = 0;

        while(true) {
            st = new StringTokenizer(br.readLine(), " ");

            ui.add(Integer.parseInt(st.nextToken()));
            vi.add(Integer.parseInt(st.nextToken()));

            if(ui.get(n) == 0 && vi.get(n) == 0) break;

            graph.add(new ArrayList<>());
            for(int i=0; i<=ui.get(n); i++) graph.get(n).add(new ArrayList<>());
            for(int i=0; i<vi.get(n); i++) {
                st = new StringTokenizer(br.readLine(), " ");

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                graph.get(n).get(x).add(y);
                graph.get(n).get(y).add(x);
            }

            n++;
        }
    }

    public static boolean isTree(List<List<Integer>> g, boolean[] visited, int now, int prev) {

        boolean ret = true;
        visited[now] = true;

        for(int next : g.get(now)) {
            if(next == prev) continue;
            if(visited[next]) return false;
            if(ret) ret = isTree(g, visited, next, now);
        }

        return ret;
    }

    public static void findTree() {

        StringBuilder answer = new StringBuilder();
        boolean[] visited;
        int u, count;

        for(int i=0; i<n; i++) {
            u = ui.get(i);
            visited = new boolean[u+1];
            count = 0;

            for(int j=1; j<=u; j++) {
                if(!visited[j]) {
                    if(isTree(graph.get(i), visited, j, 0)) count++;
                }
            }

            answer.append("Case ").append(i + 1).append(": ");
            if(count == 0) answer.append("No trees.");
            else if(count == 1) answer.append("There is one tree.");
            else answer.append("A forest of ").append(count).append(" trees.");
            answer.append("\n");
        }

        System.out.print(answer);
    }

    public static void main(String[] args) throws IOException {

        input();
        findTree();
    }
}