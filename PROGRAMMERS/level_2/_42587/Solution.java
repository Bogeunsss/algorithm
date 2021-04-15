package PROGRAMMERS.level_2._42587;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public static class Work {
        int priority;
        int location;

        public Work(int priority, int location) {
            this.priority = priority;
            this.location = location;
        }
    }
    public int solution(int[] priorities, int location) {
        Queue<Work> works = new LinkedList<Work>();
        int[] finished = new int[priorities.length];
        int index = 0;

        for(int i=0; i<priorities.length; i++) {
            works.offer(new Work(priorities[i], i));
        }
        Arrays.sort(priorities);
        while(!works.isEmpty()) {
            Work work = works.poll();
            if(work.priority == priorities[priorities.length-index-1]) {
                if(work.location == location) return index+1;
                index++;
            }else {
                works.offer(work);
            }
        }
        return priorities.length;
    }
}
