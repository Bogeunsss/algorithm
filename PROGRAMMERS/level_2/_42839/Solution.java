package PROGRAMMERS.level_2._42839;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
    static boolean[] visited;
    static Stack<Character> stack;
    static List<Integer> list;

    public static boolean isPrime(int n) {
        for(int i=2; i*i<n; i++) {
            if(n % i == 0) return true;
        }
        return false;
    }

    public static void recursive(Stack<Character> stack, String numbers, int n, int depth) {
        if(depth == n) {
            StringBuilder temp = new StringBuilder();
            for (Character character : stack) temp.append(character);
            int x = Integer.parseInt(temp.toString());
            if(x > 1 && !isPrime(x) && !list.contains(x)) list.add(x);
        }
        for(int i=0; i<numbers.length(); i++) {
            if(!visited[i]) {
                visited[i] = true;
                stack.add(numbers.charAt(i));
                recursive(stack, numbers, n, depth+1);
                stack.pop();
                visited[i] = false;
            }
        }
    }

    public int solution(String numbers) {
        int n = numbers.length();
        visited = new boolean[n];
        stack = new Stack<>();
        list = new ArrayList<>();

        for(int i=1; i<numbers.length()+1; i++) {
            recursive(stack, numbers, i, 0);
        }

        return list.size();
    }
}
