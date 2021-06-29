package BEAKJOON.BOJ_1780;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] paper;
    static int[] answer = new int[3];

    public static void quadTree(int x, int y, int n) {
        if(n == 1) {
            answer[paper[x][y]+1]++;
            return;
        }

        boolean impossible = false;
        for(int i=x; i<x+n; i++) {
            for(int j=y; j<y+n; j++) {
                if(paper[x][y] != paper[i][j]) {
                    for(int k=0; k<3; k++) {
                        for(int l=0; l<3; l++) {
                            quadTree(x+k*n/3, y+l*n/3, n/3);
                        }
                    }
                    impossible = true;
                    break;
                }
            }
            if(impossible) break;
        }
        if(!impossible) {
            answer[paper[x][y]+1]++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        paper = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        quadTree(0, 0, N);

        for(int i=0; i<3; i++) System.out.println(answer[i]);
    }
}