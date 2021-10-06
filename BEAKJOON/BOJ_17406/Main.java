package BEAKJOON.BOJ_17406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static List<int[]> commands = new ArrayList<>();
    public static Stack<int[]> stack = new Stack<>();

    public static boolean[] visited;
    public static int[][] arr;
    public static int n, m, k;
    public static int answer = Integer.MAX_VALUE;

    public static void getValue(int[][] sample) {
        for(int i=0; i<n; i++) {
            int sum = 0;

            for(int j=0; j<m; j++) {
                sum += sample[i][j];
            }
            answer = Math.min(answer, sum);
        }
    }

    public static void rotate(int[][] sample, int r, int c, int s) {
        int len = s + 1;

        while(len-- > 1) {
            int temp = sample[r-len][c-len];

            for(int i=0; i<2*len; i++) {
                sample[r-len+i][c-len] = sample[r-len+i+1][c-len];
            }
            for(int i=0; i<2*len; i++) {
                sample[r+len][c-len+i] = sample[r+len][c-len+i+1];
            }
            for(int i=2*len; i>0; i--) {
                sample[r-len+i][c+len] = sample[r-len+i-1][c+len];
            }
            for(int i=2*len; i>0; i--) {
                sample[r-len][c-len+i] = sample[r-len][c-len+i-1];
            }
            sample[r-len][c-len+1] = temp;
        }
    }

    public static void select(int depth) {
        if(depth >= k) {
            Stack<int[]> backup = new Stack<>();
            int[][] sample = new int[n][m];

            for(int i=0; i<n; i++) {
                for(int j=0; j<m; j++) {
                    sample[i][j] = arr[i][j];
                }
            }
            while(!stack.isEmpty()) {
                int[] command = stack.pop();
                int r = command[0];
                int c = command[1];
                int s = command[2];

                rotate(sample, r, c, s);

                backup.push(command);
            }
            while(!backup.isEmpty()) {
                stack.push(backup.pop());
            }

            getValue(sample);
            return;
        }

        for(int i=0; i<k; i++) {
            if(visited[i]) continue;

            visited[i] = true;
            stack.push(commands.get(i));
            select(depth + 1);
            stack.pop();
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        visited = new boolean[k];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            for(int j=0; j<m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            commands.add(new int[]{r, c, s});
        }

        select(0);

        System.out.println(answer);
    }
}