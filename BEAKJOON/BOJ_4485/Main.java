package BEAKJOON.BOJ_4485;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static final int[] dx = {-1, 1, 0, 0};
    public static final int[] dy = {0, 0, -1, 1};
    public static final int INF = Integer.MAX_VALUE;

    public static int[][] cave, d;
    public static int N;

    public static void dijkstra(int _x, int _y) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        pq.offer(new int[]{_x, _y, cave[_x][_y]});
        d[_x][_y] = cave[_x][_y];

        while(!pq.isEmpty()) {
            int[] node = pq.poll();
            int x = node[0];
            int y = node[1];
            int z = node[2];

            if(d[x][y] < z) continue;
            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if(d[nx][ny] > z + cave[nx][ny]) {
                    d[nx][ny] = z + cave[nx][ny];
                    pq.offer(new int[]{nx, ny, d[nx][ny]});
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        StringTokenizer st;

        int index = 1;

        while(true) {
            N = Integer.parseInt(br.readLine());

            if(N == 0) break;

            cave = new int[N][N];
            d = new int[N][N];

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine(), " ");

                for(int j=0; j<N; j++) {
                    cave[i][j] = Integer.parseInt(st.nextToken());
                    d[i][j] = INF;
                }
            }

            dijkstra(0, 0);

            answer.append("Problem ").append(index++).append(": ");
            answer.append(d[N-1][N-1]).append("\n");
        }

        System.out.println(answer);
    }
}