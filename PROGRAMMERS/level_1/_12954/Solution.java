package PROGRAMMERS.level_1._12954;

public class Solution {
    public long[] solution(int x, int n) {
        long[] answer = new long[n];
        long unit = x;
        int index = 0;

        while(index < n) {
            answer[index++] = unit;
            unit += x;
        }
        return answer;
    }
}
