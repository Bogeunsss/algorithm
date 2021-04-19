package PROGRAMMERS.level_2._42578;

import java.util.HashMap;

public class Solution {
    public int solution(String[][] clothes) {
        HashMap<String, Integer> style = new HashMap<>();
        int answer = 1;

        for(String[] cloth : clothes) {
            style.put(cloth[1], style.getOrDefault(cloth[1], 0) + 1);
        }
        for(String key : style.keySet()) answer *= style.get(key) + 1;
        return answer - 1;
    }
}
