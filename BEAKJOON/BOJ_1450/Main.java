package BEAKJOON.BOJ_1450;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static int N, C, answer, idx;
    public static int a = 0, b = 0;
    public static int[] goods;
    public static int[] sumA = new int[33000];
    public static int[] sumB = new int[33000];

    public static void left(int i, int sum) {
        if(sum > C) return;
        if(i == N / 2) {
            sumA[a++] = sum;
            return;
        }
        left(i+1, sum+goods[i]);
        left( i+1, sum);
    }

    public static void right(int i, int sum) {
        if(sum > C) return;
        if(i == N) {
            sumB[b++] = sum;
            return;
        }
        right(i + 1, sum + goods[i]);
        right(i + 1, sum);
    }

    public static void binarySearch(int low, int high, long value) {
        if(low > high) return;

        int mid = (high + low) / 2;
        if(sumB[mid] + value <= C) {
            idx = mid;
            binarySearch(mid + 1, high, value);
        }else {
            binarySearch(low, mid-1, value);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        goods = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++) goods[i] = Integer.parseInt(st.nextToken());

        left(0, 0);
        right(N/2, 0);
        Arrays.sort(sumB, 0, b);

        answer = 0;
        for(int i=0; i<a; i++) {
            idx = -1;
            binarySearch(0, b-1, sumA[i]);
            answer += idx + 1;
        }
        System.out.println(answer);
    }
}