package PROGRAMMERS.level_1._12977;

public class Solution {
    public boolean isPrime(int n) {
        int count = 0;
        for(int i=1; i*i<=n; i++) {
            if(n % i == 0) count++;
        }

        return count == 1;
    }

    public int solution(int[] nums) {
        int answer = 0;

        int n = nums.length;
        for(int i=0; i<n-2; i++) {
            for(int j=i+1; j<n-1; j++) {
                for(int k=j+1; k<n; k++) {
                    if(isPrime(nums[i] + nums[j] + nums[k])) answer++;
                }
            }
        }

        return answer;
    }
}
