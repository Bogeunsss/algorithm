package PROGRAMMERS.level_1._12943;

public class Solution {
    public int collatz(long num, int step) {
        if(num == 1) return step;
        if(step == 500) return -1;
        if(num % 2 == 0) return collatz(num/2, step+1);
        return collatz(num*3+1, step+1);
    }

    public int solution(int num) {
        return collatz(num, 0);
    }
}
