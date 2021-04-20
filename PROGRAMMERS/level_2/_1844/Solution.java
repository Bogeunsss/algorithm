package PROGRAMMERS.level_2._1844;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;

    public static class Node {
        int x;
        int y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void bfs(int[][] maps, int n, int m) {
        Queue<Node> Q = new LinkedList<>();
        Q.offer(new Node(0, 0));
        visited[0][0] = true;
        maps[0][0] = 1;

        while(!Q.isEmpty()) {
            Node node = Q.poll();
            int x = node.x;
            int y = node.y;
            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(visited[nx][ny] || maps[nx][ny] == 0) continue;
                visited[nx][ny] = true;
                maps[nx][ny] = maps[x][y] + 1;
                Q.offer(new Node(nx, ny));
            }
        }
    }

    public int solution(int[][] maps) {
        int n = maps.length, m = maps[0].length;
        visited = new boolean[n][m];

        bfs(maps, n, m);

        if(maps[n-1][m-1] == 1) return -1;
        return maps[n-1][m-1];
    }
}
