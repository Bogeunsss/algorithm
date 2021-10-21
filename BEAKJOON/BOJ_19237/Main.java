package BEAKJOON.BOJ_19237;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static final int[] dx = {0, -1, 1, 0, 0};
    public static final int[] dy = {0, 0, 0, -1, 1};

    public static int n, m, k;
    public static List<Shark> list = new ArrayList<Shark>();
    public static Shark[][] map;
    public static Smell[][] smell;

    public static class Shark {

        int id;
        int x;
        int y;
        int d;
        int[][] priority = new int[5][4];
        boolean isMove = false;

        public Shark(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }

        public Shark clone() {
            Shark shark = new Shark(id, x, y);
            shark.d = d;
            shark.priority = priority;

            return shark;
        }
    }

    public static class Smell {

        int id;
        int rest;

        public Smell(int id, int rest) {
            this.id = id;
            this.rest = rest;
        }
    }

    public static boolean outOfRange(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= n;
    }

    public static int back(int d) {
        if(d == 1) return 2;
        if(d == 2) return 1;
        if(d == 3) return 4;
        return 3;
    }

    public static void spread() {

        for(int i=1; i<=m; i++) {
            if(list.get(i) == null) continue;

            int id = list.get(i).id;
            int x = list.get(i).x;
            int y = list.get(i).y;

            smell[x][y] = new Smell(id, k);
        }
    }

    public static void fade() {

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(smell[i][j] != null) {
                    smell[i][j].rest--;

                    if(smell[i][j].rest == 0) smell[i][j] = null;
                }
            }
        }
    }

    public static void move() {

        for(int i=1; i<=m; i++) {
            if(list.get(i) == null) continue;

            int x = list.get(i).x;
            int y = list.get(i).y;
            int d = list.get(i).d;
            int[][] priority = list.get(i).priority;
            int cnt = 0;

            for(int j=1; j<=4; j++) {
                int nx = x + dx[j];
                int ny = y + dy[j];

                if(outOfRange(nx, ny)) continue;
                if(smell[nx][ny] == null) cnt++;
            }

            if(cnt == 0) {

                for(int j=0; j<4; j++) {
                    int nd = priority[d][j];
                    int nx = x + dx[nd];
                    int ny = y + dy[nd];

                    if(outOfRange(nx, ny)) continue;
                    if(smell[nx][ny] != null && smell[nx][ny].id == i) {
                        list.get(i).x = nx;
                        list.get(i).y = ny;
                        list.get(i).d = nd;
                        list.get(i).isMove = true;
                        break;
                    }
                }
            }else if(cnt == 1) {

                for(int j=1; j<=4; j++) {
                    int nx = x + dx[j];
                    int ny = y + dy[j];

                    if(outOfRange(nx, ny)) continue;
                    if(smell[nx][ny] == null) {
                        list.get(i).x = nx;
                        list.get(i).y = ny;
                        list.get(i).d = j;
                        list.get(i).isMove = true;
                        break;
                    }
                }
            }else {

                for(int j=0; j<4; j++) {
                    int nd = priority[d][j];
                    int nx = x + dx[nd];
                    int ny = y + dy[nd];

                    if(outOfRange(nx, ny)) continue;
                    if(smell[nx][ny] == null) {
                        list.get(i).x = nx;
                        list.get(i).y = ny;
                        list.get(i).d = nd;
                        list.get(i).isMove = true;
                        break;
                    }
                }
            }
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(map[i][j] != null && list.get(map[i][j].id).isMove) {
                    int id = map[i][j].id;
                    int x = list.get(id).x;
                    int y = list.get(id).y;

                    if(map[x][y] != null) {
                        if(id < map[x][y].id) {
                            list.set(map[x][y].id, null);
                        }else {
                            list.set(id, null);
                            map[i][j] = null;
                            continue;
                        }
                    }

                    map[x][y] = list.get(id).clone();
                    list.get(id).isMove = false;
                    map[i][j] = null;
                }
            }
        }
    }

    public static boolean fin() {

        for(int i=1; i<=m; i++) {
            if(list.get(i) != null && list.get(i).id > 1) return false;
        }
        return true;
    }

    public static int adultShark() {
        int T = 0;

        while(T++ < 1000) {
            spread();
            move();
            fade();

            if(fin()) return T;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new Shark[n][n];
        smell = new Smell[n][n];

        for(int i=0; i<=m; i++) list.add(null);
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<n; j++) {
                int id = Integer.parseInt(st.nextToken());

                if(id > 0) {
                    map[i][j] = new Shark(id, i, j);
                    list.set(id, map[i][j]);
                }
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i<=m; i++) {
            int d = Integer.parseInt(st.nextToken());

            list.get(i).d = d;
        }
        for(int i=1; i<=m; i++) {
            for(int x=1; x<=4; x++) {
                st = new StringTokenizer(br.readLine(), " ");
                for(int y=0; y<4; y++) {
                    list.get(i).priority[x][y] = Integer.parseInt(st.nextToken());
                }
            }
        }

        System.out.println(adultShark());
    }
}
