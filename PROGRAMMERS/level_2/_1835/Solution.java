package PROGRAMMERS.level_2._1835;

import java.util.Stack;

public class Solution {
    static String friends = "ACFJMNRT";
    static Stack<Character> stack;
    static boolean[] visited;
    static int count;

    public static void dfs(String[] data, int index) {
        if(index == 8) {
            for(String d : data) {
                if(d.charAt(3) == '>') {
                    if(Math.abs(stack.indexOf(d.charAt(0)) - stack.indexOf(d.charAt(2))) <= Character.getNumericValue(d.charAt(4)) + 1) return;
                }
                else if(d.charAt(3) == '<') {
                    if(Math.abs(stack.indexOf(d.charAt(0)) - stack.indexOf(d.charAt(2))) >= Character.getNumericValue(d.charAt(4)) + 1) return;
                }
                else {
                    if(Math.abs(stack.indexOf(d.charAt(0)) - stack.indexOf(d.charAt(2))) != Character.getNumericValue(d.charAt(4)) + 1) return;
                }
            }
            count++;
        }
        for(int i=0; i<8; i++) {
            if(!visited[i]) {
                visited[i] = true;
                stack.add(friends.charAt(i));
                dfs(data, index+1);
                stack.pop();
                visited[i] = false;
            }
        }
    }

    public int solution(int n, String[] data) {
        stack = new Stack<>();
        visited = new boolean[8];
        count = 0;

        dfs(data, 0);

        return count;
    }
}
