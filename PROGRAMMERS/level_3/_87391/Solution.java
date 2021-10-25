package PROGRAMMERS.level_3._87391;

public class Solution {

    public long solution(int n, int m, int x, int y, int[][] queries) {

        long top = x, bottom = x;
        long left = y, right = y;
        long answer = 0;
        boolean arrival = true;

        for(int i=queries.length-1; i>=0; i--) {
            int command = queries[i][0];
            int dx = queries[i][1];
            long move;

            switch(command) {
                case 0:
                    move = right + dx < m ? right + dx : m - 1;
                    if(left != 0) left += dx;
                    right = move;
                    break;
                case 1:
                    move = left - dx >= 0 ? left - dx : 0;
                    if(right != m - 1) right -= dx;
                    left = move;
                    break;
                case 2:
                    move = bottom + dx < n ? bottom + dx : n - 1;
                    if(top != 0) top += dx;
                    bottom = move;
                    break;
                case 3:
                    move = top - dx >= 0 ? top - dx : 0;
                    if(bottom != n - 1) bottom -= dx;
                    top = move;
            }
            if(left > m - 1 || right < 0 || top > n - 1 || bottom < 0) {
                arrival = false;
                break;
            }
        }
        if(arrival) answer = ((bottom - top) + 1) * ((right - left) + 1);

        return answer;
    }
}
