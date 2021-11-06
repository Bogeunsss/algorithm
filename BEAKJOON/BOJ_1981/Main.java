package BEAKJOON.BOJ_1981;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static final int[] dx = {-1, 1, 0, 0};
    public static final int[] dy = {0, 0, -1, 1};

    public static int n, min, max;
    public static int[][] arr;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        min = Integer.MAX_VALUE;
        max = 0;

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                min = Math.min(min, arr[i][j]);
                max = Math.max(max, arr[i][j]);
            }
        }
    }

    public static boolean isAvailableRoutes(int mid) {

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited;

        for(int i=min; i<=max; i++) {
            visited = new boolean[n][n];

            q.offer(new int[]{0, 0});
            for(int j=0; j<n; j++) {
                for(int k=0; k<n; k++) {
                    if(arr[j][k] < i || arr[j][k] > i + mid) visited[j][k] = true;
                }
            }

            while(!q.isEmpty()) {
                int x = q.peek()[0];
                int y = q.peek()[1];
                q.poll();

                if(visited[x][y]) continue;
                if(x == n - 1 && y == n - 1) return true;

                visited[x][y] = true;
                for(int j=0; j<4; j++) {
                    int nx = x + dx[j];
                    int ny = y + dy[j];

                    if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                    q.offer(new int[]{nx, ny});
                }
            }
        }

        return false;
    }

    public static void calculateDistance() {

        int low = 0, high = max - min;
        int mid;

        while(low <= high) {
            mid = (low + high) / 2;

            if(isAvailableRoutes(mid)) high = mid - 1;
            else low = mid + 1;
        }

        System.out.println(high + 1);
    }

    public static void main(String[] args) throws IOException {

        input();
        calculateDistance();
    }
}
