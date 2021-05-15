package PROGRAMMERS.level_3._67258;

import java.util.*;

public class Solution {
    public int[] solution(String[] gems) {
        HashSet<String> set = new HashSet<>(Arrays.asList(gems));
        HashMap<String,Integer> map = new HashMap<>();
        int[] answer = {};
        int low = 0, high = 0, i = 0;
        int min = Integer.MAX_VALUE;

        if(set.size() == 1) return new int[]{1, 1};
        while(low < gems.length && high < gems.length) {
            for(i=high; i< gems.length; i++) {
                map.put(gems[i], map.getOrDefault(gems[i], 0) + 1);
                if(set.size() == map.size()) {
                    high = i;
                    break;
                }
            }
            if(i == gems.length) break;
            for(i=low; i< gems.length; i++) {
                if(map.get(gems[i]) == 1) {
                    low = i;
                    break;
                }else map.put(gems[i], map.getOrDefault(gems[i], 0) - 1);
            }
            if(high - low < min) {
                answer = new int[]{low + 1, high + 1};
                min = high - low;
            }
            map.remove(gems[low++]);
            high++;
        }

        return answer;
    }
}
