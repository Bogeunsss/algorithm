package BEAKJOON.BOJ_1051;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] square = new int[n][m];
        int answer = 1;

        for(int i=0; i<n; i++) {
            String s = br.readLine();

            for(int j=0; j<m; j++) {
                square[i][j] = s.charAt(j) - '0';
            }
        }
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                for(int k=1; k<Math.min(n,m); k++) {
                    if(i + k < n && j + k < m) {
                        if(square[i+k][j] == square[i][j] && square[i+k][j+k] == square[i][j] && square[i][j+k] == square[i][j]) {
                            answer = Math.max(answer, k + 1);
                        }
                    }
                }
            }
        }

        System.out.println(answer * answer);
    }
}