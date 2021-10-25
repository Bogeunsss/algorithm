package PROGRAMMERS.level_3._86053;

public class Solution {

    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {

        long answer = (long) (1e5 * 1e9 * 4);
        long low = 0, high = (long) (1e5 * 1e9 * 4);

        while(low <= high) {
            long mid = (high + low) / 2;
            long gold = 0, silver = 0, sum = 0;

            for(int i=0; i<g.length; i++) {
                long G = g[i], S = s[i], W = w[i], T = t[i];
                long move = mid / (T * 2);

                if(mid % (T * 2) >= t[i]) move++;

                gold += Math.min(G, move * W);
                silver += Math.min(S, move * W);
                sum += Math.min(G + S, move * W);
            }

            if(gold >= a && silver >= b && sum >= a + b) {
                high = mid - 1;
                answer = Math.min(answer, mid);
            }else low = mid + 1;
        }

        return answer;
    }
}
