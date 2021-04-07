package PROGRAMMERS._12910;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static int[] solution(int[] arr, int divisor) {
        int[] answer;
        List<Integer> list = new ArrayList<Integer>();

        for(int a : arr) {
            if(a % divisor == 0) list.add(a);
        }
        if(list.size() == 0) {
            answer = new int[1];
            answer[0] = -1;
        }else {
            answer = new int[list.size()];
            int index = 0;
            while(index < list.size()) answer[index] = list.get(index++);
            Arrays.sort(answer);
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 6};
        int divisor = 10;

        System.out.println(Arrays.toString(solution(arr, divisor)));
    }
}
