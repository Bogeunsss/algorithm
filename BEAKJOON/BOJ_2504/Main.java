package BEAKJOON.BOJ_2504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static Stack<String> stack = new Stack<>();
    public static String brackets;
    public static int n;

    public static boolean inner(String type1, String type2, int weight) {
        int calc = 0;

        while(!stack.isEmpty()) {
            String peek = stack.peek();

            if(peek.equals(type1)) {
                return false;
            }else if(peek.equals(type2)) {
                stack.pop();
                calc *= weight;
                stack.push(Integer.toString(calc));
                break;
            }else {
                calc += Integer.parseInt(stack.pop());
            }
        }

        return true;
    }

    public static int solution() {
        int ret = 0;

        for(int i=0; i<n; i++) {
            String bracket = Character.toString(brackets.charAt(i));
            boolean available;

            if(bracket.equals("(") || bracket.equals("[")) stack.push(bracket);
            else {
                if(stack.isEmpty()) return 0;
                if(bracket.equals(")")) {
                    if(stack.peek().equals("(")) {
                        stack.pop();
                        stack.push("2");
                    }else {
                        available = inner("[", "(", 2);
                        if(!available) {
                            return 0;
                        }
                    }
                }else {
                    if(stack.peek().equals("[")) {
                        stack.pop();
                        stack.push("3");
                    }else {
                        available = inner("(", "[", 3);
                        if(!available) {
                            return 0;
                        }
                    }
                }
            }
        }

        while(!stack.isEmpty()) {
            if("()[]".contains(stack.peek())) {
                return 0;
            }
            ret += Integer.parseInt(stack.pop());
        }

        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        brackets = br.readLine();
        n = brackets.length();

        System.out.println(solution());
    }
}