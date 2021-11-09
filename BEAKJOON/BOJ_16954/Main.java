package BEAKJOON.BOJ_16954;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static final int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
    public static final int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};

    public static int n, answer;
    public static char[][] map;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = 8;
        answer = 0;
        map = new char[n][n];

        for(int i=0; i<n; i++) {
            String row = br.readLine();
            for(int j=0; j<n; j++) {
                map[i][j] = row.charAt(j);
            }
        }
    }

    public static boolean outOfRange(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= n;
    }

    public static void drop(char[][] maze) {

        for(int i=0; i<n; i++) {
            for(int j=n-1; j>0; j--) {
                maze[j][i] = maze[j-1][i];
            }
            maze[0][i] = '.';
        }
    }

    public static void escape(char[][] maze, int x, int y, int moves) {

        if(answer == 1) return;
        if(x == 0 && y == n - 1) {
            answer = 1;
            return;
        }
        if(moves >= 1) {
            drop(maze);
            if(maze[x][y] == '#') return;
        }

        boolean moved = false;
        for(int i=0; i<5; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(outOfRange(nx, ny)) continue;
            if(maze[nx][ny] == '#' || (nx > 0 && maze[nx-1][ny] == '#')) continue;

            char[][] nextStateOfMaze = new char[n][n];
            for(int j=0; j<n; j++) {
                for(int k=0; k<n; k++) {
                    nextStateOfMaze[j][k] = maze[j][k];
                }
            }

            moved = true;
            escape(nextStateOfMaze, nx, ny, moves + 1);
        }
        if(!moved) escape(maze, x, y, moves + 1);
    }

    public static void movingMaze() {

        escape(map, n - 1, 0, 0);

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {

        input();
        movingMaze();
    }
}