package PROGRAMMERS.level_3._42628;

import java.util.*;

public class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> ordered = new PriorityQueue<>((o1, o2) -> o1 - o2);
        PriorityQueue<Integer> reversed = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int[] answer = new int[2];

        for(String operation : operations) {
            if(operation.charAt(0) == 'I') {
                ordered.offer(Integer.parseInt(operation.substring(2, operation.length())));
                reversed.offer(Integer.parseInt(operation.substring(2, operation.length())));
            }else if(operation.charAt(0) == 'D') {
                if(operation.charAt(2) == '-') {
                    if(!ordered.isEmpty()) {
                        int n = ordered.poll();
                        reversed.remove(n);
                    }
                }else {
                    if(!reversed.isEmpty()) {
                        int n = reversed.poll();
                        ordered.remove(n);
                    }
                }
            }
        }

        if(!ordered.isEmpty()) answer[1] = ordered.peek();
        if(!reversed.isEmpty()) answer[0] = reversed.peek();
        return answer;
    }
}