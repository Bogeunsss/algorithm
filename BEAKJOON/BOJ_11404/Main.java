package BEAKJOON.BOJ_11404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static final int INF = 10000001;

    public static int n, m;
    public static int[][] city;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        city = new int[n+1][n+1];

        for(int i=0; i<=n; i++) {
            Arrays.fill(city[i], INF);
            city[i][i] = 0;
        }
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            city[a][b] = Math.min(city[a][b], c);
        }
    }

    public static void floydWarshall() {

        for(int k=1; k<=n; k++) {
            for(int i=1; i<=n; i++) {
                for(int j=1; j<=n; j++) {
                    if(city[i][j] > city[i][k] + city[k][j]) {
                        city[i][j] = city[i][k] + city[k][j];
                    }
                }
            }
        }
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                if(city[i][j] == INF) System.out.print(0 + " ");
                else System.out.print(city[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {

        input();
        floydWarshall();
    }
}