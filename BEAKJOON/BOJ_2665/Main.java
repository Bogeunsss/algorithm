package BEAKJOON.BOJ_2665;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static final int[] dx = {-1, 1, 0, 0};
    public static final int[] dy = {0, 0, -1, 1};
    public static final int INF = Integer.MAX_VALUE;

    public static int[][] maze, d;
    public static int N;

    public static void dijkstra(int _x, int _y) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        pq.offer(new int[]{_x, _y, 0});
        d[_x][_y] = 0;

        while(!pq.isEmpty()) {
            int[] node = pq.poll();
            int x = node[0];
            int y = node[1];
            int z = node[2];

            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx <= 0 || nx > N || ny <= 0 || ny > N) continue;
                if(d[x][y] < z) continue;

                int nz = (maze[nx][ny] == 0 ? 1 : 0) + z;

                if(d[nx][ny] > nz) {
                    d[nx][ny] = nz;
                    pq.offer(new int[]{nx, ny, nz});
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        maze = new int[N+1][N+1];
        d = new int[N+1][N+1];

        for(int i=1; i<=N; i++) {
            String data = br.readLine();

            for(int j=1; j<=N; j++) {
                maze[i][j] = data.charAt(j-1) - '0';
                d[i][j] = INF;
            }
        }

        dijkstra(1, 1);

        System.out.println(d[N][N]);
    }
}