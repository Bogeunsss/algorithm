package PROGRAMMERS.level_3._42884;

import java.util.*;

public class Solution {
    public int solution(int[][] routes) {
        int camera = -30001;
        int answer = 0;

        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        for(int[] route : routes) {
            if(camera < route[0]) {
                camera = route[1];
                answer++;
            }
        }
        return answer;
    }
}