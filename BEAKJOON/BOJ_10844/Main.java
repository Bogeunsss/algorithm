package BEAKJOON.BOJ_10844;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final long MOD = 1000000000;
    static long[][] dp;

    public static long easyStairs(int digit, int count) {
        if(digit == 1) return dp[digit][count];
        if(dp[digit][count] == 0) {
            if(count == 0) {
                dp[digit][count] = easyStairs(digit-1, 1);
            }else if(count == 9) {
                dp[digit][count] = easyStairs(digit-1, 8);
            }else {
                dp[digit][count] = easyStairs(digit-1, count-1) + easyStairs(digit-1, count+1);
            }
        }
        return dp[digit][count] % MOD;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long answer = 0;

        dp = new long[N+1][10];
        for(int i=0; i<=9; i++) dp[1][i] = 1;
        for(int i=1; i<=9; i++) answer += easyStairs(N, i);
        System.out.println(answer % MOD);
    }
}

/*
1
10 12
101 121 123
1010 1012 1210 1212 1232 1234
10101 10121 10123 12101 12121 12123 12321 12323 12343 12345

2
21 23
210 212 232 234
2101 2121 2123 2321 2323 2343 2345
21010 21012 21210 21212 21232 21234 23210 23212 23232 23234 23432 23434 23454 23456
1 2 4 7 14
 */