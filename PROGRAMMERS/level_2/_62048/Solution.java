package PROGRAMMERS.level_2._62048;

public class Solution {
    public static int gcd(int x, int y) {
        return x % y == 0 ? y : gcd(y, x % y);
    }

    public long solution(int w, int h) {
        long answer = (long) w * h;
        long damaged = w + h - gcd(w, h);

        return answer - damaged;
    }
}
