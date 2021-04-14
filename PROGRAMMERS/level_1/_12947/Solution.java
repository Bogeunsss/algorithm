package PROGRAMMERS.level_1._12947;

public class Solution {
    public boolean solution(int x) {
        int sum = 0;
        int _x = x;

        while(_x > 0) {
            sum += _x % 10;
            _x /= 10;
        }
        return x % sum == 0;
    }
}
