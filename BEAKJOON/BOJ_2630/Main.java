package BEAKJOON.BOJ_2630;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] paper;
    static int[] answer = new int[2];

    public static void dfs(int x, int y, int n) {
        if(n == 1) {
            answer[paper[x][y]]++;
            return;
        }
        int color = paper[x][y];
        int total = 0;
        for(int i=x; i<x+n; i++) {
            boolean diff = false;
            for(int j=y; j<y+n; j++) {
                if(paper[i][j] == 1) total++;
                if(color != paper[i][j]) {
                    dfs(x, y, n/2);
                    dfs(x, y+n/2, n/2);
                    dfs(x+n/2, y, n/2);
                    dfs(x+n/2, y+n/2, n/2);
                    diff = true;
                    break;
                }
            }
            if(diff) break;
        }
        if(total == n * n) {
            answer[1]++;
        }else if(total == 0) {
            answer[0]++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        paper = new int[n][n];
        StringTokenizer st;

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<n; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, n);

        System.out.println(answer[0]);
        System.out.println(answer[1]);
    }
}