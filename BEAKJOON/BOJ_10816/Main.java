package BEAKJOON.BOJ_10816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static int[] arr;

    public static int lower_bound(int t) {
        int low = 0, high = arr.length - 1, mid;
        while(low < high) {
            mid = (high + low) / 2;
            if(arr[mid] >= t) high = mid;
            else low = mid + 1;
        }
        return high;
    }

    public static int upper_bound(int t) {
        int low = 0, high = arr.length - 1, mid;
        while(low < high) {
            mid = (high + low) / 2;
            if(arr[mid] > t) high = mid;
            else low = mid + 1;
        }
        if(arr[high] == t) high++;
        return high;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<M; i++) {
            int t = Integer.parseInt(st.nextToken());
            int lo = lower_bound(t);
            int up = upper_bound(t);
            sb.append(up - lo).append(" ");
        }
        System.out.println(sb);
    }
}