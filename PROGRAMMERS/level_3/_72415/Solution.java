package PROGRAMMERS.level_3._72415;

import java.util.*;

public class Solution {
    public static final int[] dx = {-1, 1, 0, 0};
    public static final int[] dy = {0, 0, -1, 1};
    public static final int N = 4;

    public static Map<Integer,List<Point>> map;

    public static class Point {
        int x;
        int y;
        int d;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    public static boolean gameOver(int[][] board) {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(board[i][j] != 0) return false;
            }
        }
        return true;
    }

    public static boolean range(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    public static int getDistance(int[][] board, int x1, int y1, int x2, int y2) {
        PriorityQueue<Point> pq = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.d - o2.d;
            }
        });
        int[][] distance = new int[N][N];

        pq.offer(new Point(x1, y1, 0));
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }
        distance[x1][y1] = 0;

        while(!pq.isEmpty()) {
            Point point = pq.poll();

            if(distance[point.x][point.y] < point.d) continue;
            if(point.x == x2 && point.y == y2) return point.d;

            for(int i=0; i<N; i++) {
                int count = 0;
                int nx = point.x;
                int ny = point.y;

                while(range(nx + dx[i], ny + dy[i])) {
                    count++;
                    nx += dx[i];
                    ny += dy[i];

                    if(board[nx][ny] != 0) break;
                    if(distance[nx][ny] > point.d + count) {
                        distance[nx][ny] = point.d + count;
                        pq.offer(new Point(nx, ny, point.d + count));
                    }
                }

                if(distance[nx][ny] > point.d + 1) {
                    distance[nx][ny] = point.d + 1;
                    pq.offer(new Point(nx , ny, point.d + 1));
                }
            }
        }
        return 0;
    }

    public static int play(int[][] board, int r, int c) {
        if(gameOver(board)) return 0;

        int ret = Integer.MAX_VALUE;
        Set<Integer> keySet = new HashSet<>(map.keySet());

        for(int key : keySet) {
            List<Point> points = map.get(key);

            int firstCard = getDistance(board, r, c, points.get(0).x, points.get(0).y)
                    + getDistance(board, points.get(0).x, points.get(0).y, points.get(1).x, points.get(1).y) + 2;
            int secondCard = getDistance(board, r, c, points.get(1).x, points.get(1).y)
                    + getDistance(board, points.get(1).x, points.get(1).y, points.get(0).x, points.get(0).y) + 2;

            board[points.get(0).x][points.get(0).y] = 0;
            board[points.get(1).x][points.get(1).y] = 0;
            map.remove(key);

            ret = Math.min(ret, firstCard + play(board, points.get(1).x, points.get(1).y));
            ret = Math.min(ret, secondCard + play(board, points.get(0).x, points.get(0).y));

            board[points.get(0).x][points.get(0).y] = key;
            board[points.get(1).x][points.get(1).y] = key;
            map.put(key, points);
        }
        return ret;
    }

    public int solution(int[][] board, int r, int c) {
        map = new HashMap<>();

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(board[i][j] != 0) {
                    if(!map.containsKey(board[i][j])){
                        map.put(board[i][j], new ArrayList<>());
                    }
                    map.get(board[i][j]).add(new Point(i, j));
                }
            }
        }

        return play(board, r, c);
    }
}