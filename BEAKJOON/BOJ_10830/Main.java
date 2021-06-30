package BEAKJOON.BOJ_10830;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] origin;

    public static int[][] pow(int[][] A, long B) {
        if(B == 1) return A;
        int[][] ret = pow(A, B/2);

        ret = multiply(ret, ret);
        if(B % 2 == 1) ret = multiply(ret, origin);
        return ret;
    }

    public static int[][] multiply(int[][] A, int[][] B) {
        int[][] ret = new int[N][N];

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                for(int k=0; k<N; k++) {
                    ret[i][j] += A[i][k] * B[k][j];
                    ret[i][j] %= 1000;
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        origin = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<N; j++) {
                origin[i][j] = Integer.parseInt(st.nextToken()) % 1000;
            }
        }

        int[][] answer = pow(origin, B);

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }
    }
}