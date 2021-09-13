package BEAKJOON.BOJ_1261;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static final int[] dx = {-1, 1, 0 ,0};
    public static final int[] dy = {0, 0, -1, 1};
    public static final int INF = Integer.MAX_VALUE;

    public static int[][] maze;
    public static int[][] d;
    public static int n, m;

    public static void algospot(int _x, int _y) {
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{_x, _y});
        d[_x][_y] = 0;

        while(!q.isEmpty()) {
            int[] node = q.poll();
            int x = node[0];
            int y = node[1];

            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(d[nx][ny] < d[x][y]) continue;
                if(d[nx][ny] > d[x][y] + maze[nx][ny]) {
                    d[nx][ny] = d[x][y] + maze[nx][ny];
                    q.offer(new int[]{nx, ny});
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        maze = new int[n][m];
        d = new int[n][m];

        for(int i=0; i<n; i++) {
            String data = br.readLine();

            for(int j=0; j<m; j++) {
                maze[i][j] = data.charAt(j) - '0';
                d[i][j] = INF;
            }
        }

        algospot(0, 0);

        System.out.println(d[n-1][m-1]);
    }
}