package PROGRAMMERS.level_1._12921;

public class Solution {
    public int solution(int n) {
        int answer = 0;
        boolean[] primes = new boolean[n+1];

        primes[0] = primes[1] = true;
        for(int i=2; i*i<=n; i++) {
            if(!primes[i]) {
                for(int j=i*i; j<=n; j+=i) primes[j] = true;
            }
        }
        for(int i=2; i<=n; i++) {
            if(!primes[i]) answer++;
        }

        return answer;
    }
}
