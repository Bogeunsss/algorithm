package PROGRAMMERS.level_2._42584;

public class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        int index = 0;

        for(int i=0; i<prices.length; i++) {
            int seconds = -1;
            for(int j=i; j<prices.length; j++) {
                seconds++;
                if(prices[i] > prices[j]) break;
            }
            answer[index++] = seconds;
        }
        return answer;
    }
}
