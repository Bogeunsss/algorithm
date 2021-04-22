package PROGRAMMERS.level_2._12973;

import java.util.Stack;

public class Solution {
    public int solution(String s) {
        Stack<Character> stack = new Stack<>();
        int answer = 0;

        for(int i=0; i<s.length(); i++) {
            if(stack.isEmpty()) stack.add(s.charAt(i));
            else {
                if(s.charAt(i) == stack.peek()) stack.pop();
                else stack.add(s.charAt(i));
            }
        }
        if(stack.isEmpty()) answer = 1;
        return answer;
    }
}
