package BEAKJOON.BOJ_1931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] I = new int[N][2];
        int answer = 0;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            I[i][0] = Integer.parseInt(st.nextToken());
            I[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(I, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]) return o1[0] - o2[0];
                return o1[1] - o2[1];
            }
        });

        int end = 0;
        for(int i=0; i<N; i++) {
            if(end <= I[i][0]) {
                end = I[i][1];
                answer++;
            }
        }
        System.out.println(answer);
    }
}