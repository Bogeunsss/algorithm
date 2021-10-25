package PROGRAMMERS.level_2._87946;

public class Solution {

    private int[][] dungeons;
    private boolean[] visited;
    private int n, k;
    private int answer = 0;

    private void adventure(int depth, int cnt) {

        if(depth >= n) {
            answer = Math.max(answer, cnt);
            return;
        }

        for(int i=0; i<n; i++) {
            if(visited[i]) continue;

            visited[i] = true;
            if(dungeons[i][0] > k) adventure(depth + 1, cnt);
            else {
                k -= dungeons[i][1];
                adventure(depth + 1, cnt + 1);
                k += dungeons[i][1];
            }
            visited[i] = false;
        }
    }

    public int solution(int k, int[][] dungeons) {

        this.dungeons = dungeons;
        this.k = k;

        n = dungeons.length;
        visited = new boolean[n];

        adventure(0, 0);

        return answer;
    }
}
