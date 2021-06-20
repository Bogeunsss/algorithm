package BEAKJOON.BOJ_1018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int paintBoard(char[][] board, int x, int y) {
        char color = 'W';
        int caseW = 0, caseB = 0;

        for(int i=x; i<x+8; i++) {
            for(int j=y; j<y+8; j++) {
                if(board[i][j] != color) caseW++;
                if(j == y+7) break;
                color = color == 'W' ? 'B' : 'W';
            }
        }
        color = 'B';
        for(int i=x; i<x+8; i++) {
            for(int j=y; j<y+8; j++) {
                if(board[i][j] != color) caseB++;
                if(j == y+7) break;
                color = color == 'W' ? 'B' : 'W';
            }
        }
        return Math.min(caseW, caseB);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] board = new char[N][M];
        int answer = Integer.MAX_VALUE;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            board[i] = st.nextToken().toCharArray();
        }
        for(int i=0; i<N-7; i++) {
            for(int j=0; j<M-7; j++) {
                answer = Math.min(answer, paintBoard(board, i, j));
            }
        }
        System.out.println(answer);
    }
}