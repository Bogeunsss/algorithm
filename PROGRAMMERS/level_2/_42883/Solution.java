package PROGRAMMERS.level_2._42883;

import java.util.Stack;

public class Solution {
    public String solution(String number, int k) {
        Stack<Character> stack = new Stack<>();
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < number.length(); i++) {
            char num = number.charAt(i);
            while (!stack.isEmpty() && stack.peek() < num && k > 0) {
                stack.pop();
                k--;
            }
            if (k == 0) {
                for (int j = i; j < number.length(); j++) stack.add(number.charAt(j));
                break;
            }
            stack.add(num);
        }

        if (k > 0) {
            for (int i = 0; i < number.length() - k; i++) answer.append(stack.get(i));
        } else {
            for (Character c : stack) answer.append(c);
        }

        return answer.toString();
    }
}