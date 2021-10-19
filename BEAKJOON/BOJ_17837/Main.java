package BEAKJOON.BOJ_17837;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static final int[] dx = {0, 0, 0, -1, 1};
    public static final int[] dy = {0, 1, -1, 0, 0};

    public static List<Chess> pieces = new ArrayList<>();
    public static List<List<List<Integer>>> map = new ArrayList<>();
    public static int[][] color;
    public static int n, k;

    public static class Chess {
        int x;
        int y;
        int d;

        public Chess(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    public static int turn(int d) {
        if(d == 1) return 2;
        if(d == 2) return 1;
        if(d == 3) return 4;
        return 3;
    }

    public static int getIndex(int x, int y, int z) {

        for(int i=0; i<map.get(x).get(y).size(); i++) {
            if(map.get(x).get(y).get(i) == z) return i;
        }
        return -1;
    }

    public static int loaded(int x, int y, int id) {
        int ret = 0;

        for(int i=map.get(x).get(y).size()-1; i>=0 ; i--) {
            ret++;
            if(map.get(x).get(y).get(i) == id) break;
        }

        return ret;
    }

    public static void remove(int x, int y) {

        map.get(x).get(y).remove(map.get(x).get(y).size() - 1);
    }

    public static void move(int x, int y, int nx, int ny, int id, int idx, int cmd) {
        int load;

        switch(cmd) {
            case 0:
                for(int i=idx; i<map.get(x).get(y).size(); i++) {
                    int v = map.get(x).get(y).get(i);

                    map.get(nx).get(ny).add(v);
                    pieces.get(v).x = nx;
                    pieces.get(v).y = ny;
                }
                load = loaded(x, y, id);
                for(int i=0; i<load; i++) {
                    remove(x, y);
                }
                return;
            case 1:
                for(int i=map.get(x).get(y).size()-1; i>=idx; i--) {
                    int v = map.get(x).get(y).get(i);

                    map.get(nx).get(ny).add(v);
                    pieces.get(v).x = nx;
                    pieces.get(v).y = ny;
                }
                load = loaded(x, y, id);
                for(int i=0; i<load; i++) {
                    remove(x, y);
                }
                return;
            case 2:
                int d = turn(pieces.get(id).d);
                pieces.get(id).d = d;

                nx = x + dx[d];
                ny = y + dy[d];

                if(0 <= nx && nx < n && 0 <= ny && ny < n) {
                    if(color[nx][ny] != 2) {
                        move(x, y, nx, ny, id, idx, color[nx][ny]);
                    }
                }
        }
    }

    public static boolean fin() {

        for(int i=1; i<=k; i++) {
            Chess chess = pieces.get(i);
            int x = chess.x;
            int y = chess.y;

            if(map.get(x).get(y).size() >= 4) return true;
        }

        return false;
    }

    public static int newGame() {
        int T = 0;

        while(T++ <= 1000) {

            for(int i=1; i<=k; i++) {
                Chess chess = pieces.get(i);

                int x = chess.x;
                int y = chess.y;
                int d = chess.d;

                int nx = x + dx[d];
                int ny = y + dy[d];
                int index = getIndex(x, y, i);

                if(nx < 0 || nx >= n || ny < 0 || ny >= n) move(x, y, nx, ny, i, index, 2);
                else move(x, y, nx, ny, i, index, color[nx][ny]);

                if(fin()) return T;
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        color = new int[n][n];
        pieces.add(null);

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            map.add(new ArrayList<>());

            for(int j=0; j<n; j++) {
                color[i][j] = Integer.parseInt(st.nextToken());
                map.get(i).add(new ArrayList<>());
            }
        }
        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());

            map.get(r).get(c).add(i + 1);
            pieces.add(new Chess(r, c, d));
        }

        System.out.println(newGame());
    }
}