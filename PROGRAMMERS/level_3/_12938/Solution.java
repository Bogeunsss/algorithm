package PROGRAMMERS.level_3._12938;

import java.util.Arrays;

public class Solution {
    public static int[] solution(int n, int s) {
        int[] answer = new int[n];

        if(n > s) return new int[]{-1};
        for(int i=0; i<n; i++) answer[i] = s / n;
        for(int i=0; i<s%n; i++) answer[i]++;
        Arrays.sort(answer);

        return answer;
    }
}
