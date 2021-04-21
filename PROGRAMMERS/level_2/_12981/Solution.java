package PROGRAMMERS.level_2._12981;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static int[] solution(int n, String[] words) {
        List<String> list = new ArrayList<>();
        char prev = words[0].charAt(words[0].length()-1);
        int[] player = new int[n+1];
        int[] answer = new int[2];
        list.add(words[0]);
        player[1] = 1;

        for(int i=1; i<words.length; i++) {
            player[i%n+1]++;
            if(prev != words[i].charAt(0) || list.contains(words[i])) {
                answer[0] = i % n + 1;
                answer[1] = player[i%n+1];
                break;
            }
            list.add(words[i]);
            prev = words[i].charAt(words[i].length()-1);
        }

        return answer;
    }
}
