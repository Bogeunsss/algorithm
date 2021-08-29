package PROGRAMMERS.level_3._68646;

public class Solution {

    public int solution(int[] a) {
        int answer = 1;
        int left = 0, right = a.length-1;
        int lMin = a[left], rMin = a[right];

        while(left < right) {
            if(a[left] < a[right]) {
                if(rMin > a[right-1]) {
                    rMin = a[right-1];
                    answer++;
                }
                right--;
            }else {
                if(lMin > a[left+1]) {
                    lMin = a[left+1];
                    answer++;
                }
                left++;
            }
        }
        return answer;
    }
}
