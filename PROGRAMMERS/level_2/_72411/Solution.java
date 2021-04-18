package PROGRAMMERS.level_2._72411;

import java.util.*;

public class Solution {
    static Stack<Character> comb;
    static List<String> list;
    static Map<String, Integer> map;
    static boolean[] visited;
    static int max;

    public static void combination(String order, int k, int depth, int start) {
        if(k == depth) {
            char[] temp = new char[comb.size()];
            for(int i=0; i<comb.size(); i++) temp[i] = comb.get(i);
            Arrays.sort(temp);
            String strTemp = String.valueOf(temp);
            map.put(strTemp, map.getOrDefault(strTemp, 0) + 1);
            max = Math.max(max, map.get(strTemp));
        }
        for(int i=start; i<order.length(); i++) {
            if(!visited[i]) {
                visited[i] = true;
                comb.add(order.charAt(i));
                combination(order, k, depth+1, i);
                comb.pop();
                visited[i] = false;
            }
        }
    }

    public String[] solution(String[] orders, int[] course) {
        PriorityQueue<String> answer = new PriorityQueue<>();
        comb = new Stack<>();

        for(int c : course) {
            map = new HashMap<>();
            max = 0;
            for(String order : orders) {
                visited = new boolean[order.length()];
                combination(order, c, 0, 0);
            }
            for(String key : map.keySet()) {
                if(map.get(key) == max && max > 1) answer.offer(key);
            }
        }
        String[] ret = new String[answer.size()];
        int index = 0;
        while(!answer.isEmpty()) ret[index++] = answer.poll();
        return ret;
    }
}
