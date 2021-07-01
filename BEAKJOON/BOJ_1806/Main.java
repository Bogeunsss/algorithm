package BEAKJOON.BOJ_1806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int low = 0, high = 0;
        int sum = 0;
        int answer = Integer.MAX_VALUE;
        while(true) {
            if(sum >= S) {
                sum -= arr[low++];
                answer = Math.min(answer, high - low + 1);
            }else if(high == N) break;
            else sum += arr[high++];
        }
        System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
    }
}