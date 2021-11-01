package BEAKJOON.BOJ_3197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static final int[] dx = {-1, 1, 0, 0};
    public static final int[] dy = {0, 0, -1, 1};

    public static Queue<int[]> waterQ, waterBufferedQ, swanQ, swanBufferedQ;
    public static int r, c;
    public static char[][] map;
    public static boolean[][] visited;
    public static int[] swan;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        visited = new boolean[r][c];
        swan = new int[2];

        waterQ = new LinkedList<>();
        waterBufferedQ = new LinkedList<>();
        swanQ = new LinkedList<>();
        swanBufferedQ = new LinkedList<>();

        for(int i=0; i<r; i++) {
            String row = br.readLine();
            for(int j=0; j<c; j++) {
                map[i][j] = row.charAt(j);

                if(map[i][j] != 'X') waterQ.offer(new int[]{i, j});
                if(map[i][j] == 'L') {
                    swan[0] = i;
                    swan[1] = j;
                }
            }
        }
    }

    public static boolean outOfRange(int x, int y) {
        return x < 0 || x >= r || y < 0 || y >= c;
    }

    public static boolean isMeet() {

        while(!swanQ.isEmpty()) {
            int x = swanQ.peek()[0];
            int y = swanQ.peek()[1];
            swanQ.poll();

            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(outOfRange(nx, ny)) continue;
                if(visited[nx][ny]) continue;
                if(map[nx][ny] == 'L') return true;

                if(map[nx][ny] == '.') swanQ.offer(new int[]{nx, ny});
                else if(map[nx][ny] == 'X') swanBufferedQ.offer(new int[]{nx, ny});

                visited[nx][ny] = true;
            }
        }

        return false;
    }

    public static void melt() {

        while(!waterQ.isEmpty()) {
            int x = waterQ.peek()[0];
            int y = waterQ.peek()[1];
            waterQ.poll();

            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(outOfRange(nx, ny)) continue;
                if(map[nx][ny] == 'X') {
                    map[nx][ny] = '.';
                    waterBufferedQ.offer(new int[]{nx, ny});
                }
            }
        }
    }

    public static void theSwanLake() {

        int day = 0;
        swanQ.offer(new int[]{swan[0], swan[1]});
        visited[swan[0]][swan[1]] = true;

        while(!isMeet()) {
            melt();

            waterQ.clear();
            swanQ.clear();

            waterQ.addAll(waterBufferedQ);
            swanQ.addAll(swanBufferedQ);

            waterBufferedQ.clear();
            swanBufferedQ.clear();
            day++;
        }

        System.out.println(day);
    }

    public static void main(String[] args) throws IOException {

        input();
        theSwanLake();
    }
}