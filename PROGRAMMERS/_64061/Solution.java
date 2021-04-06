package PROGRAMMERS._64061;

import java.util.Stack;

public class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> stack = new Stack<Integer>();

        stack.push(0);
        for(int i=0; i<moves.length; i++) moves[i]--;

        for(int move : moves) {
            for(int crane=0; crane<board.length; crane++) {
                if(board[crane][move] != 0) {
                    if(board[crane][move] == stack.peek()) {
                        stack.pop();
                        answer += 2;
                    }else {
                        stack.push(board[crane][move]);
                    }
                    board[crane][move] = 0;
                    break;
                }
            }
        }

        return answer;
    }
}
