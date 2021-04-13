package PROGRAMMERS.level_1._12940;

public class Solution {
    public int GCD(int n, int m) {
        if(n == 0) return m;
        return GCD(m % n, n);
    }
    public int LCM(int n, int m) {
        return Math.abs(n * m) / GCD(n, m);
    }
    public int[] solution(int n, int m) {
        if(n > m) {
            int temp = n;
            n = m;
            m = temp;
        }
        int gcd = GCD(n, m);
        int lcm = LCM(n, m);

        return new int[]{gcd, lcm};
    }
}
