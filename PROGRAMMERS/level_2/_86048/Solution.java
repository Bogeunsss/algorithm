package PROGRAMMERS.level_2._86048;

import java.util.*;

public class Solution {

    public int[] solution(int[] enter, int[] leave) {
        int n = enter.length;
        int[] answer = new int[n];

        Map<Integer,Integer> room = new HashMap<>();
        Set<Integer> out = new HashSet<>();
        int index = 0;

        for(int i=0; i<n; i++) {
            int cnt = 0;
            if(room.size() > 0) {
                for(int k : room.keySet()) {
                    if(out.contains(k)) continue;
                    room.put(k, room.get(k) + 1);
                    cnt++;
                }
            }
            room.put(enter[i], cnt);

            while(true) {
                if(index < n && room.containsKey(leave[index])) {
                    answer[leave[index]-1] = room.get(leave[index]);
                    out.add(leave[index]);
                    index++;
                }else break;
            }
        }

        return answer;
    }
}
