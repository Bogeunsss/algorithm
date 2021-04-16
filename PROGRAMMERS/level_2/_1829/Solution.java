package PROGRAMMERS.level_2._1829;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static class Node {
        int a;
        int b;

        public Node(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
    public static int bfs(int[][] picture, boolean[][] visited, int m, int n, int a, int b, int color) {
        Queue<Node> Q = new LinkedList<Node>();
        Q.offer(new Node(a, b));
        visited[a][b] = true;
        int cnt = 1;

        while(!Q.isEmpty()) {
            Node node = Q.poll();
            int x = node.a;
            int y = node.b;
            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                if(visited[nx][ny] || picture[nx][ny] != color) continue;
                visited[nx][ny] = true;
                Q.offer(new Node(nx, ny));
                cnt++;
            }
        }
        return cnt;
    }

    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        boolean[][] visited = new boolean[m][n];

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(!visited[i][j] && picture[i][j] != 0) {
                    numberOfArea++;
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, bfs(picture, visited, m, n, i, j, picture[i][j]));
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
}