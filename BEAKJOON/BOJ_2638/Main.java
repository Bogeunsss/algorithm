package BEAKJOON.BOJ_2638;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static final int[] dx = {-1, 1, 0, 0};
    public static final int[] dy = {0, 0, -1, 1};

    public static int[][] map;
    public static int n, m;
    public static boolean[][] visited;
    public static Queue<int[]> q;

    public static boolean outOfRange(int nx, int ny) {
        return nx < 0 || nx >= n || ny < 0 || ny >= m;
    }

    public static void outside() {
        q = new LinkedList<>();
        visited = new boolean[n][m];

        q.offer(new int[]{0, 0});
        visited[0][0] = true;
        while(!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.peek()[1];
            q.poll();

            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(outOfRange(nx, ny)) continue;
                if(map[nx][ny] == 1 || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                map[nx][ny] = -1;
                q.offer(new int[]{nx, ny});
            }
        }
    }

    public static void oxidation(int _x, int _y) {
        q = new LinkedList<>();

        q.offer(new int[]{_x, _y});
        visited[_x][_y] = true;
        int cnt = 0;

        for(int i=0; i<4; i++) {
            int nx = _x + dx[i];
            int ny = _y + dy[i];

            if(outOfRange(nx, ny)) continue;
            if(map[nx][ny] == -1) cnt++;
        }
        if(cnt >= 2) map[_x][_y] = 2;

        while(!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.peek()[1];
            q.poll();

            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                cnt = 0;

                if(outOfRange(nx, ny)) continue;
                if(map[nx][ny] == -1 || map[nx][ny] == 0 || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                q.offer(new int[]{nx, ny});

                for(int j=0; j<4; j++) {
                    int nnx = nx + dx[j];
                    int nny = ny + dy[j];

                    if(outOfRange(nnx, nny)) continue;
                    if(map[nnx][nny] == -1) cnt++;
                }
                if(cnt >= 2) map[nx][ny] = 2;
            }
        }
    }

    public static void melt() {

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(map[i][j] == 2) {
                    map[i][j] = -1;
                }
            }
        }
    }

    public static boolean finish() {

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(map[i][j] == 1) return false;
            }
        }
        return true;
    }

    public static int cheese() {
         int time = 0;

         while(true) {
             if(finish()) break;

             outside();
             visited = new boolean[n][m];
             for(int i=0; i<n; i++) {
                 for(int j=0; j<m; j++) {
                     if(map[i][j] == 1) oxidation(i, j);
                 }
             }
             melt();
             time++;
         }

         return time;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            for(int j=0; j<m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(cheese());
    }
}