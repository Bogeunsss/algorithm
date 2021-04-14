package PROGRAMMERS.level_1._42889;

public class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        float[] rate = new float[N];
        int challengers = stages.length;


        for (int stage : stages) {
            if (stage == 0) challengers--;
        }
        for(int i=1; i<=N; i++) {
            float failed = 0;
            for(int j=0; j<stages.length; j++) {
                if(stages[j] != -1 && stages[j] <= i) {
                    failed++;
                    stages[j] = -1;
                }
            }
            answer[i-1] = i;
            rate[i-1] = failed / challengers;
            challengers -= failed;
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N-i-1; j++) {
                if(rate[j] < rate[j+1]) {
                    int temp = answer[j];
                    answer[j] = answer[j+1];
                    answer[j+1] = temp;

                    float temp2 = rate[j];
                    rate[j] = rate[j+1];
                    rate[j+1] = temp2;
                }
            }
        }
        return answer;
    }
}
