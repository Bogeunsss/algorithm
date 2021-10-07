package BEAKJOON.BOJ_14719;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int[][] map = new int[h+1][w+1];
        int answer = 0;

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i<=w; i++) {
            int b = Integer.parseInt(st.nextToken());

            for(int j=h-b; j>0; j--) {
                map[j][i]++;
            }
        }

        for(int i=h; i>0; i--) {
            int cnt = 0;
            boolean containable = false;

            for(int j=1; j<=w; j++) {
                if(map[i][j] == 0) {
                    if(!containable) {
                        containable = true;
                    }else {
                        if(cnt > 0) {
                            answer += cnt;
                            cnt = 0;
                        }
                    }
                }else {
                    if(containable) cnt++;
                }
            }
        }

        System.out.println(answer);
    }
}
