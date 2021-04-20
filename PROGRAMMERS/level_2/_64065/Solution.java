package PROGRAMMERS.level_2._64065;

import java.util.HashMap;

public class Solution {
    public int[] solution(String s) {
        StringBuilder numbers = new StringBuilder();
        HashMap<String, Integer> map = new HashMap<>();

        for(int i=1; i<s.length()-1; i++) {
            if(s.charAt(i) == ',') {
                map.put(numbers.toString(), map.getOrDefault(numbers.toString(), 0) + 1);
                numbers = new StringBuilder();
            }else {
                if(Character.isDigit(s.charAt(i))) {
                    numbers.append(s.charAt(i));
                }
                if(i == s.length()-2) {
                    map.put(numbers.toString(), map.getOrDefault(numbers.toString(), 0) + 1);
                }
            }
        }

        int size = map.size();
        int[] answer = new int[size];
        for(String key : map.keySet()) {
            answer[size - map.get(key)] = Integer.parseInt(key);
        }

        return answer;
    }
}
