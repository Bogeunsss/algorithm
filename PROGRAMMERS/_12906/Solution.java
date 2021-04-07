package PROGRAMMERS._12906;

import java.util.*;

public class Solution {
    public static ArrayList<Integer> solution(int []arr) {
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

    public static void main(String[] args) {
        int[] arr = {1, 1, 3, 3, 0, 1, 1};

        System.out.println(solution(arr));
    }
}
