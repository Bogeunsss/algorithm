package PROGRAMMERS.level_2._42586;

import java.util.*;

public class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> fin = new ArrayList<Integer>();

        for(int i=0; i<progresses.length; i++) {
            if((100 - progresses[i]) % speeds[i] == 0) progresses[i] = (100 - progresses[i]) / speeds[i];
            else progresses[i] = (100 - progresses[i]) / speeds[i] + 1;
        }

        int j = 0;
        for(int i=0; i<progresses.length; i++) {
            int complete = 0;
            for(j=i+1; j<progresses.length; j++) {
                if(progresses[i] < progresses[j]) {
                    i = j - 1;
                    break;
                }
                complete++;
            }
            fin.add(complete+1);
            if(j == progresses.length) break;
        }

        int[] answer = new int[fin.size()];
        for(int k=0; k<fin.size(); k++) answer[k] = fin.get(k);

        return answer;
    }
}
