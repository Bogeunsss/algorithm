package PROGRAMMERS.level_1._12906;

import java.util.*;

public class Solution {
    public ArrayList<Integer> solution(int []arr) {
        int pos;
        ArrayList<Integer> answer = new ArrayList<Integer>();
        for(pos=0; pos<arr.length;) {
            int current = arr[pos];
            while(current == arr[pos]) {
                pos++;
                if(pos >= arr.length) break;
            }
            answer.add(current);
        }

        return answer;
    }
}
