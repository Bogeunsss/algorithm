package BEAKJOON.BOJ_2110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] houses = new int[N];

        for(int i=0; i<N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(houses);

        int left = 1;
        int right = houses[N-1] - houses[0];
        int mid, distance, answer = 0;

        while(left <= right) {
            mid = (left + right) / 2;

            int start = houses[0];
            int count = 1;

            for(int i=0; i<N; i++) {
                distance = houses[i] - start;
                if(distance >= mid) {
                    count++;
                    start = houses[i];
                }
            }
            if(count >= C) {
                answer = mid;
                left = mid + 1;
            }else right = mid - 1;
        }
        System.out.println(answer);
    }
}