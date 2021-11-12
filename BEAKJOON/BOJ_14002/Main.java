package BEAKJOON.BOJ_14002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static int n;
    public static int[] arr;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static void theLongestIncreasingSubsequence() {

        List<List<Integer>> LIS = new ArrayList<>();
        List<Integer> answer = new ArrayList<>();
        int[] dp = new int[n];

        for(int i=0; i<n; i++) {
            dp[i] = 1;
            LIS.add(new ArrayList<>());
            LIS.get(i).add(arr[i]);

            for(int j=0; j<i; j++) {
                if(arr[i] > arr[j] && dp[i] < dp[j] + 1) {
                    LIS.set(i, new ArrayList<>(LIS.get(j)));
                    LIS.get(i).add(arr[i]);
                    dp[i] = dp[j] + 1;
                }
            }
            if(answer.size() < LIS.get(i).size()) {
                answer = new ArrayList<>(LIS.get(i));
            }
        }

        System.out.println(answer.size());
        for(int i=0; i<answer.size(); i++) {
            System.out.print(answer.get(i) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {

        input();
        theLongestIncreasingSubsequence();
    }
}