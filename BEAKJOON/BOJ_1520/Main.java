package BEAKJOON.BOJ_1520;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static final int[] dx = {-1, 1, 0, 0};
    public static final int[] dy = {0, 0, -1, 1};

    public static int n, m;
    public static int[][] map, dp;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        dp = new int[n][m];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void downHill() {

        PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[2] - o1[2];
            }
        });
        boolean[][] visited = new boolean[n][m];

        q.offer(new int[]{0, 0, map[0][0]});
        visited[0][0] = true;
        dp[0][0] = 1;

        while(!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.peek()[1];
            q.poll();

            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(map[nx][ny] < map[x][y]) {
                    if(!visited[nx][ny]) {
                        visited[nx][ny] = true;
                        q.offer(new int[]{nx, ny, map[nx][ny]});
                    }
                    dp[nx][ny] += dp[x][y];
                }
            }
        }

        System.out.println(dp[n-1][m-1]);
    }

    public static void main(String[] args) throws IOException {

        input();
        downHill();
    }
}