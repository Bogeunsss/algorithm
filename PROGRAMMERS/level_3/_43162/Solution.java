package PROGRAMMERS.level_3._43162;

import java.util.*;

public class Solution {
    static List<List<Integer>> graph;
    static boolean[] visited;

    public static void bfs(int x) {
        Queue<Integer> Q = new LinkedList<>();
        Q.offer(x);
        visited[x] = true;

        while(!Q.isEmpty()) {
            int y = Q.poll();
            for(int z : graph.get(y)) {
                if(!visited[z]) {
                    visited[z] = true;
                    Q.offer(z);
                }
            }
        }
    }

    public int solution(int n, int[][] computers) {
        graph = new ArrayList<>();
        visited = new boolean[n];
        int answer = 0;

        for(int i=0; i<n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i=0; i<computers.length-1; i++) {
            for(int j=i+1; j<computers[i].length; j++) {
                if(computers[i][j] == 1) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        for(int i=0; i<graph.size(); i++) {
            if(!visited[i]) {
                answer++;
                bfs(i);
            }
        }
        return answer;
    }
}
