package PROGRAMMERS.level_3._76503;

import java.util.List;
import java.util.ArrayList;

public class Solution {
    static List<List<Integer>> graph;
    static boolean[] visited;
    static long[] weight;
    static long answer;

    public static long dfs(int v) {
        visited[v] = true;
        for(int i=0; i<graph.get(v).size(); i++) {
            int next = graph.get(v).get(i);
            if(!visited[next]) weight[v] += dfs(next);
        }
        answer += Math.abs(weight[v]);
        return weight[v];
    }

    public long solution(int[] a, int[][] edges) {
        graph = new ArrayList<>();
        visited = new boolean[a.length];
        weight = new long[a.length];
        answer = 0;

        int sum = 0;
        for(int i=0; i<a.length; i++) {
            sum += a[i];
            weight[i] = a[i];
            graph.add(new ArrayList<>());
        }

        if(sum != 0) return -1;
        for(int i=0; i<edges.length; i++) {
            int x = edges[i][0];
            int y = edges[i][1];
            graph.get(x).add(y);
            graph.get(y).add(x);
        }
        dfs(0);

        return answer;
    }
}
