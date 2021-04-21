package PROGRAMMERS.level_2._12978;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    static List<List<List<Integer>>> graph;
    static List<Integer> city;
    static boolean[] visited;

    public static void dfs(int v, int K, int weight) {
        if(weight > K) return;
        visited[v] = true;
        if(!city.contains(v)) city.add(v);
        for(int w=0; w<graph.get(v).size(); w++) {
            if(!visited[graph.get(v).get(w).get(0)]) dfs(graph.get(v).get(w).get(0), K, weight+graph.get(v).get(w).get(1));
        }
        visited[v] = false;
    }

    public int solution(int N, int[][] roads, int K) {
        graph = new ArrayList<>();
        city = new ArrayList<>();
        visited = new boolean[N+1];

        for(int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            int x = road[0]; // 출발점
            int y = road[1]; // 도착점
            int z = road[2]; // 가중치
            graph.get(x).add(new ArrayList<Integer>(){{add(y); add(z);}});
            graph.get(y).add(new ArrayList<Integer>(){{add(x); add(z);}});
        }
        dfs(1, K, 0);

        return city.size();
    }
}
