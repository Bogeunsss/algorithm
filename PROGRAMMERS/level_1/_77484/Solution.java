package PROGRAMMERS.level_1._77484;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static final int[] winnings = {6, 6, 5, 4, 3, 2, 1};

    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        List<Integer> list = new ArrayList<>();

        for(int win : win_nums) list.add(win);
        for(int i=0; i<lottos.length; i++) {
            if(lottos[i] == 0) {
                answer[0]++;
            }else {
                if(list.contains(lottos[i])) {
                    answer[0]++;
                    answer[1]++;
                }
            }
        }
        answer[0] = winnings[answer[0]];
        answer[1] = winnings[answer[1]];

        return answer;
    }
}
