package PROGRAMMERS.level_3._70130;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int solution(int[] a) {
        Map<Integer,Integer> map = new HashMap<>();
        int answer = -1;

        for(int n : a) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        for(int key : map.keySet()) {
            if(map.get(key) <= answer) continue;
            int count = 0;

            for(int i=0; i<a.length-1; i++) {
                if(a[i] != key && a[i+1] != key) continue;
                if(a[i] == a[i+1]) continue;
                count++;
                i++;
            }
            answer = Math.max(answer, count);
        }

        return answer * 2;
    }
}