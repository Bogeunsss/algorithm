package PROGRAMMERS.level_2._12939;

public class Solution {
    public String solution(String s) {
        String[] numbers = s.split(" ");
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for(String number : numbers) {
            max = Math.max(max, Integer.parseInt(number));
            min = Math.min(min, Integer.parseInt(number));
        }
        return min + " " + max;
    }
}
