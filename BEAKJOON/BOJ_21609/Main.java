package BEAKJOON.BOJ_21609;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static final int[] dx = {-1, 1, 0, 0};
    public static final int[] dy = {0, 0, -1, 1};

    public static List<int[]> blocks;
    public static boolean[][] visited;
    public static int[][] map;
    public static int n, m;
    public static int answer = 0;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static List<int[]> findGroup(int _x, int _y, int unit) {

        List<int[]> ret = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();

        ret.add(new int[]{_x, _y});
        q.offer(new int[]{_x, _y});
        visited[_x][_y] = true;

        while(!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.peek()[1];
            q.poll();

            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if(visited[nx][ny] || map[nx][ny] <= -1) continue;
                if(map[nx][ny] > 0 && map[nx][ny] != unit) continue;

                visited[nx][ny] = true;
                ret.add(new int[]{nx, ny});
                q.offer(new int[]{nx, ny});
            }
        }

        for(int i=0; i<ret.size(); i++) {
            int x = ret.get(i)[0];
            int y = ret.get(i)[1];

            if(map[x][y] == 0) visited[x][y] = false;
        }

        return ret;
    }

    public static void remove() {

        answer += blocks.size() * blocks.size();

        for(int i=0; i<blocks.size(); i++) {
            int x = blocks.get(i)[0];
            int y = blocks.get(i)[1];

            map[x][y] = -2;
        }
    }

    public static void gravity() {

        for(int i=0; i<n; i++) {
            for(int j=n-1; j>=0; j--) {
                if(map[j][i] == -2) {
                    int x = j;
                    int y = i;
                    while(x > 0 && map[x][y] == -2) x--;
                    if(map[x][y] == -1) continue;
                    map[j][i] = map[x][y];
                    map[x][y] = -2;
                }
            }
        }
    }

    public static void rotate() {

        int square = n / 2;

        for(int i=0; i<square; i++) {
            int startX = i;
            int startY = i;
            int endX = n - i - 1;
            int endY = n - i - 1;

            int x = endX;
            int y = endY;
            int z = 0;
            List<Integer> backup = new ArrayList<>();

            for(int j=startX; j<endX; j++) backup.add(map[j][startX]);
            for(int j=startX; j<endX; j++) map[j][startX] = map[startX][y--];
            for(int j=endY; j>startY; j--) map[startX][j] = map[x--][endY];
            for(int j=endX; j>startX; j--) map[j][endY] = map[endX][y++];
            for(int j=startY; j<endY; j++) map[endX][j] = backup.get(z++);
        }
    }

    public static void autoPlay() {

        while(true) {
            blocks = new ArrayList<>();
            visited = new boolean[n][n];

            int max = 0, rainbow = 0;

            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    if(!visited[i][j] && map[i][j] > 0) {
                        List<int[]> group = findGroup(i, j, map[i][j]);

                        if(group.size() >= 2) {
                            int cnt = 0;

                            for(int k=0; k<group.size(); k++) {
                                int x = group.get(k)[0];
                                int y = group.get(k)[1];

                                if(map[x][y] == 0) cnt++;
                            }

                            if(max < group.size()) {
                                max = group.size();
                                rainbow = cnt;
                                blocks = group;
                            }else if(max == group.size()) {

                                if(rainbow <= cnt) {
                                    rainbow = cnt;
                                    blocks = group;
                                }
                            }
                        }
                    }
                }
            }
            if(max == 0) break;

            remove();
            gravity();
            rotate();
            gravity();
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {

        input();
        autoPlay();
    }
}