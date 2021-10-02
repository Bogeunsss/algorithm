package PROGRAMMERS.level_2._86052;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    private final int[] dx = {-1, 0, 1, 0};
    private final int[] dy = {0, 1, 0, -1};

    private int n, m;
    private String[] grid;
    private boolean[][][] visited;

    private int light(int x, int y, int d, int len) {

        while(!visited[x][y][d]) {
            visited[x][y][d] = true;

            if(grid[x].charAt(y) == 'L') {
                d = d == 0 ? 3 : d - 1;
            }else if(grid[x].charAt(y) == 'R') {
                d = d == 3 ? 0 : d + 1;
            }
            x += dx[d];
            y += dy[d];

            if(x < 0) x = n - 1;
            if(y < 0) y = m - 1;
            if(x >= n) x = 0;
            if(y >= m) y = 0;

            len++;
        }

        return len;
    }

    public int[] solution(String[] grid) {

        this.grid = grid;

        n = grid.length;
        m = grid[0].length();
        visited = new boolean[n][m][4];

        List<Integer> cycles = new ArrayList<>();

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                for(int k=0; k<4; k++) {
                    int cycle = light(i, j, k, 0);
                    if(cycle != 0) cycles.add(cycle);
                }
            }
        }

        int[] answer = new int[cycles.size()];
        for(int i=0; i<cycles.size(); i++) {
            answer[i] = cycles.get(i);
        }

        Arrays.sort(answer);

        return answer;
    }
}