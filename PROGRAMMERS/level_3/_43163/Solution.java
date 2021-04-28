package PROGRAMMERS.level_3._43163;

public class Solution {
    static boolean[] visited;
    static int answer;

    public static void dfs(String[] words, int depth, String begin, String target) {
        if(depth >= words.length) return;
        if(begin.equals(target)) {
            answer = Math.min(answer, depth);
            return;
        }
        for(int i=0; i<words.length; i++) {
            int cnt = 0;
            for(int j=0; j<begin.length(); j++) {
                if(begin.charAt(j) != words[i].charAt(j)) cnt++;
            }
            if(cnt == 1 && !visited[i]) {
                visited[i] = true;
                dfs(words, depth+1, words[i], target);
                visited[i] = false;
            }
        }
    }

    public int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];
        boolean find = false;
        answer = Integer.MAX_VALUE;

        for(String word : words) {
            if(word.equals(target)) find = true;
        }
        if(!find) return 0;
        dfs(words, 0, begin, target);
        return answer;
    }
}