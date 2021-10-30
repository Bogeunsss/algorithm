package BEAKJOON.BOJ_17136;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int[][] map;
    public static int[] coloredPaper;
    public static int n, answer;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = 10;
        answer = Integer.MAX_VALUE;
        map = new int[n][n];
        coloredPaper = new int[6];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void attach(int x, int y, int sum) {

        if(y == n) {
            attach(x + 1, 0, sum);
            return;
        }
        if(x == n) {
            answer = Math.min(answer, sum);
            return;
        }
        if(map[x][y] == 0) {
            attach(x, y + 1, sum);
            return;
        }

        for(int k=5; k>=1; k--) {
            if(coloredPaper[k] >= 5 || x + k > n || y + k > n) continue;

            boolean isSquare = true;

            loop:
            for(int i=0; i<k; i++) {
                for(int j=0; j<k; j++) {
                    if(map[x+i][y+j] == 0) {
                        isSquare = false;
                        break loop;
                    }
                }
            }

            if(!isSquare) continue;
            for(int i=0; i<k; i++) {
                for(int j=0; j<k; j++) {
                    map[x+i][y+j] = 0;
                }
            }

            coloredPaper[k]++;

            attach(x, y + k, sum + 1);

            coloredPaper[k]--;
            for(int i=0; i<k; i++) {
                for(int j=0; j<k; j++) {
                    map[x+i][y+j] = 1;
                }
            }
        }
    }

    public static void attachColoredPaper() {

         attach(0, 0, 0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    public static void main(String[] args) throws IOException {

        input();
        attachColoredPaper();

    }
}