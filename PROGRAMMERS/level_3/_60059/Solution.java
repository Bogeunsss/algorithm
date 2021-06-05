package PROGRAMMERS.level_3._60059;

public class Solution {

    public static int[][] rotate(int[][] key) {
        int[][] copy = new int[key.length][key[0].length];
        for(int i=0; i<key.length; i++) {
            for(int j=0; j<key[i].length; j++) {
                copy[j][key.length-i-1] = key[i][j];
            }
        }
        return copy;
    }

    public static int[][] copyBoard(int[][] board) {
        int[][] copy = new int[board.length][board.length];
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board.length; j++) {
                copy[i][j] = board[i][j];
            }
        }
        return copy;
    }

    public boolean solution(int[][] key, int[][] lock) {
        int[][] board = new int[(key.length-1)*2+ lock.length][(key.length-1)*2+ lock.length];
        boolean answer = false;
        int x = key.length - 1;
        int y = key.length - 1;

        for(int i=0; i<lock.length; i++) {
            for(int j=0; j<lock[i].length; j++) {
                board[i+x][j+y] = lock[i][j];
            }
        }

        int[][] copy = copyBoard(board);
        for(int i=0; i<board.length-key.length+1; i++) {
            for(int j=0; j<board.length-key.length+1; j++) {
                for(int rot=0; rot<4; rot++) {
                    for(int k=0; k<key.length; k++) {
                        for(int l=0; l<key.length; l++) {
                            copy[i+k][j+l] += key[k][l];
                        }
                    }
                    answer = true;
                    int cnt = 0;
                    for(int k=0; k<key.length; k++) {
                        for(int l=0; l<key.length; l++) {
                            if(copy[k+x][l+y] != 1) {
                                answer = false;
                                break;
                            }else {
                                cnt++;
                            }
                        }
                        if(!answer) break;
                    }
                    if(cnt == key.length * key.length) return true;
                    key = rotate(key);
                    copy = copyBoard(board);
                }
            }
        }
        return answer;
    }
}
