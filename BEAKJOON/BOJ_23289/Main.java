package BEAKJOON.BOJ_23289;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static final int[] dx = {0, 0, -1, 1, 0, 1, -1, -1, -1, -1, 1, 1, 1};
    public static final int[] dy = {0, 1, 1, 1, -1, -1, -1, 0, -1, 1, 0, 1, -1};

    public static int r, c, k, w;
    public static int[][] map, temperature;
    public static boolean[][][] walls;

    public static List<int[]> heaters, targets;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[r+1][c+1];
        temperature = new int[r+1][c+1];
        walls = new boolean[r+1][c+1][13];

        heaters = new ArrayList<>();
        targets = new ArrayList<>();

        for(int i=1; i<=r; i++) {
            st  = new StringTokenizer(br.readLine(), " ");
            for(int j=1; j<=c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(1 <= map[i][j] && map[i][j] <= 4) heaters.add(new int[]{i, j});
                if(map[i][j] == 5) targets.add(new int[]{i, j});
            }
        }

        w = Integer.parseInt(br.readLine());
        for(int i=0; i<w; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            if(t == 0) walls[x][y][7] = walls[x-1][y][10] = true;
            else walls[x][y][1] = walls[x][y+1][4] = true;
        }
    }

    public static int getDirection(int d) {

        if(d == 1) return 1;
        if(d == 2) return 4;
        if(d == 3) return 7;
        return 10;
    }

    public static int getLeft(int d) {

        if(d == 1) return 7;
        if(d == 4) return 10;
        if(d == 7) return 4;
        return 1;
    }

    public static int getRight(int d) {

        if(d == 1) return 10;
        if(d == 4) return 7;
        if(d == 7) return 1;
        return 4;
    }

    public static int getOpposite(int d) {

        if(d == 1) return 4;
        if(d == 4) return 1;
        if(d == 7) return 10;
        return 7;
    }

    public static void blow() {

        int[][][] temp = new int[heaters.size()][r+1][c+1];
        Queue<int[]> q;

        for(int i=0; i<heaters.size(); i++) {
            q = new LinkedList<>();

            int _x = heaters.get(i)[0];
            int _y = heaters.get(i)[1];
            int d = getDirection(map[_x][_y]);
            int left = getLeft(d);
            int right = getRight(d);

            q.offer(new int[]{_x + dx[d], _y + dy[d]});
            temp[i][_x + dx[d]][_y + dy[d]] = 5;

            while(!q.isEmpty()) {
                int x = q.peek()[0];
                int y = q.peek()[1];
                q.poll();

                for(int j=d; j<=d+2; j++) {
                    int nx = x + dx[j];
                    int ny = y + dy[j];

                    if(nx <= 0 || nx > r || ny <= 0 || ny > c) continue;
                    if(j == d) {
                        if(walls[x][y][d]) continue;
                    }else if(j == d + 1) {
                        if(walls[x][y][left] || walls[x + dx[left]][y + dy[left]][d]) continue;
                    }else {
                        if(walls[x][y][right] || walls[x + dx[right]][y + dy[right]][d]) continue;
                    }

                    temp[i][nx][ny] = temp[i][x][y] - 1;
                    if(temp[i][nx][ny] > 0) q.offer(new int[]{nx, ny});
                }
            }

            for(int j=1; j<=r; j++) {
                for(int k=1; k<=c; k++) {
                    temperature[j][k] += temp[i][j][k];
                }
            }
        }
    }

    public static void conditioning() {

        int[][] temp = new int[r+1][c+1];
        boolean[][][] visited = new boolean[r+1][c+1][13];

        for(int i=1; i<=r; i++) {
            for(int j=1; j<=c; j++) {
                for(int k=1; k<=10; k+=3) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if(nx <= 0 || nx > r || ny <= 0 || ny > c) continue;
                    if(visited[i][j][k]) continue;
                    if(walls[i][j][k]) continue;

                    int gap = Math.abs(temperature[i][j] - temperature[nx][ny]) / 4;
                    if(temperature[i][j] > temperature[nx][ny]) {
                        temp[i][j] -= gap;
                        temp[nx][ny] += gap;
                    }else {
                        temp[i][j] += gap;
                        temp[nx][ny] -= gap;
                    }

                    visited[i][j][k] = true;
                    visited[nx][ny][getOpposite(k)] = true;
                }
            }
        }
        for(int i=1; i<=r; i++) {
            for(int j=1; j<=c; j++) {
                temperature[i][j] += temp[i][j];
            }
        }
    }

    public static void decrease() {

        for(int i=1; i<=c; i++) {
            if(temperature[1][i] > 0) temperature[1][i]--;
            if(temperature[r][i] > 0) temperature[r][i]--;
        }
        for(int i=2; i<r; i++) {
            if(temperature[i][1] > 0) temperature[i][1]--;
            if(temperature[i][c] > 0) temperature[i][c]--;
        }
    }

    public static boolean check() {

        for (int[] target : targets) {
            int x = target[0];
            int y = target[1];

            if (temperature[x][y] < k) return false;
        }
        return true;
    }

    public static void heating() {

        int answer = 0;

        while(answer <= 100) {

            blow();
            conditioning();
            decrease();
            answer++;
            if(check()) break;
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {

        input();
        heating();

    }
}