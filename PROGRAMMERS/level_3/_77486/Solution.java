package PROGRAMMERS.level_3._77486;

import java.util.*;

public class Solution {

    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String,String> parents = new HashMap<>();
        Map<String,Integer> indices = new HashMap<>();
        int[] answer = new int[enroll.length];

        for(int i=0; i<enroll.length; i++) {
            parents.put(enroll[i], referral[i]);
            indices.put(enroll[i], i);
        }
        for(int i=0; i<seller.length; i++) {
            amount[i] *= 100;
            String child = seller[i];
            while(!child.equals("-")) {
                answer[indices.get(child)] += amount[i] - amount[i] / 10;
                amount[i] /= 10;
                if(amount[i] < 1) break;
                child = parents.get(child);
            }
        }

        return answer;
    }
}
