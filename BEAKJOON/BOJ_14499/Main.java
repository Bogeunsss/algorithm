package BEAKJOON.BOJ_14499;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static final int[] dy = {0, 0, -1, 1};
    public static final int[] dx = {1, -1, 0, 0};

    public static int[] dice = new int[7];

    public static int[] roll(int cmd) {
        int[] ret = dice.clone();

        switch(cmd) {
            case 0:
                ret[1] = dice[4];
                ret[3] = dice[1];
                ret[4] = dice[6];
                ret[6] = dice[3];
                break;
            case 1:
                ret[1] = dice[3];
                ret[3] = dice[6];
                ret[4] = dice[1];
                ret[6] = dice[4];
                break;
            case 2:
                ret[1] = dice[5];
                ret[2] = dice[1];
                ret[5] = dice[6];
                ret[6] = dice[2];
                break;
            case 3:
                ret[1] = dice[2];
                ret[2] = dice[6];
                ret[5] = dice[1];
                ret[6] = dice[5];
                break;
        }

        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder answer = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            for(int j=0; j<m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<k; i++) {
            int cmd = Integer.parseInt(st.nextToken()) - 1;
            int ny = y + dy[cmd];
            int nx = x + dx[cmd];

            if(nx < 0 || nx >= m || ny < 0 || ny >= n) continue;

            dice = roll(cmd);

            if(map[ny][nx] == 0) {
                map[ny][nx] = dice[6];
            }else {
                dice[6] = map[ny][nx];
                map[ny][nx] = 0;
            }

            y = ny;
            x = nx;
            answer.append(dice[1]).append("\n");
        }

        System.out.println(answer);
    }
}