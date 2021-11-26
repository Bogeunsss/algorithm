package BEAKJOON.BOJ_14503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static final int[] dx = {-1, 0, 1, 0};
    public static final int[] dy = {0, 1, 0, -1};

    public static int n, m, r, c, d;
    public static int[][] map;
    public static boolean[][] visited;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];

        st = new StringTokenizer(br.readLine(), " ");

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static int turn(int d) {
        if(d == 0) return 3;
        return d - 1;
    }

    public static int getOpposite(int d) {
        if(d == 0) return 2;
        if(d == 1) return 3;
        if(d == 2) return 0;
        return 1;
    }

    public static boolean outOfRange(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }

    public static void cleaningRobot() {

        int nx, ny, nd;
        int back;
        boolean isNotVisitedArea;
        boolean goBackward = false;
        int answer = 0;

        while(true) {
            if(!goBackward && !visited[r][c]) {
                visited[r][c] = true;
                answer++;
            }

            goBackward = false;
            isNotVisitedArea = false;
            nd = d;

            for(int i=0; i<4; i++) {
                nd = turn(nd);
                nx = r + dx[nd];
                ny = c + dy[nd];

                if(outOfRange(nx, ny)) continue;
                if(visited[nx][ny] || map[nx][ny] == 1) continue;

                r = nx;
                c = ny;
                d = nd;
                isNotVisitedArea = true;
                break;
            }
            if(isNotVisitedArea) continue;

            back = getOpposite(d);
            nx = r + dx[back];
            ny = c + dy[back];

            if(outOfRange(nx, ny) || map[nx][ny] == 1) break;

            r = nx;
            c = ny;
            goBackward = true;
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {

        input();
        cleaningRobot();
    }
}