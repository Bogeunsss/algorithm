package PROGRAMMERS.level_2._77485;

public class Solution {
    static int[][] board;
    static int min;

    public static void swapX(int i, int x, int y) {
        min = Math.min(min, Math.min(board[i][x], board[i][y]));

        int temp = board[i][x];
        board[i][x] = board[i][y];
        board[i][y] = temp;
    }

    public static void swapY(int i, int x, int y) {
        min = Math.min(min, Math.min(board[x][i], board[y][i]));

        int temp = board[x][i];
        board[x][i] = board[y][i];
        board[y][i] = temp;
    }

    public int[] solution(int rows, int columns, int[][] queries) {
        board = new int[rows+1][columns+1];
        int[] answer = new int[queries.length];
        int value = 1, index = 0;

        for(int i=1; i<=rows; i++) {
            for(int j=1; j<=columns; j++) {
                board[i][j] = value++;
            }
        }
        for(int[] query : queries) {
            int x1 = query[0];
            int y1 = query[1];
            int x2 = query[2];
            int y2 = query[3];
            min = Integer.MAX_VALUE;

            for(int i=y2; i>y1; i--) {
                swapX(x1, i, i-1);
            }
            for(int i=x1; i<x2; i++) {
                swapY(y1, i, i+1);
            }
            for(int i=y1; i<y2; i++) {
                swapX(x2, i, i+1);
            }
            for(int i=x2; i>x1+1; i--) {
                swapY(y2, i, i-1);
            }
            answer[index++] = min;
            for(int i=0; i<=rows; i++) {
                for(int j=0; j<=columns; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        return answer;
    }
}
