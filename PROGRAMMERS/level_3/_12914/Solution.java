package PROGRAMMERS.level_3._12914;

public class Solution {
    public static long[] memo = new long[2001];

    public static long jump(int n) {
        if(n <= 1) return 1;
        if(memo[n] != 0L) return memo[n];
        return memo[n] = (jump(n - 1) + jump(n - 2)) % 1234567;
    }

    public static long solution(int n) {
        return jump(n);
    }
}
