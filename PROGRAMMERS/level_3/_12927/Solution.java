package PROGRAMMERS.level_3._12927;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for(int i=0; i<works.length; i++) pq.offer(works[i]);
        for(int i=0; i<n; i++) {
            if(!pq.isEmpty() && pq.peek() > 0) {
                int process = pq.poll();
                pq.offer(--process);
            }
        }
        while(!pq.isEmpty()) {
            int work = pq.poll();
            answer += (long) work * work;
        }

        return answer;
    }
}
