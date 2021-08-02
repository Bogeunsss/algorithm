package PROGRAMMERS.level_1._81301;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int solution(String s) {
        Map<String,Integer> numbers = new HashMap<String,Integer>() {
            {
                put("zero", 0);
                put("one", 1);
                put("two", 2);
                put("three", 3);
                put("four", 4);
                put("five", 5);
                put("six", 6);
                put("seven", 7);
                put("eight", 8);
                put("nine", 9);
            }
        };
        int answer = 0;
        String number = "";

        for(int i=0; i<s.length(); i++) {
            if(Character.isDigit(s.charAt(i))) {
                answer *= 10;
                answer += s.charAt(i) - '0';
            }else {
                number += s.charAt(i);
                if(numbers.get(number) != null) {
                    answer *= 10;
                    answer += numbers.get(number);
                    number = "";
                }
            }
        }
        return answer;
    }
}