package PROGRAMMERS.level_2._12951;

public class Solution {
    public String solution(String s) {
        StringBuilder answer = new StringBuilder();
        String[] words = s.toLowerCase().split("");
        boolean isFirst = true;

        for(String word : words) {
            answer.append(isFirst ? word.toUpperCase() : word);
            isFirst = word.equals(" ");
        }
        return answer.toString();
    }
}
