package BEAKJOON.BOJ_13305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] roads = new long[N-1];
        long[] cities = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N-1; i++) {
            roads[i] = Long.parseLong(st.nextToken());
        }
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++) {
            cities[i] = Integer.parseInt(st.nextToken());
        }

        long answer = 0;
        long cost = cities[0];
        for(int i=0; i<N-1; i++) {
            cost = Math.min(cost, cities[i]);
            answer += cost * roads[i];
        }
        System.out.println(answer);
    }
}