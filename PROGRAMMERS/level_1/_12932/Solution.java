package PROGRAMMERS.level_1._12932;

public class Solution {
    public int[] solution(long n) {
        int[] answer = new int[Long.toString(n).length()];
        int index = 0;

        while(n > 0) {
            answer[index++] = (int) (n % 10);
            n /= 10;
        }
        return answer;
    }
}
