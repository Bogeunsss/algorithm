package BEAKJOON.BOJ_17472;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    /*
        1. 일단 섬에 번호을 매기면서 구분한다.
        2. 섬의 순서를 번갈아가며 다리를 놓아본다.
            - ex) 1 2 3 4, 1 2 4 3, 1 3 2 4 ...
        3. 다리를 놓을 때, 섬의 가장자리에서 놓는다.
            - 직선으로 쭉 가보고 섬이 있으면 설치
            - 두 섬이 연결되면 연결 표시를 남긴다.
     */
    public static final int[] dx = {-1, 1, 0, 0};
    public static final int[] dy = {0, 0, -1, 1};
    public static final int INF = Integer.MAX_VALUE;

    // 섬의 번호와 좌표를 저장
    public static Map<Integer, List<int[]>> islands = new HashMap<>();
    public static List<int[]> bridges = new ArrayList<>();
    public static Stack<Integer> stack = new Stack<>();
    public static boolean[][] visited;
    public static boolean[] check;
    public static int[][] map, dist;
    public static int n, m;
    public static int count = 0;
    public static int answer = Integer.MAX_VALUE;

    public static boolean range(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }

    // 섬 구분
    public static void search(int _x, int _y, int idx) {
        Queue<int[]> q = new LinkedList<>();

        islands.put(idx, new ArrayList<>());
        islands.get(idx).add(new int[]{_x, _y});

        q.offer(new int[]{_x, _y});
        visited[_x][_y] = true;
        map[_x][_y] = idx;

        while(!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.peek()[1];
            q.poll();

            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >=n || ny < 0 || ny >= m) continue;
                if(map[nx][ny] == 0 || visited[nx][ny]) continue;

                islands.get(idx).add(new int[]{nx, ny});
                visited[nx][ny] = true;
                map[nx][ny] = idx;
                q.offer(new int[]{nx, ny});
            }
        }
    }

    public static void connect(int x, int y, int d, int len, int src, int dst) {
        int nx = x + dx[d];
        int ny = y + dy[d];

        if(range(nx, ny)) return;
        if(map[nx][ny] == 0) connect(nx, ny, d, len + 1, src, dst);
        else if(map[nx][ny] == dst) {
            if(len >= 2 && dist[src][dst] > len) {
                dist[src][dst] = len;
                dist[dst][src] = len;
            }
        }
    }

    public static void build(int src, int dst) {
        for(int[] island : islands.get(src)) {
            int x = island[0];
            int y = island[1];

            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(range(nx, ny)) continue;
                if(map[nx][ny] == 0) {
                    connect(x, y, i, 0, src, dst);
                }
            }
        }
    }

    public static boolean isAvailable() {
        Queue<Integer> q = new LinkedList<>();
        boolean[][] connectionIsland = new boolean[count+1][count+1];
        boolean[] checkIsland = new boolean[count+1];
        int cnt = 1;

        q.offer(1);
        checkIsland[1] = true;

        for(int i=0; i<bridges.size(); i++) {
            if(check[i]) {
                int x = bridges.get(i)[0];
                int y = bridges.get(i)[1];

                connectionIsland[x][y] = connectionIsland[y][x] = true;
            }
        }
        while(!q.isEmpty()) {
            int now = q.poll();

            if(cnt == count) return true;
            for(int i=1; i<=count; i++) {
                if(i == now) continue;
                if(!checkIsland[i] && connectionIsland[now][i]) {
                    checkIsland[i] = true;
                    q.offer(i);
                    cnt++;
                }
            }
        }

        return false;
    }

    public static void select(int idx, int cnt, int sum) {
        if(cnt >= 1) {
            if(isAvailable()) {
                answer = Math.min(answer, sum);
            }
        }

        for(int i=idx; i<bridges.size(); i++) {
            if(check[i]) continue;

            check[i] = true;
            select(i, cnt + 1, sum + bridges.get(i)[2]);
            check[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean[n][m];
        map = new int[n][m];
        dist = new int[7][7];

        int numbering = 1;

        for(int i=0; i<7; i++) Arrays.fill(dist[i], INF);
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(map[i][j] == 1 && !visited[i][j]) {
                    search(i, j, numbering++);
                    count++;
                }
            }
        }
        for(int i=1; i<count; i++) {
            for(int j=i+1; j<=count; j++) {
                build(i, j);

                if(dist[i][j] == INF) continue;
                bridges.add(new int[]{i, j, dist[i][j]});
            }
        }

        check = new boolean[count+1];
        select(0, 0, 0);

        System.out.println(answer == INF ? -1 : answer);
    }
}