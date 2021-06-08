package PROGRAMMERS.level_3._67259;

import java.util.*;

public class Solution {
    final static int[] dx = {0, 1, 0, -1};
    final static int[] dy = {1, 0, -1, 0};
    final static int INF = Integer.MAX_VALUE;

    public static class Node {
        int x;
        int y;
        int cost;
        int prev;

        public Node(int x, int y, int cost, int prev) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.prev = prev;
        }
    }

    public int solution(int[][] board) {
        int answer = INF;
        int N = board.length;
        int[][][] dist = new int[N][N][4];

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                Arrays.fill(dist[i][j], INF);
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Integer.compare(o1.cost, o2.cost);
            }
        });
        for(int i=0; i<4; i++) {
            dist[0][0][i] = 0;
            pq.offer(new Node(0,0,0, i));
        }

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int x = node.x;
            int y = node.y;
            int cost = node.cost;
            int prev = node.prev;

            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(Math.abs(prev-i) == 2) continue;
                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if(board[nx][ny] == 1) continue;

                int nc = cost + (prev == i ? 100 : 600);
                if(dist[nx][ny][i] > nc) {
                    dist[nx][ny][i] = nc;
                    pq.offer(new Node(nx, ny, nc, i));
                }
            }
        }

        for(int i=0; i<4; i++) answer = Math.min(answer, dist[N-1][N-1][i]);
        return answer;
    }
}
