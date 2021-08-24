package PROGRAMMERS.level_3._12979;

public class Solution {

    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int now = 1, index = 0;

        while(now <= n) {
            if(index < stations.length && now >= stations[index] - w) {
                now = stations[index++] + w + 1;
            }else {
                now += w * 2 + 1;
                answer++;
            }
        }
        return answer;
    }
}