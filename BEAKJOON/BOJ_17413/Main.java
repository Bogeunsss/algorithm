package BEAKJOON.BOJ_17413;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        Stack<Character> stack = new Stack<>();
        StringBuilder word = new StringBuilder();
        StringBuilder answer = new StringBuilder();

        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == '<') {
                if(word.length() > 0) {
                    answer.append(word.reverse());
                    word = new StringBuilder();
                }
                stack.push('<');
                answer.append('<');
            }else if(s.charAt(i) == '>'){
                stack.push('>');
                answer.append('>');
            }else if(s.charAt(i) == ' ') {
                answer.append(word.reverse()).append(' ');
                word = new StringBuilder();
            }else {
                if(stack.isEmpty()) {
                    word.append(s.charAt(i));
                }else {
                    if(stack.peek() == '<') answer.append(s.charAt(i));
                    else word.append(s.charAt(i));
                }
            }
        }
        if(word.length() > 0) {
            answer.append(word.reverse());
        }

        System.out.println(answer);
    }
}