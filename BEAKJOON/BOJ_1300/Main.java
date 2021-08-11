package BEAKJOON.BOJ_1300;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        long left = 1, right = k;
        long answer = 0;
        long mid;

        while(left <= right) {
            mid = (left + right) / 2;
            long count = 0;

            for(int i=1; i<=N; i++) {
                count += Math.min(mid / i, N);
            }
            if(count < k) {
                left = mid + 1;
            }else {
                answer = mid;
                right = mid - 1;
            }
        }
        System.out.println(answer);
    }
}