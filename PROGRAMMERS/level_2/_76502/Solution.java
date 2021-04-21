package PROGRAMMERS.level_2._76502;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solution {
    public static boolean isCorrect(char[] Q) {
        Stack<Character> stack = new Stack<>();
        int step = 0, size = Q.length;

        while(step < size) {
            char blank = Q[step++];
            if(stack.isEmpty()) {
                if(blank == ')' || blank == ']' || blank == '}') return false;
            }else {
                if(stack.peek() == '(' && blank == ')') {
                    stack.pop();
                    continue;
                }
                else if(stack.peek() == '[' && blank == ']') {
                    stack.pop();
                    continue;
                }
                else if(stack.peek() == '{' && blank == '}') {
                    stack.pop();
                    continue;
                }
            }
            stack.add(blank);
        }
        return stack.isEmpty();
    }

    public int solution(String s) {
        Queue<Character> Q = new LinkedList<>();
        int answer = 0;

        for(char c : s.toCharArray()) Q.offer(c);
        for(int i=0; i<s.length(); i++) {
            char[] check = new char[Q.size()];
            for(int j=0; j<Q.size(); j++) {
                check[j] = Q.poll();
                Q.offer(check[j]);
            }
            if(isCorrect(check)) answer++;
            char next = Q.poll();
            Q.offer(next);
        }
        return answer;
    }
}
