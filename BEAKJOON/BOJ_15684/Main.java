package BEAKJOON.BOJ_15684;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static boolean[][] ladder = new boolean[30][11];
    public static int n, m, h;
    public static boolean found = false;

    public static boolean verify(int src) {
        int row = src;

        for(int i=0; i<h; i++) {
            if(ladder[i][row]) row++;
            else if(row > 1 && ladder[i][row-1]) row--;
        }
        return row == src;
    }

    public static void install(int y, int depth, int added) {
        if(found) return;
        if(added == depth) {
            boolean available = true;

            for(int i=1; i<=n; i++) {
                if(!verify(i)) {
                    available = false;
                    break;
                }
            }

            if(available) found = true;
            return;
        }

        for(int i=y; i<h; i++) {
            for(int j=1; j<n; j++) {
                if(!ladder[i][j-1] && !ladder[i][j] && !ladder[i][j+1]) {
                    ladder[i][j] = true;
                    install(i, depth + 1, added);
                    ladder[i][j] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        int answer = -1;

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            ladder[a-1][b] = true;
        }
        for(int i=0; i<=3; i++) {
            install(0, 0, i);

            if(found) {
                answer = i;
                break;
            }
        }

        System.out.println(answer);
    }
}