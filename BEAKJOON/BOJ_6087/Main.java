package BEAKJOON.BOJ_6087;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static final int INF = Integer.MAX_VALUE;
    public static final int[] dx = {-1, 1, 0, 0};
    public static final int[] dy = {0, 0, -1, 1};

    public static int[][] points = new int[2][2];
    public static char[][] map;
    public static int[][] dist;
    public static int w, h;

    public static int laser(int _x, int _y) {
        Queue<int[]> q = new LinkedList<>();

        for(int i=0; i<4; i++) {
            q.offer(new int[]{_x, _y, i, 0});
        }
        dist[_x][_y] = 0;
        map[_x][_y] = '.';

        while(!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.peek()[1];
            int d = q.peek()[2];
            int m = q.peek()[3];
            q.poll();

            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int mm = m;

                if(nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
                if(map[nx][ny] == '*') continue;
                if(d != i) mm++;
                if(dist[nx][ny] >= mm) {
                    dist[nx][ny] = mm;
                    q.offer(new int[]{nx, ny, i, mm});
                }
            }
        }

        return dist[points[1][0]][points[1][1]];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        String s;

        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new char[h][w];
        dist = new int[h][w];

        int index = 0;

        for(int i=0; i<h; i++) {
            s = br.readLine();
            for(int j=0; j<w; j++) {
                map[i][j] = s.charAt(j);
                dist[i][j] = INF;

                if(map[i][j] == 'C') {
                    points[index][0] = i;
                    points[index][1] = j;
                    index++;
                }
            }
        }

        System.out.println(laser(points[0][0], points[0][1]));
    }
}