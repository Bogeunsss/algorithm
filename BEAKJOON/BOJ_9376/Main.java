package BEAKJOON.BOJ_9376;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static final int[] dx = {-1, 1, 0, 0};
    public static final int[] dy = {0, 0, -1, 1};

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder answer = new StringBuilder();
    public static int h, w;
    public static int[][][] d;
    public static char[][] prison;
    public static Queue<int[]> q;

    public static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        prison = new char[h+2][w+2];
        d = new int[h+2][w+2][3];

        q = new LinkedList<>();

        int number = 0;
        for(int i=0; i<=h+1; i++) {
            Arrays.fill(prison[i], '.');
            for(int j=0; j<=w+1; j++) {
                Arrays.fill(d[i][j], Integer.MAX_VALUE);
            }
        }
        for(int i=1; i<=h; i++) {
            String row = br.readLine();
            for(int j=1; j<=w; j++) {
                prison[i][j] = row.charAt(j-1);

                if(prison[i][j] == '$') {
                    prison[i][j] = '.';
                    d[i][j][number] = 0;
                    q.offer(new int[]{i, j, number++});
                }
            }
        }

        d[0][0][number] = 0;
        q.offer(new int[]{0, 0, number});
    }

    public static void prisonBreak() {

        while(!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.peek()[1];
            int k = q.peek()[2];
            q.poll();

            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx > h + 1 || ny < 0 || ny > w + 1) continue;
                if(prison[nx][ny] == '*') continue;
                if(prison[nx][ny] == '.') {
                    if(d[nx][ny][k] > d[x][y][k]) {
                        d[nx][ny][k] = d[x][y][k];
                        q.offer(new int[]{nx, ny, k});
                    }
                }else if(prison[nx][ny] == '#') {
                    if(d[nx][ny][k] > d[x][y][k] + 1) {
                        d[nx][ny][k] = d[x][y][k] + 1;
                        q.offer(new int[]{nx, ny, k});
                    }
                }
            }
        }
    }

    public static void count() {

        int min = Integer.MAX_VALUE;

        for(int i=1; i<=h; i++) {
            for(int j=1; j<=w; j++) {
                int doors = d[i][j][0] + d[i][j][1] + d[i][j][2];

                if(prison[i][j] == '#') doors -= 2;
                min = Math.min(min, doors);
            }
        }

        answer.append(min).append("\n");
    }

    public static void escape() throws IOException {

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            input();
            prisonBreak();
            count();
        }

        System.out.print(answer);
    }

    public static void main(String[] args) throws IOException {

        escape();
    }
}
