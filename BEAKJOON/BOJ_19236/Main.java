package BEAKJOON.BOJ_19236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static final int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    public static final int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};

    public static int answer = 0;

    public static class Fish {
        int id;
        int d;
        boolean alive;

        public Fish(int id, int d, boolean alive) {
            this.id = id;
            this.d = d;
            this.alive = alive;
        }

        public Fish clone() { return new Fish(id, d, alive); }
    }

    public static boolean outOfRange(int x, int y) {
        return x < 0 || x >= 4 || y < 0 || y >= 4;
    }

    public static int rotate(int d) {
        if(d == 8) return 1;
        return d + 1;
    }

    public static void move(Fish[][] map, int x, int y) {

        for(int k=1; k<=16; k++) {
            fish:
            for(int i=0; i<4; i++) {
                for(int j=0; j<4; j++) {
                    if(k == map[i][j].id && map[i][j].alive) {
                        int d = map[i][j].d;
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        while(outOfRange(nx, ny) || (x == nx && y == ny)) {
                            d = rotate(d);
                            nx = i + dx[d];
                            ny = j + dy[d];
                        }

                        map[i][j].d = d;
                        Fish temp = map[i][j];
                        map[i][j] = map[nx][ny];
                        map[nx][ny] = temp;

                        break fish;
                    }
                }
            }
        }
    }

    public static void youthShark(int x, int y, int sum, Fish[][] map) {

        int d = map[x][y].d;
        sum += map[x][y].id;
        map[x][y].alive = false;

        answer = Math.max(answer, sum);
        move(map, x, y);

        for(int i=1; i<=3; i++) {
            int nx = x + dx[d] * i;
            int ny = y + dy[d] * i;

            if(outOfRange(nx, ny)) break;
            if(!map[nx][ny].alive) continue;

            Fish[][] newMap = new Fish[4][4];
            for(int j=0; j<4; j++) {
                for(int k=0; k<4; k++) {
                    newMap[j][k] = map[j][k].clone();
                }
            }

            youthShark(nx, ny, sum, newMap);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Fish[][] map = new Fish[4][4];

        for(int i=0; i<4; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<8; j+=2) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                map[i][j/2] = new Fish(a, b, true);
            }
        }

        youthShark(0, 0, 0, map);

        System.out.println(answer);
    }
}