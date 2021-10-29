package BEAKJOON.BOJ_23290;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static final int[] fishX = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    public static final int[] fishY = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    public static final int[] sharkX = {0, -1, 0, 1, 0};
    public static final int[] sharkY = {0, 0, -1, 0, 1};

    public static int n, m, s;
    public static int[][][] map;
    public static int[][] smell;
    public static int[] shark;
    public static int max;
    public static String confirmed;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = 4;
        m = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        map = new int[n+1][n+1][9];
        smell = new int[n+1][n+1];
        shark = new int[2];

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int fx = Integer.parseInt(st.nextToken());
            int fy = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            map[fx][fy][d] += 1;
        }
        for(int i=1; i<=n; i++) {
            Arrays.fill(smell[i], Integer.MIN_VALUE);
        }

        st = new StringTokenizer(br.readLine(), " ");
        shark[0] = Integer.parseInt(st.nextToken());
        shark[1] = Integer.parseInt(st.nextToken());
    }

    public static boolean outOfRange(int x, int y) {
        return x <= 0 || x > n || y <= 0 || y > n;
    }

    public static int rotate(int d) {
        if(d == 1) return 8;
        return d - 1;
    }

    public static int[][][] castStart() {

        int[][][] ret = new int[n+1][n+1][9];

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                for(int k=1; k<=8; k++) {
                    ret[i][j][k] = map[i][j][k];
                }
            }
        }
        return ret;
    }

    public static void fishMove(int time) {

        int[][][] next = new int[n+1][n+1][9];

        for(int x=1; x<=n; x++) {
            for(int y=1; y<=n; y++) {
                for(int d=1; d<=8; d++) {
                    if(map[x][y][d] > 0) {
                        int nd = d;
                        int nx = x + fishX[nd];
                        int ny = y + fishY[nd];
                        int cnt = 0;
                        boolean cannotMove = false;

                        while(outOfRange(nx, ny) || (shark[0] == nx && shark[1] == ny) || smell[nx][ny] >= time - 2) {
                            nd = rotate(nd);
                            nx = x + fishX[nd];
                            ny = y + fishY[nd];
                            cnt++;

                            if(cnt >= 8) {
                                cannotMove = true;
                                break;
                            }
                        }

                        if(cannotMove) {
                            next[x][y][d] += map[x][y][d];
                        }else {
                            next[nx][ny][nd] += map[x][y][d];
                        }
                    }
                }
            }
        }

        map = next;
    }

    public static void explore(boolean[][] visited, int x, int y, String move, int eat) {

        if(move.length() >= 3) {

            if(max < eat) {
                max = eat;

                confirmed = move;
            }
            return;
        }

        for(int i=1; i<=4; i++) {
            int nx = x + sharkX[i];
            int ny = y + sharkY[i];
            int sum = 0;

            if(outOfRange(nx, ny)) continue;
            if(visited[nx][ny]) {
                explore(visited, nx, ny, move + i, eat);
            }else {
                for(int j=1; j<=8; j++) {
                    sum += map[nx][ny][j];
                }

                visited[nx][ny] = true;
                explore(visited, nx, ny, move + i, eat + sum);
                visited[nx][ny] = false;
            }
        }
    }

    public static void sharkMove(int time) {

        int x = shark[0];
        int y = shark[1];
        max = -1;

        explore(new boolean[n+1][n+1], x, y, "", 0);

        for(int i=0; i<confirmed.length(); i++) {
            int d = confirmed.charAt(i) - '0';
            int nx = x + sharkX[d];
            int ny = y + sharkY[d];

            for(int j=1; j<=8; j++) {
                if(map[nx][ny][j] > 0) {
                    map[nx][ny][j] = 0;
                    smell[nx][ny] = time;
                }
            }

            x = nx;
            y = ny;
        }

        shark[0] = x;
        shark[1] = y;
    }

    public static void castComplete(int[][][] casting) {

        for(int x=1; x<=n; x++) {
            for(int y=1; y<=n; y++) {
                for(int d=1; d<=8; d++) {
                    map[x][y][d] += casting[x][y][d];
                }
            }
        }
    }

    public static int count() {

        int ret = 0;

        for(int x=1; x<=n; x++) {
            for(int y=1; y<=n; y++) {
                for(int d=1; d<=8; d++) {
                    ret += map[x][y][d];
                }
            }
        }

        return ret;
    }

    public static void replication() {

        for(int i=0; i<s; i++) {
            int[][][] casting = castStart();
            fishMove(i);
            sharkMove(i);
            castComplete(casting);
        }

        System.out.println(count());
    }

    public static void main(String[] args) throws IOException{

        input();
        replication();
    }
}
