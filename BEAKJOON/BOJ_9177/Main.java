package BEAKJOON.BOJ_9177;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int n;
    public static String[] first, second, third;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        first = new String[n];
        second = new String[n];
        third = new String[n];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            first[i] = st.nextToken();
            second[i] = st.nextToken();
            third[i] = st.nextToken();
        }
    }

    public static boolean mix(String A, String B, String C) {

        int left = A.length();
        int right = B.length();
        boolean[][] dp = new boolean[left+1][right+1];

        dp[0][0] = true;
        for(int i=1; i<=left; i++) dp[i][0] = A.charAt(i - 1) == C.charAt(i - 1) && dp[i - 1][0];
        for(int i=1; i<=right; i++) dp[0][i] = B.charAt(i - 1) == C.charAt(i - 1) && dp[0][i - 1];
        for(int i=1; i<=left; i++) {
            for(int j=1; j<=right; j++) {
                char a = A.charAt(i - 1), b = B.charAt(j - 1), c = C.charAt(i + j - 1);

                if(a != c && b != c) dp[i][j] = false;
                else if(a == c && b != c) dp[i][j] = dp[i-1][j];
                else if(a != c && b == c) dp[i][j] = dp[i][j-1];
                else dp[i][j] = dp[i-1][j] || dp[i][j-1];
            }
        }

        return dp[left][right];
    }

    public static void mixingWords() {

        StringBuilder answer = new StringBuilder();

        for(int i=0; i<n; i++) {
            answer.append("Data set ").append(i + 1).append(": ");
            answer.append(mix(first[i], second[i], third[i]) ? "yes" : "no").append("\n");
        }

        System.out.print(answer);
    }

    public static void main(String[] args) throws IOException {

        input();
        mixingWords();
    }
}