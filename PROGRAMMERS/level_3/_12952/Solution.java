package PROGRAMMERS.level_3._12952;

public class Solution {
    public static int[] board;
    public static int N, answer;

    public static boolean possibility(int col) {
        for(int i=0; i<col; i++) {
            if(board[col] == board[i]) return false;
            else if(Math.abs(col - i) == Math.abs(board[col] - board[i])) return false;
        }
        return true;
    }

    public static void nQueen(int depth) {
        if(depth == N) {
            answer++;
            return;
        }
        for(int i=0; i<N; i++) {
            board[depth] = i;
            if(possibility(depth)) {
                nQueen(depth + 1);
            }
        }
    }

    public static int solution(int n) {
        board = new int[n];
        answer = 0;
        N = n;

        nQueen(0);

        return answer;
    }
}