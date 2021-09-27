package BEAKJOON.BOJ_15685;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static final int[] dx = {0, -1, 0, 1};
    public static final int[] dy = {1, 0, -1, 0};
    public static final int MAX = 101;

    public static boolean[][] board;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int answer = 0;

        board = new boolean[MAX][MAX];

        List<Integer> list;
        StringTokenizer st;
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            list = new ArrayList<>();
            list.add(d);

            for(int j=0; j<g; j++) {
                int size = list.size();

                for(int k=size-1; k>=0; k--) {
                    list.add((list.get(k) + 1) % 4);
                }
            }

            board[x][y] = true;

            for(int j=0; j<list.size(); j++) {
                d = list.get(j);
                x += dx[d];
                y += dy[d];

                if(0 <= x && x < MAX && 0 <= y && y < MAX) {
                    board[x][y] = true;
                }
            }
        }

        for(int i=0; i<MAX-1; i++) {
            for(int j=0; j<MAX-1; j++) {
                if(board[i][j] && board[i][j+1] && board[i+1][j] && board[i+1][j+1]) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }
}