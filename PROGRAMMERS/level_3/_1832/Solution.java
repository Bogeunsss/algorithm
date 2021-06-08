package PROGRAMMERS.level_3._1832;

public class Solution {
    final static int MOD = 20170805;

    public int solution(int m, int n, int[][] cityMap) {
        int[][] row = new int[m+1][n+1];
        int[][] col = new int[m+1][n+1];

        row[0][0] = 1;
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(cityMap[i][j] == 0) {
                    row[i+1][j] = (row[i+1][j] + row[i][j] + col[i][j]) % MOD;
                    col[i][j+1] = (col[i][j+1] + row[i][j] + col[i][j]) % MOD;
                }else if(cityMap[i][j] == 2) {
                    row[i+1][j] = (row[i+1][j] + row[i][j]) % MOD;
                    col[i][j+1] = (col[i][j+1] + col[i][j]) % MOD;
                }
            }
        }

        return (row[m-1][n-1] + col[m-1][n-1]) % MOD;
    }
}
