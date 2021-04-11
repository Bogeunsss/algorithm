package PROGRAMMERS._12912;

public class Solution {
    public long sum(int a, int b) {
        return (long) (b - a + 1) *(b+a)/2;
    }
    public long solution(int a, int b) {
        return sum(Math.min(a, b), Math.max(a, b));
    }
}
