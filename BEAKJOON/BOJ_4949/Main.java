package BEAKJOON.BOJ_4949;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> stack = new Stack<>();

        while(true) {
            String sentence = br.readLine();
            if(sentence.equals(".")) break;

            String balance = "yes";
            for(int i=0; i<sentence.length(); i++) {
                if(sentence.charAt(i) == '(' || sentence.charAt(i) == '[') {
                    stack.add(sentence.charAt(i));
                }else if(sentence.charAt(i) == ')') {
                    if(stack.isEmpty() || stack.peek() == '[') {
                        balance = "no";
                        break;
                    }
                    stack.pop();
                }else if(sentence.charAt(i) == ']') {
                    if(stack.isEmpty() || stack.peek() == '(') {
                        balance = "no";
                        break;
                    }
                    stack.pop();
                }
            }
            if(!stack.isEmpty()) balance = "no";
            System.out.println(balance.equals("yes") ? "yes" : "no");
            stack.clear();
        }
    }
}