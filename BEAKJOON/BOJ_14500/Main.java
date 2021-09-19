package BEAKJOON.BOJ_14500;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int n, m;
    public static int[][] paper;

    public static int check(int[][] block, int x, int y) {
        int ret = 0;

        for(int i=0; i<block.length; i++) {
            for(int j=0; j<block[i].length; j++) {
                if(x + i >= n || y + j >= m) return -1;
                if(block[i][j] == 1) ret += paper[x + i][y + j];
            }
        }

        return ret;
    }

    public static int[][] rotate(int[][] block) {
        int r = block.length;
        int c = block[0].length;
        int[][] ret = new int[c][r];

        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                ret[c-j-1][i] = block[i][j];
            }
        }

        return ret;
    }

    public static int[][] symmetric(int[][] block) {
        int r = block.length;
        int c = block[0].length;
        int[][] ret = new int[r][c];

        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                ret[i][j] = block[i][c-j-1];
            }
        }

        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        paper = new int[n][m];

        int answer = 0;
        int[][][] blocks = {
                {{1, 1, 1, 1}},
                {{1, 1}, {1, 1}},
                {{1, 0}, {1, 0}, {1, 1}},
                {{1, 0}, {1, 1}, {0, 1}},
                {{1, 1, 1}, {0, 1, 0}}};

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            for(int j=0; j<m; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                for(int[][] block : blocks) {
                    int r = 4;

                    while(r-- > 0) {
                        answer = Math.max(answer, check(block, i, j));
                        block = symmetric(block);

                        answer = Math.max(answer, check(block, i, j));
                        block = symmetric(block);
                        block = rotate(block);
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
