package BEAKJOON.BOJ_2174;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static final int[] dx = {1, 0, -1, 0}; // 북동남서
    public static final int[] dy = {0, 1, 0, -1};

    public static Map<Integer,Robot> robots = new HashMap<>();
    public static Queue<int[]> commands = new LinkedList<>();
    public static int[][] map;
    public static int a, b;

    public static class Robot {
        int r;
        int c;
        int d;

        public Robot(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    public static int directions(String s) {
        if(s.equals("N")) return 0;
        if(s.equals("E")) return 1;
        if(s.equals("S")) return 2;
        return 3;
    }

    public static int actions(String s) {
        if(s.equals("F")) return 0;
        if(s.equals("R")) return 1;
        return 2;
    }

    public static boolean outOfRange(int x, int y) {
        return x <= 0 || x > b || y <= 0 || y > a;
    }

    public static int rotate(int now, int d) {
        if(d == 1) {
            return now == 3 ? 0 : now + 1;
        }
        return now == 0 ? 3 : now - 1;
    }

    public static void move(int x, int y, int nx, int ny) {
        int pos = map[x][y];
        map[x][y] = map[nx][ny];
        map[nx][ny] = pos;
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        map = new int[b+1][a+1];

        st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int c = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            String d = st.nextToken();

            map[r][c] = i;
            robots.put(i, new Robot(r, c, directions(d)));
        }
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int id = Integer.parseInt(st.nextToken());
            int action = actions(st.nextToken());
            int times = Integer.parseInt(st.nextToken());

            commands.offer(new int[]{id, action, times});
        }
    }

    public static String simulation() {
        while(!commands.isEmpty()) {
            int[] command = commands.poll();
            int id = command[0];
            int action = command[1];
            int times = command[2];

            Robot robot = robots.get(id);
            int r = robot.r;
            int c = robot.c;
            int d = robot.d;
            int nr = r, nc = c;

            while(times-- > 0) {
                if(action == 0) {
                    nr = nr + dx[d];
                    nc = nc + dy[d];
                }else {
                    d = rotate(d, action);
                }
                if(outOfRange(nr, nc)) return "Robot " + id + " crashes into the wall";
                if(map[nr][nc] != id && map[nr][nc] > 0) return "Robot " + id + " crashes into robot " + map[nr][nc];
            }

            robot.r = nr;
            robot.c = nc;
            robot.d = d;
            move(r, c, nr, nc);
        }

        return "OK";
    }

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(simulation());
    }
}