package BEAKJOON.BOJ_2740;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] A;
    static int[][] B;
    static int[][] C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        A = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        B = new int[M][K];

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<K; j++) {
                B[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        C = new int[N][K];

        for(int i=0; i<N; i++) {
            for(int j=0; j<K; j++) {
                int sum = 0;
                for(int k=0; k<M; k++) {
                    sum += A[i][k] * B[k][j];
                }
                sb.append(sum).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}