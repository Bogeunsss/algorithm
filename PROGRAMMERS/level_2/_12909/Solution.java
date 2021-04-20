package PROGRAMMERS.level_2._12909;

import java.util.Stack;

public class Solution {
    public boolean solution(String s) {
        boolean answer = true;
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<s.length(); i++) {
            if(stack.isEmpty()) {
                if(s.charAt(i) == ')') return false;
                stack.add(s.charAt(i));
            }
            else {
                if(stack.peek() == '(') {
                    if(s.charAt(i) == ')') stack.pop();
                    else stack.add(s.charAt(i));
                }else stack.add(s.charAt(i));
            }
        }
        if(!stack.isEmpty()) return false;
        return answer;
    }
}
