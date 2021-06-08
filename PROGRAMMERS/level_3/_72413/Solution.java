package PROGRAMMERS.level_3._72413;

import java.util.*;

public class Solution {
    final static int INF = 1000000;
    static int[][] graph;

    public static void floydWarshall(int n) {
        for(int k=1; k<=n; k++) {
            for(int i=1; i<=n; i++) {
                for(int j=1; j<=n; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
    }

    public int solution(int n, int s, int a, int b, int[][] fares) {
        graph = new int[n+1][n+1];

        for(int i=0; i<=n; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }
        for(int[] fare : fares) {
            int x = fare[0];
            int y = fare[1];
            int z = fare[2];

            graph[x][y] = z;
            graph[y][x] = z;
        }

        floydWarshall(n);
        int cost = graph[s][a] + graph[s][b];
        for(int i=1; i<=n; i++) {
            if(i == s) continue;
            cost = Math.min(cost, graph[s][i] + graph[i][a] + graph[i][b]);
        }

        return cost;
    }
}
