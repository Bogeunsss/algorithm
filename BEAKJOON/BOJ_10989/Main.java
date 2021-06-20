package BEAKJOON.BOJ_10989;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final int MAX = 10001;
    public static int[] countingSort(int[] arr) {
        int[] ret = new int[arr.length];
        int[] count = new int[MAX];
        int idx = 0;

        for(int i=0; i<arr.length; i++) {
            count[arr[i]]++;
        }
        for(int i=1; i<MAX; i++) {
            if(count[i] != 0) {
                for(int j=0; j<count[i]; j++) {
                    ret[idx++] = i;
                }
            }
        }

        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(br.readLine());
        arr = countingSort(arr);
        for(int i=0; i<N; i++) sb.append(arr[i]).append("\n");
        System.out.println(sb);
    }
}