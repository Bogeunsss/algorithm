package BEAKJOON.BOJ_3273;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int left = 0, right = n - 1;
        int answer = 0;

        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        while(left < right) {
            if(arr[left] + arr[right] >= x) {
                if(arr[left] + arr[right] == x) answer++;
                right--;
            }else {
                left++;
            }
        }
        System.out.println(answer);
    }
}