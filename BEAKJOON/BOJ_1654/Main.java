package BEAKJOON.BOJ_1654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static int[] cables;
    public static int K, N;

    public static long binarySearch(long low, long high) {
        while(low <= high) {
            long mid = (high + low) / 2;
            long sum = 0;

            for(int i=0; i<K; i++) {
                sum += cables[i] / mid;
            }
            if(sum >= N) low = mid + 1;
            else high = mid - 1;
        }
        return high;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        cables = new int[K];

        for(int i=0; i<K; i++) {
            cables[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(cables);

        System.out.println(binarySearch(1, cables[K-1]));
    }
}