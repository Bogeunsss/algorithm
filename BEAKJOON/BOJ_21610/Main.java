package BEAKJOON.BOJ_21610;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static final int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    public static final int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};

    public static int n, m;
    public static int[][] map, info;
    public static List<Cloud> clouds = new ArrayList<>();
    public static int answer = 0;

    public static class Cloud {

        int x;
        int y;

        public Cloud(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n+1][n+1];
        info = new int[m][2];

        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1; j<=n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                answer += map[i][j];
            }
        }
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            info[i][0] = Integer.parseInt(st.nextToken());
            info[i][1] = Integer.parseInt(st.nextToken());
        }

        clouds.add(new Cloud(n, 1));
        clouds.add(new Cloud(n, 2));
        clouds.add(new Cloud(n - 1, 1));
        clouds.add(new Cloud(n - 1, 2));
    }

    public static void move(int idx) {

        int d = info[idx][0];
        int s = info[idx][1];

        for(Cloud cloud : clouds) {
            int nx = cloud.x + (dx[d] * s % n);
            int ny = cloud.y + (dy[d] * s % n);

            if(nx <= 0) nx += n;
            else if(nx > n) nx -= n;
            if(ny <= 0) ny += n;
            else if(ny > n) ny -= n;

            cloud.x = nx;
            cloud.y = ny;
        }
    }

    public static void rain() {

        for(Cloud cloud : clouds) {
            int x = cloud.x;
            int y = cloud.y;

            map[x][y]++;
            answer++;
        }
    }

    public static void waterCopyBug() {

        for(Cloud cloud : clouds) {
            int x = cloud.x;
            int y = cloud.y;
            int baskets = 0;

            for(int i=2; i<=8; i+=2) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx <= 0 || nx > n || ny <= 0 || ny > n) continue;
                if(map[nx][ny] > 0) baskets++;
            }

            map[x][y] += baskets;
            answer += baskets;
        }
    }

    public static boolean isRemovedLocation(List<Cloud> removed, int x, int y) {

        for(int i=0; i<removed.size(); i++) {
            if(x == removed.get(i).x && y == removed.get(i).y) return true;
        }
        return false;
    }

    public static void generateCloud() {

        List<Cloud> removedCloud = new ArrayList<>(clouds);
        clouds.clear();

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                if(isRemovedLocation(removedCloud, i, j)) continue;
                if(map[i][j] >= 2) {
                    clouds.add(new Cloud(i, j));
                    map[i][j] -= 2;
                    answer -= 2;
                }
            }
        }
    }

    public static void rainDance() {

        for(int i=0; i<m; i++) {
            move(i);
            rain();
            waterCopyBug();
            generateCloud();
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {

        input();
        rainDance();
    }
}