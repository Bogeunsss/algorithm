package PROGRAMMERS.level_2._17679;

public class Solution {
    static boolean[][] visited;

    public static boolean complete(char[][] blocks, int m, int n) {
        for(int i=0; i<m-1; i++) {
            for(int j=0; j<n-1; j++) {
                if(blocks[i][j] != '-') {
                    if(check(blocks, i, j)) return false;
                }
            }
        }
        return true;
    }

    public static boolean check(char[][] blocks, int x, int y) {
        char standard = blocks[x][y];
        for(int i=x; i<=x+1; i++) {
            for(int j=y; j<=y+1; j++) {
                if(blocks[i][j] != standard) return false;
            }
        }
        return true;
    }

    public static void explode(char[][] blocks, int m, int n) {
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(visited[i][j]) blocks[i][j] = '-';
            }
        }
    }

    public static void move(char[][] blocks, int m, int n) {
        for(int i=0; i<n; i++) {
            for(int j=m-1; j>0; j--) {
                if(blocks[j][i] == '-') {
                    for(int k=j; k>=0; k--) {
                        if(blocks[k][i] != '-') {
                            char temp = blocks[j][i];
                            blocks[j][i] = blocks[k][i];
                            blocks[k][i] = temp;
                            break;
                        }
                    }
                }
            }
        }
    }

    public int solution(int m, int n, String[] board) {
        char[][] blocks = new char[m][n];
        int answer = 0;

        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length(); j++) {
                blocks[i][j] = board[i].charAt(j);
            }
        }

        while(!complete(blocks, m, n)) {
            visited = new boolean[m][n];
            for(int i=0; i<m-1; i++) {
                for(int j=0; j<n-1; j++) {
                    if(blocks[i][j] != '-' && check(blocks, i, j)) {
                        for(int k=i; k<=i+1; k++) {
                            for(int l=j; l<=j+1; l++) {
                                if(!visited[k][l]) answer++;
                                visited[k][l] = true;
                            }
                        }
                    }
                }
            }
            explode(blocks, m, n);
            move(blocks, m, n);
        }
        return answer;
    }
}
