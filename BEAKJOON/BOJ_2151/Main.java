package BEAKJOON.BOJ_2151;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static final int[] dx = {-1, 1, 0, 0};
    public static final int[] dy = {0, 0, -1, 1};

    public static int n;
    public static char[][] map;
    public static List<int[]> mirrors, doors;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new char[n][n];

        mirrors = new ArrayList<>();
        doors = new ArrayList<>();

        for(int i=0; i<n; i++) {
            String row = br.readLine();
            for(int j=0; j<n; j++) {
                map[i][j] = row.charAt(j);

                if(map[i][j] == '!') mirrors.add(new int[]{i, j});
                else if(map[i][j] == '#') {
                    map[i][j] = '.';
                    doors.add(new int[]{i, j});
                }
            }
        }
    }

    public static boolean outOfRange(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= n;
    }

    public static int[] turn(int d) {

        if(d == 0 || d == 1) return new int[]{d, 2, 3};
        return new int[]{d, 0, 1};
    }

    public static int install() {

        Queue<int[]> q = new LinkedList<>();
        int[][][] mirror = new int[n][n][4];
        int[] src = doors.get(0);
        int[] dst = doors.get(1);
        int ret = Integer.MAX_VALUE;

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                Arrays.fill(mirror[i][j], Integer.MAX_VALUE);
            }
        }
        for(int i=0; i<4; i++) {
            int nx = src[0] + dx[i];
            int ny = src[1] + dy[i];

            if(outOfRange(nx, ny)) continue;
            if(map[nx][ny] == '*') continue;

            q.offer(new int[]{src[0], src[1], i});
            mirror[src[0]][src[1]][i] = 0;
        }

        while(!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.peek()[1];
            int d = q.peek()[2];
            q.poll();

            int nx = x + dx[d];
            int ny = y + dy[d];

            if(outOfRange(nx, ny) || map[nx][ny] == '*') continue;
            if(map[nx][ny] == '!') {
                for(int nd : turn(d)) {
                    if(nd == d) {
                        if(mirror[nx][ny][d] > mirror[x][y][d]) {
                            mirror[nx][ny][d] = mirror[x][y][d];
                            q.offer(new int[]{nx, ny, d});
                        }
                    }else {
                        if(mirror[nx][ny][nd] > mirror[x][y][d] + 1) {
                            mirror[nx][ny][nd] = mirror[x][y][d] + 1;
                            q.offer(new int[]{nx, ny, nd});
                        }
                    }
                }
            }else if(map[nx][ny] == '.') {
                if(mirror[nx][ny][d] > mirror[x][y][d]) {
                    mirror[nx][ny][d] = mirror[x][y][d];
                    q.offer(new int[]{nx, ny, d});
                }
            }
        }
        for(int i=0; i<4; i++) {
            ret = Math.min(ret, mirror[dst[0]][dst[1]][i]);
        }

        return ret;
    }

    public static void installMirror() {

        System.out.println(install());
    }

    public static void main(String[] args) throws IOException {

        input();
        installMirror();
    }
}