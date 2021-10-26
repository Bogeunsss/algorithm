package BEAKJOON.BOJ_21608;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static final int[] dx = {-1, 1, 0, 0};
    public static final int[] dy = {0, 0, -1, 1};

    public static int[][] map, relations;
    public static int[] sequence;
    public static int n;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        relations = new int[n*n+1][4];
        map = new int[n+1][n+1];
        sequence = new int[n*n];

        for(int i=1; i<=n*n; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int idx = Integer.parseInt(st.nextToken());
            for(int j=0; j<4; j++) relations[idx][j] = Integer.parseInt(st.nextToken());
            sequence[i-1] = idx;
        }

    }

    public static boolean isFriend(int friend, int people) {

        for(int i=0; i<4; i++) {
            if(relations[friend][i] == people) return true;
        }
        return false;
    }

    public static void verify(int friend) {

        int max = -1, vacancy = -1;
        int x = 0, y = 0;

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                if(map[i][j] > 0) continue;
                int friendCount = 0;
                int vacancyCount = 0;

                for(int k=0; k<4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if(nx <= 0 || nx > n || ny <= 0 || ny > n) continue;
                    if(map[nx][ny] == 0) vacancyCount++;
                    else {
                        if(isFriend(friend, map[nx][ny])) friendCount++;
                    }
                }

                if(max < friendCount) {
                    max = friendCount;
                    vacancy = vacancyCount;
                    x = i;
                    y = j;
                }else if(max == friendCount) {
                    if(vacancy < vacancyCount) {
                        vacancy = vacancyCount;
                        x = i;
                        y = j;
                    }
                }
            }
        }

        map[x][y] = friend;
    }

    public static void satisfied() {

        int answer = 0;

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                int cnt = 0;

                for(int k=0; k<4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if(nx <= 0 || nx > n || ny <= 0 || ny > n) continue;
                    if(isFriend(map[i][j], map[nx][ny])) cnt++;
                }

                answer += (int) Math.pow(10, cnt - 1);
            }
        }

        System.out.println(answer);
    }

    public static void select() {

        map[2][2] = sequence[0];

        for(int i=1; i<n*n; i++) {
            verify(sequence[i]);
        }
        satisfied();

    }

    public static void main(String[] args) throws IOException {

        input();
        select();
    }
}