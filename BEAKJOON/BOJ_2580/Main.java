package BEAKJOON.BOJ_2580;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] board;
    static int N = 9;

    public static void sudoku(int row, int col) {
        if(col == N) {
            sudoku(row+1, 0);
        }else {
            if(row == N) {
                for(int i=0; i<N; i++) {
                    for(int j=0; j<N; j++) {
                        System.out.print(board[i][j] + " ");
                    }
                    System.out.println();
                }
                System.exit(0);
            }
            if(board[row][col] == 0) {
                for(int i=1; i<=N; i++) {
                    if(isPossible(row, col, i)) {
                        board[row][col] = i;
                        sudoku(row, col+1);
                    }
                }
                board[row][col] = 0;
                return;
            }
            sudoku(row, col+1);
        }
    }

    public static boolean isPossible(int x, int y, int v) {
        int squareX = (x / 3) * 3;
        int squareY = (y / 3) * 3;

        for(int i=0; i<N; i++) {
            if(board[x][i] == v) return false;
        }

        for(int i=0; i<N; i++) {
            if(board[i][y] == v) return false;
        }

        for(int i=squareX; i<squareX+3; i++) {
            for(int j=squareY; j<squareY+3; j++) {
                if(board[i][j] == v) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new int[N][N];

        for(int i=0; i<9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sudoku(0, 0);
    }
}