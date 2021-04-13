package PROGRAMMERS.level_1._12935;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int[] solution(int[] arr) {
        int min = arr[0];
        List<Integer> list = new ArrayList<Integer>();

        if(arr.length == 1) {
            list.add(-1);
        }else {
            for(int i=1; i<arr.length; i++) {
                if(min > arr[i]) min = arr[i];
            }
            for (int j : arr) {
                if (min != j) list.add(j);
            }
        }

        int[] answer = new int[list.size()];
        for(int i=0; i<answer.length; i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}
