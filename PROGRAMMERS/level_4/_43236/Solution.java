package PROGRAMMERS.level_4._43236;

import java.util.Arrays;

public class Solution {

    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        int low = 1, high = distance;
        int mid;

        Arrays.sort(rocks);

        while(low <= high) {
            int prev = 0;
            int cnt = 0;
            mid = (high + low) / 2;

            for(int rock : rocks) {
                if(mid > rock - prev) cnt++;
                else prev = rock;
            }

            if(distance - prev < mid) cnt++;

            if(cnt <= n) {
                low = mid + 1;
                answer = Math.max(answer, mid);
            }else high = mid - 1;
        }

        return answer;
    }
}