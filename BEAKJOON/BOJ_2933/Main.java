package BEAKJOON.BOJ_2933;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static final int[] dx = {-1, 1, 0, 0};
    public static final int[] dy = {0, 0, -1, 1};

    public static int r, c, n;
    public static char[][] cave;
    public static boolean[][] visited;
    public static int[] height;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        cave = new char[r+1][c+1];

        for(int i=1; i<=r; i++) {
            String row = br.readLine();
            for(int j=1; j<=c; j++) {
                cave[i][j] = row.charAt(j-1);
            }
        }

        n = Integer.parseInt(br.readLine());
        height = new int[n];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<n; i++) height[i] = r + 1 - Integer.parseInt(st.nextToken());

    }

    public static boolean outOfRange(int x, int y) {
        return x <= 0 || x > r || y <= 0 || y > c;
    }

    public static void copy(char[][] A, char[][] B) {

        for(int i=1; i<=r; i++) {
            for(int j=1; j<=c; j++) {
                if(B[i][j] == 'x') A[i][j] = B[i][j];
            }
        }
    }

    public static void throwing(int turn, int h) {

        if(turn % 2 == 0) {
            for(int i=1; i<=c; i++) {
                if(cave[h][i] == 'x') {
                    cave[h][i] = '.';
                    return;
                }
            }
        }else {
            for(int i=c; i>=1; i--) {
                if(cave[h][i] == 'x') {
                    cave[h][i] = '.';
                    return;
                }
            }
        }
    }

    public static boolean isFloating(List<int[]> mineral, int _x, int _y) {

        Queue<int[]> q = new LinkedList<>();
        int lowest = _x;

        q.offer(new int[]{_x, _y});
        visited[_x][_y] = true;
        mineral.add(new int[]{_x, _y});

        while(!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.peek()[1];
            q.poll();

            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(outOfRange(nx, ny)) continue;
                if(visited[nx][ny] || cave[nx][ny] == '.') continue;

                lowest = Math.max(lowest, nx);
                mineral.add(new int[]{nx, ny});

                visited[nx][ny] = true;
                q.offer(new int[]{nx, ny});
            }
        }

        return lowest < r;
    }

    public static int getHeight(boolean[][] cluster, int x, int y) {

        int ret = 0;

        for(int i=x+1; i<=r; i++) {
            if(cave[i][y] == 'x') {
                if(cluster[i][y]) return Integer.MAX_VALUE;
                return ret;
            }else if(cave[i][y] == '.') ret++;
        }
        return ret;
    }

    public static void drop(List<int[]> mineral, boolean[][] cluster) {

        int h = Integer.MAX_VALUE;

        for(int[] mine : mineral) {
            int x = mine[0];
            int y = mine[1];
            int gap = getHeight(cluster, x, y);

            if(gap != Integer.MAX_VALUE) {
                h = Math.min(h, gap);
            }
        }

        mineral.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o2[0] == o1[0]) return o2[1] - o1[1];
                return o2[0] - o1[0];
            }
        });
        for(int i=0; i<mineral.size(); i++) {
            int x = mineral.get(i)[0];
            int y = mineral.get(i)[1];

            cave[x][y] = '.';
            cave[x+h][y] = 'x';
        }
    }

    public static void floatingCheck() {

        visited = new boolean[r+1][c+1];
        boolean[][] cluster = new boolean[r+1][c+1];
        List<int[]> floatingMineral = new ArrayList<>();

        for(int i=1; i<=r; i++) {
            for(int j=1; j<=c; j++) {
                if(!visited[i][j] && cave[i][j] == 'x') {
                    List<int[]> mineral = new ArrayList<>();

                    if(isFloating(mineral, i, j)) {
                        for(int[] mine : mineral) {
                            int x = mine[0];
                            int y = mine[1];

                            floatingMineral.add(new int[]{x, y});
                        }
                    }
                }
            }
        }
        for(int[] mine : floatingMineral) {
            int x = mine[0];
            int y = mine[1];

            cluster[x][y] = true;
        }

        drop(floatingMineral, cluster);
    }

    public static void print() {

        for(int i=1; i<=r; i++) {
            for(int j=1; j<=c; j++) {
                System.out.print(cave[i][j]);
            }
            if(i < r) System.out.println();
        }
    }

    public static void throwingSticks() {

        for(int i=0; i<n; i++) {
            throwing(i, height[i]);
            floatingCheck();
        }

        print();
    }

    public static void main(String[] args) throws IOException {

        input();
        throwingSticks();
    }
}

/*
5 5
xxxxx
x....
xxxxx
x....
x....
1
3
 */