package PROGRAMMERS.level_3._60063;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public static final int[] dx = {0, 1, 0, -1};
    public static final int[] dy = {1, 0, -1, 0};
    public static final int[] rx = {-1, 1, 1, -1};
    public static final int[] ry = {1, 1, -1, -1};

    public static Queue<Robot> q = new LinkedList<>();
    public static boolean[][][] visited = new boolean[101][101][4];
    public static int N;

    public static class Robot {
        int x;
        int y;
        int d;
        int move;

        public Robot(int x, int y, int d, int move) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.move = move;
        }
    }

    public static boolean outOfRange(int x, int y, int px, int py) {
        if(x < 0 || x >= N || y < 0 || y >= N) return true;
        return px < 0 || px >= N || py < 0 || py >= N;
    }

    public static int moveStraight(int[][] board, Robot robot) {
        int x = robot.x;
        int y = robot.y;
        int d = robot.d;
        int move = robot.move;

        int px = x + dx[d];
        int py = y + dy[d];

        if((x == N - 1 && y == N - 1)
                || px == N - 1 && py == N - 1) return move;

        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            int npx = px + dx[i];
            int npy = py + dy[i];

            if(outOfRange(nx, ny, npx, npy)) continue;
            if(board[nx][ny]== 1 || board[npx][npy] == 1 || visited[nx][ny][d]) continue;

            visited[nx][ny][d] = true;
            q.offer(new Robot(nx, ny, d, move + 1));
        }

        return 0;
    }

    public static int moveRotate(int[][] board, Robot robot) {
        int x = robot.x;
        int y = robot.y;
        int d = robot.d;
        int move = robot.move;

        int px = x + dx[d];
        int py = y + dy[d];

        if((x == N - 1 && y == N - 1)
                || px == N - 1 && py == N - 1) return move;

        for(int i=1; i<4; i+=2) {
            int nd = (d + i) % 4;
            int npx = x + dx[nd];
            int npy = y + dy[nd];
            int dir = d;
            int nrx, nry;

            if(i == 1) {
                dir = nd;
            }
            nrx = x + rx[dir];
            nry = y + ry[dir];

            if(outOfRange(npx, npy, nrx, nry)) continue;
            if(board[npx][npy] == 1 || board[nrx][nry] == 1 || visited[x][y][nd]) continue;

            visited[x][y][nd] = true;
            q.offer(new Robot(x, y, nd, move + 1));
        }

        d = (d + 2) % 4;
        for(int i=1; i<4; i+=2) {
            int nd = (d + i) % 4;
            int npx = px + dx[nd];
            int npy = py + dy[nd];
            int dir = d;
            int nrx, nry;

            if(i == 1) {
                dir = nd;
            }
            nrx = px + rx[dir];
            nry = py + ry[dir];
            nd = (nd + 2) % 4;

            if(outOfRange(npx, npy, nrx, nry)) continue;
            if(board[npx][npy] == 1 || board[nrx][nry] == 1 || visited[npx][npy][nd]) continue;

            visited[npx][npy][nd] = true;
            q.offer(new Robot(npx, npy, nd, move + 1));
        }
        return 0;
    }

    public static int bfs(int[][] board) {
        q = new LinkedList<>();
        visited = new boolean[101][101][4];
        N = board.length;
        int ret = 0;

        q.offer(new Robot(0, 0, 0, 0));
        visited[0][0][0] = true;

        while(!q.isEmpty()) {
            Robot robot = q.poll();

            int straight = moveStraight(board, robot);
            if(straight != 0) {
                ret = straight;
                break;
            };

            int rotate = moveRotate(board, robot);
            if(rotate != 0) {
                ret = rotate;
                break;
            }
        }
        return ret;
    }

    public static int solution(int[][] board) {
        return bfs(board);
    }
}
