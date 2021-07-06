package BEAKJOON.BOJ_1920;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static int[] arr;
    public static int[] targets;
    public static int N, M;

    public static int binarySearch(int low, int high, int target) {
        while(low <= high) {
            int mid = (high + low) / 2;

            if(arr[mid] == target) return 1;
            else if(arr[mid] > target) high = mid - 1;
            else low = mid + 1;
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());
        targets = new int[M];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<M; i++) targets[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        for(int target : targets) {
            int answer = binarySearch(0, N-1, target);
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}