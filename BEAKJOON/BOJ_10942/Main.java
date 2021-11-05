package BEAKJOON.BOJ_10942;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int n, m;
    public static int[] arr;
    public static int[][] questions;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n+1];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i<=n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        questions = new int[m][2];

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            questions[i][0] = Integer.parseInt(st.nextToken());
            questions[i][1] = Integer.parseInt(st.nextToken());
        }
    }

    public static void verify() {

        StringBuilder answer = new StringBuilder();

        for(int i=0; i<m; i++) {
            int left = questions[i][0];
            int right = questions[i][1];
            boolean isPalindrome = true;

            while(left < right) {
                if(arr[left] != arr[right]) {
                    isPalindrome = false;
                    break;
                }
                left++;
                right--;
            }

            answer.append(isPalindrome ? 1 : 0).append("\n");
        }

        System.out.print(answer);
    }

    public static void main(String[] args) throws IOException {

        input();
        verify();
    }
}