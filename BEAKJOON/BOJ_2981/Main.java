package BEAKJOON.BOJ_2981;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static int gcd(int x, int y) {
        if(y == 0) return x;
        if(x % y == 0) return y;
        return gcd(y, x % y);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> answer = new ArrayList<>();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int m = arr[1] - arr[0];
        for(int i=2; i<N; i++) {
            m = gcd(m, arr[i] - arr[i-1]);
        }

        for(int i=2; i*i<=m; i++) {
            if(m % i == 0) {
                answer.add(i);
                if(i*i < m) answer.add(m / i);
            }
        }
        answer.add(m);

        Collections.sort(answer);

        for (Integer ans : answer) {
            System.out.print(ans + " ");
        }
    }
}
