package BEAKJOON.BOJ_2805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int[] trees;
    public static int N, M;

    public static int binarySearch(int low, int high) {
        while(low + 1 < high) {
            int mid = (high + low) / 2;
            if(check(mid)) {
                low = mid;
            }else {
                high = mid;
            }
        }
        return low;
    }

    public static boolean check(int h) {
        long sum = 0;
        long tree;

        for(int i=0; i<N; i++) {
            tree = trees[i];
            if(tree >= h) {
                sum += tree - h;
            }
        }
        return sum >= M;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        trees = new int[N];
        int max = 0;

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, trees[i]);
        }

        System.out.println(binarySearch(0, max));
    }
}