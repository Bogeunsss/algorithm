package PROGRAMMERS.level_1._12934;

public class Solution {
    public long solution(long n) {
        long answer = (long) Math.sqrt(n);

        if(Math.pow(answer, 2) == n) answer = (long) Math.pow(answer+1, 2);
        else answer = -1;

        return answer;
    }
}
