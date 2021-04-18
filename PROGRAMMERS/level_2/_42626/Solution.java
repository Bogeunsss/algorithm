package PROGRAMMERS.level_2._42626;

import java.util.PriorityQueue;

public class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> Q = new PriorityQueue<>();
        int answer = 0;

        for(int scv : scoville) Q.offer(scv);

        while(Q.size() >= 2 && Q.peek() < K) {
            Q.offer(Q.poll() + Q.poll() * 2);
            answer++;
        }
        if(Q.peek() < K) answer = -1;

        return answer;
    }
}
