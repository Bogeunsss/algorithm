package BEAKJOON.BOJ_2186;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static final int[] dx = {-1, 1, 0, 0};
    public static final int[] dy = {0, 0, -1, 1};

    public static int n, m, k;
    public static int[][][] dp;
    public static char[][] map;
    public static String word;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new char[n][m];

        for(int i=0; i<n; i++) {
            String row = br.readLine();
            for(int j=0; j<m; j++) {
                map[i][j] = row.charAt(j);
            }
        }

        word = br.readLine();
        dp = new int[n][m][word.length()];

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
    }

    public static int makeWord(int x, int y, int index) {

        if(index >= word.length()) return 1;
        if(dp[x][y][index] > -1) return dp[x][y][index];

        dp[x][y][index] = 0;
        for(int i=0; i<4; i++) {
            for(int j=1; j<=k; j++) {
                int nx = x + dx[i] * j;
                int ny = y + dy[i] * j;

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(map[nx][ny] == word.charAt(index)) {
                    dp[x][y][index] += makeWord(nx, ny, index + 1);
                }
            }
        }

        return dp[x][y][index];
    }

    public static void wordBoard() {

        int answer = 0;

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(map[i][j] == word.charAt(0)) {
                    answer += makeWord(i, j, 1);
                }
            }
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {

        input();
        wordBoard();
    }
}