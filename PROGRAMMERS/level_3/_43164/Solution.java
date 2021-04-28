package PROGRAMMERS.level_3._43164;

import java.util.*;

public class Solution {
    static final int SRC = 0;
    static final int DST = 1;

    static List<String> answer;
    static boolean[] visited;
    static String path;

    public static void dfs(String[][] tickets, String dst, int depth) {
        path += dst + ",";
        if(depth == tickets.length) {
            answer.add(path);
            return;
        }
        for(int i=0; i<tickets.length; i++) {
            String nextSrc = tickets[i][SRC];
            String nextDst = tickets[i][DST];
            if(nextSrc.equals(dst) && !visited[i]) {
                visited[i] = true;
                dfs(tickets, nextDst, depth+1);
                visited[i] = false;
                path = path.substring(0, path.length()-4);
            }
        }
    }

    public String[] solution(String[][] tickets) {
        answer = new ArrayList<>();
        for(int i=0; i<tickets.length; i++) {
            visited = new boolean[tickets.length];
            String src = tickets[i][SRC];
            String dst = tickets[i][DST];

            if(src.equals("ICN")) {
                path = src + ",";
                visited[i] = true;
                dfs(tickets, dst, 1);
            }
        }
        Collections.sort(answer);
        return answer.get(0).split(",");
    }
}
