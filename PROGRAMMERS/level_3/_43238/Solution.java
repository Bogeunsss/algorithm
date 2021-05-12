package PROGRAMMERS.level_3._43238;

import java.util.*;

public class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long low = 1, high = n * (long) times[times.length-1];
        long answer = high;

        while(low <= high) {
            long mid = (high + low) / 2;
            long process = 0;
            for(int i=0; i<times.length; i++) {
                process += mid / times[i];
                if(process > n) break;
            }
            if(process < n) low = mid + 1;
            else {
                answer = Math.min(answer, mid);
                high = mid - 1;
            }
        }
        return answer;
    }
}