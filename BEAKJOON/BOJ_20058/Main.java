package BEAKJOON.BOJ_20058;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static final int[] dx = {-1, 1, 0, 0};
    public static final int[] dy = {0, 0, -1, 1};

    public static int N, Q, len;
    public static int[][] map;
    public static boolean[][] visited;

    public static void rotate(int _x, int _y, int n) {

        int m = n / 2;
        for(int i=0; i<m; i++) {
            int startX = _x + i;
            int startY = _y + i;
            int endX = _x + n - i - 1;
            int endY = _y + n - i - 1;

            int x = endX;
            int y = startY;
            int z = 0;

            List<Integer> backup = new ArrayList<>();
            for(int j=startX; j<endX; j++) backup.add(map[j][startY]);
            for(int j=startX; j<endX; j++) map[j][startY] = map[endX][y++];
            for(int j=startY; j<endY; j++) map[endX][j] = map[x--][endY];
            for(int j=endX; j>startX; j--) map[j][endY] = map[startX][y--];
            for(int j=endY; j>startY; j--) map[startX][j] = backup.get(z++);
        }
    }

    public static void spell(int x, int y, int n, int L) {

        if(n == L) {
            rotate(x, y, n);
            return;
        }

        spell(x, y, n / 2, L);
        spell(x, y + n / 2, n / 2, L);
        spell(x + n / 2, y, n / 2, L);
        spell(x + n / 2, y + n / 2, n / 2, L);

    }

    public static void cast() {

        List<int[]> candidates = new ArrayList<>();

        for(int i=0; i<len; i++) {
            for(int j=0; j<len; j++) {
                if(map[i][j] == 0) continue;

                int cnt = 0;
                for(int k=0; k<4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if(nx < 0 || nx >= len || ny < 0 || ny >= len) continue;
                    if(map[nx][ny] == 0) continue;

                    cnt++;
                }
                if(cnt < 3) candidates.add(new int[]{i, j});
            }
        }

        for(int[] candidate : candidates) {
            int x = candidate[0];
            int y = candidate[1];

            map[x][y]--;
        }
    }

    public static int count(int _x, int _y) {

        Queue<int[]> q = new LinkedList<>();
        int ret = 1;

        q.offer(new int[]{_x, _y});
        visited[_x][_y] = true;

        while(!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.peek()[1];
            q.poll();

            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= len || ny < 0 || ny >= len) continue;
                if(visited[nx][ny] || map[nx][ny] == 0) continue;

                ret++;
                visited[nx][ny] = true;
                q.offer(new int[]{nx, ny});
            }
        }

        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        len = 1 << N;
        map = new int[len][len];
        visited = new boolean[len][len];

        int[] answer = new int[2];

        for(int i=0; i<len; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<len; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<Q; i++) {
            spell(0, 0, len, 1 << Integer.parseInt(st.nextToken()));
            cast();
        }

        for(int i=0; i<len; i++) {
            for(int j=0; j<len; j++) {
                answer[0] += map[i][j];

                if(!visited[i][j] && map[i][j] > 0) {
                    answer[1] = Math.max(answer[1], count(i, j));
                }
            }
        }

        System.out.println(answer[0]);
        System.out.println(answer[1]);
    }
}