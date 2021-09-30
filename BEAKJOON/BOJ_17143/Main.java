package BEAKJOON.BOJ_17143;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static final int[] dx = {-1, 1, 0, 0};
    public static final int[] dy = {0, 0, 1, -1};

    public static int R, C, M, answer;
    public static int[][] map;
    public static List<Shark> sharks = new ArrayList<>();

    public static class Shark {
        int r;
        int c;
        int speed;
        int direction;
        int size;
        boolean alive;

        public Shark(int r, int c, int speed, int direction, int size, boolean alive) {
            this.r = r;
            this.c = c;
            this.speed = speed;
            this.direction = direction;
            this.size = size;
            this.alive = alive;
        }
    }

    public static void predation(int[][] a, int x, int y, int now) {

        if(a[x][y] > 0) {
            if(sharks.get(a[x][y]).size < sharks.get(now).size) {
                sharks.get(a[x][y]).alive = false;
                a[x][y] = now;
            }else {
                sharks.get(now).alive = false;
            }
        }else {
            a[x][y] = now;
        }
    }

    public static void move() {
        int[][] afterMove = new int[R+1][C+1];

        for(int i=1; i<=M; i++) {
            Shark shark = sharks.get(i);
            if(!shark.alive) continue;

            int x = shark.r;
            int y = shark.c;
            int speed = shark.speed;
            int d = shark.direction;
            int nx, ny;

            while(true) {
                nx = x + dx[d] * speed;
                ny = y + dy[d] * speed;

                if(0 < nx && nx <= R && 0 < ny && ny <= C) break;
                if(d == 0 && nx <= 0) {
                    speed -= x - 1;
                    x = 1;
                    d = 1;
                }else if(d == 1 && nx > R) {
                    speed -= R - x;
                    x = R;
                    d = 0;
                }else if(d == 2 && ny > C) {
                    speed -= C - y;
                    y = C;
                    d = 3;
                }else if(d == 3 && ny <= 0) {
                    speed -= y - 1;
                    y = 1;
                    d = 2;
                }
            }

            predation(afterMove, nx, ny, i);

            sharks.get(i).r = nx;
            sharks.get(i).c = ny;
            sharks.get(i).direction = d;
        }
        for(int i=1; i<=R; i++) {
            for(int j=1; j<=C; j++) {
                map[i][j] = afterMove[i][j];
            }
        }
    }

    public static void fishing() {

        for(int i=1; i<=C; i++) {
            for(int j=1; j<=R; j++) {
                if(map[j][i] > 0) {
                    Shark shark = sharks.get(map[j][i]);

                    shark.alive = false;
                    answer += shark.size;
                    map[j][i] = 0;
                    break;
                }
            }

            move();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[R+1][C+1];
        answer = 0;

        sharks.add(null);
        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            Shark shark = new Shark(r, c, s, d - 1, z, true);
            sharks.add(shark);
            map[r][c] = i;
        }

        fishing();

        System.out.println(answer);
    }
}