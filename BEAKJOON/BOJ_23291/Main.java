package BEAKJOON.BOJ_23291;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static final int[] dx = {-1, 1, 0, 0};
    public static final int[] dy = {0, 0, -1, 1};

    public static int n, k;
    public static int[][] fishbowls;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        fishbowls = new int[n+1][n+1];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i<=n; i++) {
            fishbowls[n][i] = Integer.parseInt(st.nextToken());
        }
    }

    public static int getOpposite(int d) {

        if(d == 0) return 1;
        if(d == 1) return 0;
        if(d == 2) return 3;
        return 2;
    }

    public static void makeFlatten() {

        int min = Integer.MAX_VALUE;

        for(int i=1; i<=n; i++) {
            min = Math.min(min, fishbowls[n][i]);
        }
        for(int i=1; i<=n; i++) {
            if(fishbowls[n][i] == min) {
                fishbowls[n][i]++;
            }
        }
    }

    public static void firstFold() {

        int pos = 1, height = 1, width = 1;

        while(pos + width + height - 1 <= n) {
            int h = height;
            int w = width;

            for(int i=n-w; i<n; i++) {
                for(int j=pos+w; j<pos+w+h; j++) {
                    fishbowls[i][j] = fishbowls[n+pos+w-j][pos+i-(n-w)];
                    fishbowls[n+pos+w-j][pos+i-(n-w)] = 0;
                }
            }

            pos += width;
            width = h;
            height = w + 1;
        }
    }

    public static void numberControl() {

        int[][] controller = new int[n+1][n+1];
        boolean[][][] visited = new boolean[n+1][n+1][4];

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                if(fishbowls[i][j] > 0) {
                    for(int k=0; k<4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if(nx <= 0 || nx > n || ny <= 0 || ny > n) continue;
                        if(fishbowls[nx][ny] == 0) continue;
                        if(visited[i][j][k]) continue;

                        int d = Math.abs(fishbowls[nx][ny] - fishbowls[i][j]) / 5;
                        if(d > 0) {
                            if(fishbowls[nx][ny] > fishbowls[i][j]) {
                                controller[nx][ny] -= d;
                                controller[i][j] += d;
                            }else {
                                controller[i][j] -= d;
                                controller[nx][ny] += d;
                            }
                            visited[i][j][k] = true;
                            visited[nx][ny][getOpposite(k)] = true;
                        }
                    }
                }
            }
        }
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                fishbowls[i][j] += controller[i][j];
            }
        }
    }

    public static void unwrap() {

        int pos = 1;

        for(int i=1; i<=n; i++) {
            if(fishbowls[n][i] > 0) {
                int h = n;

                while(fishbowls[h][i] != 0) {
                    fishbowls[n][pos++] = fishbowls[h--][i];
                    if(h < n - 1) fishbowls[h+1][i] = 0;
                }
            }
        }
    }

    public static void secondFold() {

        int low = 1, high = n;
        int T = 0, m = n;

        while(T++ < 2) {
            int mid = (high + low) / 2;

            for(int j=0; j<T; j++) {
                for(int i=mid+1; i<=n; i++) {
                    fishbowls[n-(j+T)][i] = fishbowls[m+j][mid+1-(i-mid)];
                    fishbowls[m+j][mid+1-(i-mid)] = 0;
                }
            }

            low = mid + 1;
            m--;
        }
    }

    public static boolean fin() {

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i=1; i<=n; i++) {
            min = Math.min(min, fishbowls[n][i]);
            max = Math.max(max, fishbowls[n][i]);
        }

        return max - min <= k;
    }

    public static void organize() {

        int answer = 0;

        while(true) {
            answer++;

            makeFlatten();
            firstFold();
            numberControl();
            unwrap();
            secondFold();
            numberControl();
            unwrap();

            if(fin()) break;
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {

        input();
        organize();

    }
}