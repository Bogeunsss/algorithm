package PROGRAMMERS.level_3._12900;

public class Solution {
    public int solution(int n) {
        int[] arr = new int[n+1];
        for(int i=0; i<=n; i++) {
            if(i<=2) arr[i] = i;
            else arr[i] = (arr[i-1] + arr[i-2]) % 1000000007;
        }
        return arr[n];
    }
}
