package PROGRAMMERS.level_3._42898;

import java.util.*;

public class Solution {
    static int[] dx = {0, -1};
    static int[] dy = {-1, 0};

    public static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int solution(int m, int n, int[][] puddles) {
        Queue<Node> Q = new LinkedList<>();
        int[][] route = new int[m][n];
        boolean[][] visited = new boolean[m][n];

        route[m-1][n-1] = 1;
        Q.offer(new Node(m-1, n-1));
        for(int[] puddle : puddles) {
            route[puddle[0]-1][puddle[1]-1] = -1;
        }
        while(!Q.isEmpty()) {
            Node node = Q.poll();
            int x = node.x;
            int y = node.y;
            for(int i=0; i<2; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || ny < 0 || route[nx][ny] == -1) continue;
                route[nx][ny] += route[x][y] % 1000000007;
                route[nx][ny] %= 1000000007;
                if(!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    Q.offer(new Node(nx, ny));
                }
            }
        }

        return route[0][0];
    }
}
