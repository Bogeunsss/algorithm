package PROGRAMMERS.level_3._42627;

import java.util.*;

public class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        int time = 0;
        int index = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        while(index < jobs.length || !pq.isEmpty()) {
            while(index < jobs.length && jobs[index][0] <= time) {
                pq.offer(jobs[index++]);
            }
            if(pq.isEmpty()) time = jobs[index][0];
            else {
                int[] job = pq.poll();
                time += job[1];
                answer += time - job[0];
            }
        }
        return answer / jobs.length;
    }
}
