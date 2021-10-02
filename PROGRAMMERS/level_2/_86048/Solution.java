package PROGRAMMERS.level_2._86048;

import java.util.*;

public class Solution {

    public static int[] solution(int[] enter, int[] leave) {
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

    public static void main(String[] args) {
        System.out.println(Arrays.toString(Solution.solution(new int[]{1, 3, 2}, new int[]{1, 2, 3})));
        System.out.println(Arrays.toString(Solution.solution(new int[]{1, 4, 2, 3}, new int[]{2, 1, 3, 4})));
        System.out.println(Arrays.toString(Solution.solution(new int[]{3, 2, 1}, new int[]{2, 1, 3})));
        System.out.println(Arrays.toString(Solution.solution(new int[]{3, 2, 1}, new int[]{1, 3, 2})));
        System.out.println(Arrays.toString(Solution.solution(new int[]{1, 4, 2, 3}, new int[]{2, 1, 4, 3})));
    }
}
