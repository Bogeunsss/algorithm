package PROGRAMMERS.level_2._12953;

public class Solution {
    public int solution(int[] arr) {
        int answer = 0;
        int max = 0;
        boolean isFactor = true;

        for(int n : arr) max = Math.max(max, n);
        while(true) {
            for(int i=0; i<arr.length; i++) {
                if(max % arr[i] != 0) isFactor = false;
            }
            if(isFactor) {
                answer = max;
                break;
            }
            isFactor = true;
            max++;
        }

        return answer;
    }
}
