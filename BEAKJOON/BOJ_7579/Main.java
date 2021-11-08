package BEAKJOON.BOJ_7579;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int n, m, size;
    public static int[] memory, cost, dp;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        memory = new int[n];
        cost = new int[n];
        size = 0;

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<n; i++) memory[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<n; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            size += cost[i];
        }

        dp = new int[size+1];
    }

    public static void application() {

        for(int i=0; i<n; i++) {
            for(int j=size; j>=cost[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j-cost[i]] + memory[i]);
            }
        }
        for(int i=0; i<=size; i++) {
            if(dp[i] >= m) {
                System.out.println(i);
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        input();
        application();
    }
}