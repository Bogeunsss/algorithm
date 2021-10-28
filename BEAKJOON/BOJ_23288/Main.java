package BEAKJOON.BOJ_23288;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static final int[] dx = {0, 0, 0, 1, -1};
    public static final int[] dy = {0, 1, -1, 0, 0};

    public static int n, m, k;
    public static int[][] map;
    public static int[] dice = {0, 1, 2, 3, 4, 5, 6};

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n+1][m+1];

        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1; j<=m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void roll(int d) {

        int[] backup = dice.clone();

        if(d == 1) {
            dice[1] = backup[4];
            dice[3] = backup[1];
            dice[4] = backup[6];
            dice[6] = backup[3];
        }else if(d == 2) {
            dice[1] = backup[3];
            dice[3] = backup[6];
            dice[4] = backup[1];
            dice[6] = backup[4];
        }else if(d == 3) {
            dice[1] = backup[2];
            dice[2] = backup[6];
            dice[5] = backup[1];
            dice[6] = backup[5];
        }else {
            dice[1] = backup[5];
            dice[2] = backup[1];
            dice[5] = backup[6];
            dice[6] = backup[2];
        }
    }

    public static int turn(int d) {

        if(d == 1) return 2;
        if(d == 2) return 1;
        if(d == 3) return 4;
        return 3;
    }

    public static int rotate(int d, boolean clockwise) {

        if(clockwise) {
            if(d == 1) return 3;
            if(d == 2) return 4;
            if(d == 3) return 2;
            return 1;
        }else {
            if(d == 1) return 4;
            if(d == 2) return 3;
            if(d == 3) return 1;
            return 2;
        }
    }

    public static int getScore(int _x, int _y, int unit) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n+1][m+1];
        int ret = unit;

        q.offer(new int[]{_x, _y});
        visited[_x][_y] = true;

        while(!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.peek()[1];
            q.poll();

            for(int i=1; i<=4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx <= 0 || nx > n || ny <= 0 || ny > m) continue;
                if(visited[nx][ny] || map[nx][ny] != unit) continue;

                ret += unit;
                visited[nx][ny] = true;
                q.offer(new int[]{nx , ny});
            }
        }

        return ret;
    }

    public static void play() {

        int x = 1, y = 1, d = 1;
        int answer = 0;

        for(int i=0; i<k; i++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx <= 0 || nx > n || ny <= 0 || ny > m) {
                d = turn(d);
                nx = x + dx[d];
                ny = y + dy[d];
            }

            roll(d);
            answer += getScore(nx, ny, map[nx][ny]);

            if(dice[6] > map[nx][ny]) d = rotate(d, true);
            else if(dice[6] < map[nx][ny]) d = rotate(d, false);

            x = nx;
            y = ny;
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {

        input();
        play();
    }
}