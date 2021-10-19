package BEAKJOON.BOJ_2239;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static final int n = 9;
    public static int[][] map = new int[n][n];
    public static boolean[][] row = new boolean[n][n+1];
    public static boolean[][] col = new boolean[n][n+1];
    public static boolean[][] square = new boolean[n][n+1];
    public static boolean fin = false;

    public static void sudoku(int depth) {
        if(depth >= 81) {

            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }
            System.exit(0);

            return;
        }

        int x = depth / n;
        int y = depth % n;

        if(map[x][y] == 0) {
            for(int i=1; i<=n; i++) {
                if(!row[x][i] && !col[y][i] && !square[(x/3*3) + (y/3)][i]) {

                    row[x][i] = col[y][i] = square[(x/3*3) + (y/3)][i] = true;
                    map[x][y] = i;
                    sudoku(depth + 1);
                    map[x][y] = 0;
                    row[x][i] = col[y][i] = square[(x/3*3) + (y/3)][i] = false;
                }
            }
        }else sudoku(depth + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<n; i++) {
            String s = br.readLine();
            for(int j=0; j<n; j++) {
                map[i][j] = s.charAt(j) - '0';

                if(map[i][j] > 0) {
                    row[i][map[i][j]] = true;
                    col[j][map[i][j]] = true;
                    square[(i/3)*3+(j/3)][map[i][j]] = true;
                }
            }
        }

        sudoku(0);
    }
}