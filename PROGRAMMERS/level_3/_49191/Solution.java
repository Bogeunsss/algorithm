package PROGRAMMERS.level_3._49191;

public class Solution {
    static int[][] matches;

    public static void floydWarshall(int n) {
        for(int k=1; k<=n; k++) {
            for(int i=1; i<=n; i++) {
                for(int j=1; j<=n; j++) {
                    if(matches[i][j] == Integer.MAX_VALUE) {
                        if(matches[i][k] == 1 && matches[k][j] == 1) matches[i][j] = 1;
                        if(matches[i][k] == -1 && matches[k][j] == -1) matches[i][j] = -1;
                    }
                }
            }
        }
    }

    public int solution(int n, int[][] results) {
        matches = new int[n+1][n+1];
        int answer = 0;

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                matches[i][j] = Integer.MAX_VALUE;
            }
        }
        for(int[] result : results) {
            matches[result[0]][result[1]] = 1;
            matches[result[1]][result[0]] = -1;
        }

        floydWarshall(n);

        for(int i=1; i<=n; i++) {
            boolean isInf = false;
            for(int j=1; j<=n; j++) {
                if(i != j && matches[i][j] == Integer.MAX_VALUE) {
                    isInf = true;
                    break;
                }
            }
            answer += isInf ? 0 : 1;
        }
        return answer;
    }
}