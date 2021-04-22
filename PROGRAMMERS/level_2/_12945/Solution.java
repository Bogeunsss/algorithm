package PROGRAMMERS.level_2._12945;

public class Solution {
    public static int fibonacci(int n) {
        if(n <= 1) return n;
        int[] cache = new int[n+1];
        cache[1] = 1;

        for(int i=2; i<=n; i++) {
            cache[i] = cache[i-1] % 1234567 + cache[i-2] % 1234567;
        }
        return cache[n];
    }

    public int solution(int n) {
        int answer = fibonacci(n);
        return answer % 1234567;
    }
}
