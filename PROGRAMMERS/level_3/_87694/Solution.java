package PROGRAMMERS.level_3._87694;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    private final int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    private final int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    private final int SIZE = 103;

    private int[][] map = new int[SIZE][SIZE];
    private int[][] inner = new int[SIZE][SIZE];

    private int getItem(int srcX, int srcY, int dstX, int dstY) {

        Queue<int[]> q = new LinkedList<>();
        int[][] d = new int[SIZE][SIZE];

        for(int i=0; i<SIZE; i++) Arrays.fill(d[i], Integer.MAX_VALUE);
        d[srcY][srcX] = 0;
        q.offer(new int[]{srcY, srcX});

        while(!q.isEmpty()) {
            int y = q.peek()[0];
            int x = q.peek()[1];
            q.poll();

            for(int i=0; i<8; i+=2) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if(ny <= 0 || ny >= SIZE || nx <= 0 || nx >= SIZE) continue;
                if(map[ny][nx] == 0) continue;
                if(d[ny][nx] > d[y][x] + 1) {
                    d[ny][nx] = d[y][x] + 1;
                    q.offer(new int[]{ny, nx});
                }
            }
        }

        return d[dstY][dstX] / 2;
    }

    private void getInnerPart(int _x, int _y) {

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[SIZE][SIZE];

        q.offer(new int[]{_y, _x});
        visited[_y][_x] = true;

        while(!q.isEmpty()) {
            int y = q.peek()[0];
            int x = q.peek()[1];
            q.poll();

            for(int i=0; i<8; i+=2) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                int cnt = 0;

                if(ny <= 0 || ny >= SIZE || nx <= 0 || nx >= SIZE) continue;
                if(visited[ny][nx] || map[ny][nx] == 0) continue;

                for(int j=0; j<8; j++) {
                    int nny = ny + dy[j];
                    int nnx = nx + dx[j];

                    if(nny < 0 || nny >= SIZE || nnx < 0 || nnx >= SIZE) continue;
                    if(map[nny][nnx] == 0) cnt++;
                }
                if(cnt == 0) inner[ny][nx] = 1;

                visited[ny][nx] = true;
                q.offer(new int[]{ny, nx});
            }
        }
    }

    private void removeInnerPart() {

        for(int i=0; i<SIZE; i++) {
            for(int j=0; j<SIZE; j++) {
                map[i][j] -= inner[i][j];
            }
        }
    }

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {

        for(int[] rect : rectangle) {
            int x1 = rect[0];
            int y1 = rect[1];
            int x2 = rect[2];
            int y2 = rect[3];

            for(int i=y1*2; i<=y2*2; i++) {
                for(int j=x1*2; j<=x2*2; j++) {
                    map[i][j] = 1;
                }
            }
        }

        getInnerPart(characterX*2, characterY*2);
        removeInnerPart();

        return getItem(characterX*2, characterY*2, itemX*2, itemY*2);
    }
}