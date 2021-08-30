package PROGRAMMERS.level_4._12929;

public class Solution {

    private long catalan(int n) {
        if(n <= 1) return 1;

        long x = 1, y = 1;
        for(int i=2; i<=n*2; i++) {
            if(i < n + 1) y *= i;
            else if(i > n + 1) x *= i;
        }
        return x / y;
    }

    public int solution(int n) {
        return (int) catalan(n);
    }
}
