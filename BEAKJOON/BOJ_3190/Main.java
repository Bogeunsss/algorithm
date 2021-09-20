package BEAKJOON.BOJ_3190;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static final int[] dx = {0, 1, 0, -1};
    public static final int[] dy = {1, 0, -1, 0};

    public static Deque<Point> snake = new ArrayDeque<>();
    public static List<Integer> times = new ArrayList<>();
    public static List<Character> directions = new ArrayList<>();
    public static int[][] board;
    public static int n, k, l, hx, hy, tx, ty, dir;
    public static int answer = 0;

    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int nextDirection(char c, int d) {
        if(c == 'D') {
            return (d + 1) % 4;
        }
        return d == 0 ? 3 : d - 1;
    }

    public static void move() {
        int index = 0;

        while(true) {
            hx += dx[dir];
            hy += dy[dir];

            answer++;

            if(hx < 1 || hx > n || hy < 1 || hy > n) return;
            if(board[hx][hy] == 2) return;
            if(board[hx][hy] == 0) {
                board[hx][hy] = 2;

                Point p = snake.pollLast();
                if(p != null) {
                    board[p.x][p.y] = 0;
                    snake.offerFirst(new Point(hx, hy));
                }
            }else if(board[hx][hy] == 1) {
                board[hx][hy] = 2;
                snake.offerFirst(new Point(hx, hy));
            }

            if(index < l && answer == times.get(index)) {
                dir = nextDirection(directions.get(index), dir);
                index++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        board = new int[n+1][n+1];

        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            board[r][c] = 1;
        }

        l = Integer.parseInt(br.readLine());

        for(int i=0; i<l; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            times.add(Integer.parseInt(st.nextToken()));
            directions.add(st.nextToken().charAt(0));
        }

        hx = hy = tx = ty = 1;
        dir = 0;
        board[hx][hy] = 2;
        snake.offer(new Point(hx, hy));

        move();

        System.out.println(answer);
    }
}