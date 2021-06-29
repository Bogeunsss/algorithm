package BEAKJOON.BOJ_1992;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] video;
    static StringBuilder answer = new StringBuilder();

    public static void quadTree(int x, int y, int n) {
        if(n == 1) {
            answer.append(video[x][y]);
            return;
        }
        int dot = 0;
        boolean impossible = false;
        for(int i=x; i<x+n; i++) {
            for(int j=y; j<y+n; j++) {
                if(video[i][j] == 1) dot++;
                if(video[x][y] != video[i][j]) {
                    answer.append("(");
                    quadTree(x, y, n/2);
                    quadTree(x, y+n/2, n/2);
                    quadTree(x+n/2, y, n/2);
                    quadTree(x+n/2, y+n/2, n/2);
                    answer.append(")");
                    impossible = true;
                    break;
                }
            }
            if(impossible) break;
        }
        if(dot == n * n) answer.append(1);
        else if(dot == 0) answer.append(0);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        video = new int[N][N];

        for(int i=0; i<N; i++) {
            String temp = br.readLine();
            for(int j=0; j<N; j++) {
                video[i][j] = temp.charAt(j) - '0';
            }
        }

        quadTree(0, 0, N);

        System.out.println(answer);
    }
}