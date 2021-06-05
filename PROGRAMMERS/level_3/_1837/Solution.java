package PROGRAMMERS.level_3._1837;

import java.util.*;

public class Solution {
    final static float infinity = Float.POSITIVE_INFINITY;

    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        List<List<Integer>> graph = new ArrayList<>();
        float[][] dp = new float[k][n+1];
        int start = gps_log[0];
        int end = gps_log[k-1];
        int answer = 0;

        for(int i=0; i<=n; i++) graph.add(new ArrayList<>());
        for(int i=0; i<edge_list.length; i++) {
            int x = edge_list[i][0];
            int y = edge_list[i][1];

            graph.get(x).add(y);
            graph.get(y).add(x);
        }
        for(int i=0; i<k; i++) Arrays.fill(dp[i], infinity);
        dp[0][start] = 0;

        for(int i=0; i<k-1; i++) {
            for(int j=1; j<n+1; j++) {
                if(dp[i][j] == infinity) continue;

                List<Integer> nextNode = new ArrayList<>(graph.get(j));
                nextNode.add(j);

                for(int next : nextNode) {
                    float value = dp[i][j];

                    if(next != gps_log[i+1]) value++;
                    dp[i+1][next] = Math.min(dp[i+1][next], value);
                }
            }
        }

        return dp[k-1][end] != infinity ? (int) dp[k-1][end] : -1;
    }
}
