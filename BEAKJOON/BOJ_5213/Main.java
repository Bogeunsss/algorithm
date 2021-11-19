package BEAKJOON.BOJ_5213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Tile {

    int value;
    int index;

    public Tile(int value, int index) {
        this.value = value;
        this.index = index;
    }
}

public class Main {

    public static final int[] dx = {-1, 1, 0, 0};
    public static final int[] dy = {0, 0, -1, 1};

    public static int n, max;
    public static int[] path;
    public static Tile[][] map;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new Tile[n+1][n*2+2];
        path = new int[n*n-n/2+1];
        max = 1;

        int index = 1;
        for(int i=1; i<=n; i++) {
            if(i % 2 == 1) {
                for(int j=1; j<=n*2; j+=2) {
                    st = new StringTokenizer(br.readLine(), " ");

                    map[i][j] = new Tile(Integer.parseInt(st.nextToken()), index);
                    map[i][j+1] = new Tile(Integer.parseInt(st.nextToken()), index++);
                }
            }else {
                for(int j=2; j<=n*2-1; j+=2) {
                    st = new StringTokenizer(br.readLine(), " ");

                    map[i][j] = new Tile(Integer.parseInt(st.nextToken()), index);
                    map[i][j+1] = new Tile(Integer.parseInt(st.nextToken()), index++);
                }
            }
        }
    }

    public static void findNote() {

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n+1][n*2+1];

        q.offer(new int[]{1, 1});
        q.offer(new int[]{1, 2});
        visited[1][1] = visited[1][2] = true;
        path[1] = 1;

        while(!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.peek()[1];
            q.poll();

            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx <= 0 || nx > n || ny <= 0 || ny > n * 2) continue;
                if(map[nx][ny] == null || map[nx][ny].value == 0 || visited[nx][ny]) continue;
                if(map[x][y].value == map[nx][ny].value) {
                    max = Math.max(max, map[nx][ny].index);
                    path[map[nx][ny].index] = map[x][y].index;

                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});

                    if(map[nx][ny-1] != null && map[nx][ny-1].index == map[nx][ny].index) {
                        visited[nx][ny-1] = true;
                        q.offer(new int[]{nx, ny - 1});
                    }else if(map[nx][ny+1] != null && map[nx][ny+1].index == map[nx][ny].index) {
                        visited[nx][ny+1] = true;
                        q.offer(new int[]{nx, ny + 1});
                    }
                }
            }
        }
    }

    public static void findPath() {

        List<Integer> answer = new ArrayList<>();

        while(max != path[max]) {
            answer.add(max);
            max = path[max];
        }
        answer.add(1);

        System.out.println(answer.size());
        for(int i=answer.size()-1; i>=0; i--) {
            System.out.print(answer.get(i) + " ");
        }
    }

    public static void tutor() {

        findNote();
        findPath();
    }

    public static void main(String[] args) throws IOException {

        input();
        tutor();
    }
}