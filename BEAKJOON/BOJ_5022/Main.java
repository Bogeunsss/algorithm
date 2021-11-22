package BEAKJOON.BOJ_5022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Circuit {

    int index;

    public Circuit(int index) {
        this.index = index;
    }
}

public class Main {

    public static final int[] dx = {-1, 1, 0, 0};
    public static final int[] dy = {0, 0, -1, 1};

    public static int n, m;
    public static int[][] A, B;
    public static boolean[][] visited;
    public static Circuit[][] map;
    public static Map<Integer,int[]> locations;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        locations = new HashMap<>();
        map = new Circuit[n+1][m+1];
        A = new int[2][2];
        B = new int[2][2];

        int index = 1;

        for(int i=0; i<2; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            A[i][0] = Integer.parseInt(st.nextToken());
            A[i][1] = Integer.parseInt(st.nextToken());
        }
        for(int i=0; i<2; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            B[i][0] = Integer.parseInt(st.nextToken());
            B[i][1] = Integer.parseInt(st.nextToken());
        }
        for(int i=0; i<=n; i++) {
            for(int j=0; j<=m; j++) {
                locations.put(index, new int[]{i, j});
                map[i][j] = new Circuit(index++);
            }
        }
    }

    public static int connect(int[][] cable, int[][] other) {

        Queue<int[]> q = new LinkedList<>();
        int[] path = new int[(n+1)*(m+1)+1];
        int[] src = cable[0];
        int[] dst = cable[1];
        boolean isConnected = false;

        q.offer(new int[]{src[0], src[1]});
        path[map[src[0]][src[1]].index] = 0;
        visited[src[0]][src[1]] = true;

        while(!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.peek()[1];
            q.poll();

            if(x == dst[0] && y == dst[1]) {
                isConnected = true;
                break;
            }

            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx > n || ny < 0 || ny > m) continue;
                if(nx == other[0][0] && ny == other[0][1]) continue;
                if(nx == other[1][0] && ny == other[1][1]) continue;
                if(!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    path[map[nx][ny].index] = map[x][y].index;
                    q.offer(new int[]{nx, ny});
                }
            }
        }

        if(!isConnected) return -1;

        visited = new boolean[n+1][m+1];
        int index = map[dst[0]][dst[1]].index;
        int ret = 0;

        visited[dst[0]][dst[1]] = true;
        while(path[index] != 0) {
            int[] p = locations.get(path[index]);
            visited[p[0]][p[1]] = true;
            index = path[index];
            ret++;
        }

        return ret;
    }

    public static void connection() {

        int case1 = 0, case2 = 0;

        visited = new boolean[n+1][m+1];
        int distance = connect(A, B);

        if(distance == -1) case1 = Integer.MAX_VALUE;
        else {
            case1 += distance;
            distance = connect(B, A);

            if(distance == -1) case1 = Integer.MAX_VALUE;
            else case1 += distance;
        }


        visited = new boolean[n+1][m+1];
        distance = connect(B, A);

        if(distance == -1) case2 = Integer.MAX_VALUE;
        else {
            case2 += distance;
            distance = connect(A, B);

            if(distance == -1) case2 = Integer.MAX_VALUE;
            else case2 += distance;
        }

        System.out.println(case1 == Integer.MAX_VALUE && case2 == Integer.MAX_VALUE ? "IMPOSSIBLE" : Math.min(case1, case2));
    }

    public static void main(String[] args) throws IOException {

        input();
        connection();
    }
}

/*
탐색시에 케이블마다 시작점과 끝점이 또다른 시작점과 끝점과 겹치면 안된다.
 */