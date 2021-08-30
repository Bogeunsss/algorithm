package PROGRAMMERS.level_3._60062;

public class Solution {
    private boolean[] visited;
    private int[] weak, dist;
    private int W, D, answer = Integer.MAX_VALUE;

    private void inspection(int[] friends) {
        for(int i=0; i<W; i++) {
            int start = weak[i];
            int finish = weak[i+W-1];

            for(int j=0; j<D; j++) {
                start += friends[j];

                if(start >= finish) {
                    answer = Math.min(answer, j + 1);
                    break;
                }
                for(int k=0; k<W; k++) {
                    if(weak[k] > start) {
                        start = weak[k];
                        break;
                    }
                }
            }
        }
    }

    private void permutation(int[] seq, int depth) {
        if(depth == D) {
            inspection(seq);
            return;
        }
        for(int i=0; i<D; i++) {
            if(visited[i]) continue;

            visited[i] = true;
            seq[depth] = dist[i];
            permutation(seq, depth + 1);
            seq[depth] = 0;
            visited[i] = false;
        }
    }

    public int solution(int n, int[] weak, int[] dist) {
        this.dist = dist;
        this.W = weak.length;
        this.D = dist.length;
        this.weak = new int[W*2];
        this.visited = new boolean[D];

        for(int i=0; i<W; i++) {
            this.weak[i] = weak[i];
            this.weak[i+W] = weak[i] + n;
        }

        permutation(new int[D], 0);

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}
