package PROGRAMMERS.level_3._49189;

import java.util.*;

public class Solution {
    static List<List<Integer>> list;
    static List<Integer> answer;
    static int[] dist;

    public static int bfs(int v) {
        Queue<Integer> Q = new LinkedList<>();
        Q.offer(v);
        dist[v] = 1;

        int max = 0;
        while(!Q.isEmpty()) {
            int x = Q.poll();
            for(int y : list.get(x)) {
                if(dist[y] == 0) {
                    dist[y] = dist[x] + 1;
                    Q.offer(y);
                    max = Math.max(max, dist[y]);
                }
            }
        }
        return max;
    }

    public int solution(int n, int[][] edge) {
        list = new ArrayList<>();
        answer = new ArrayList<>();
        dist = new int[n+1];
        int answer = 0;

        for(int i=0; i<=n; i++) list.add(new ArrayList<>());
        for(int i=0; i<edge.length; i++) {
            int x = edge[i][0];
            int y = edge[i][1];

            list.get(x).add(y);
            list.get(y).add(x);
        }
        for(int i=0; i<list.size(); i++) {
            Collections.sort(list.get(i));
        }
        int max = bfs(1);
        for(int i : dist) {
            if(i == max) answer++;
        }
        return answer;
    }
}
