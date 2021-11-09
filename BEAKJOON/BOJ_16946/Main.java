package BEAKJOON.BOJ_16946;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static final int[] dx = {-1, 1, 0, 0};
    public static final int[] dy = {0, 0, -1, 1};

    public static int n, m;
    public static int[][] map;
    public static boolean[][] visited, visitedWalls;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];
        visitedWalls = new boolean[n][m];

        for(int i=0; i<n; i++) {
            String row = br.readLine();
            for(int j=0; j<m; j++) {
                map[i][j] = row.charAt(j) - '0';
            }
        }
    }

    public static void crash(int _x, int _y) {

        List<int[]> walls = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();
        int cnt = 1;

        q.offer(new int[]{_x, _y});
        visited[_x][_y] = true;

        while(!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.peek()[1];
            q.poll();

            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(!visited[nx][ny] && map[nx][ny] == 0) {
                    cnt++;
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                }else if(!visitedWalls[nx][ny] && map[nx][ny] > 0) {
                    walls.add(new int[]{nx, ny});
                    visitedWalls[nx][ny] = true;
                }
            }
        }
        for(int[] wall : walls) {
            map[wall[0]][wall[1]] += cnt;
            visitedWalls[wall[0]][wall[1]] = false;
        }
    }

    public static void moveAfterCrashWalls() {

        StringBuilder answer = new StringBuilder();

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(!visited[i][j] && map[i][j] == 0) crash(i, j);
            }
        }
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                answer.append(map[i][j] % 10);
            }
            answer.append("\n");
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {

        input();
        moveAfterCrashWalls();
    }
}