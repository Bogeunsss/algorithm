package PROGRAMMERS.level_1._12982;

import java.util.Arrays;

public class Solution {
    public int solution(int[] d, int budget) {
        int answer = 0;
        int sum = 0;

        Arrays.sort(d);
        for(int s: d) {
            sum += s;
            if(sum <= budget) answer++;
            else break;
        }
        return answer;
    }
}
