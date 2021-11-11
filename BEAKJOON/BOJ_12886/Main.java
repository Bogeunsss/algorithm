package BEAKJOON.BOJ_12886;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static int a, b, c;
    public static int sum;
    public static boolean[][] d;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        sum = a + b + c;

        d = new boolean[sum+1][sum+1];
    }

    public static void stoneGroup() {

        if(sum % 3 > 0) {
            System.out.println(0);
            return;
        }

        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{a, b});
        d[a][b] = true;

        while(!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.peek()[1];
            q.poll();

            int[] stones = {x, y, sum - x - y};
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    if(i == j) continue;
                    if(stones[i] < stones[j]) {
                        int nx = stones[i] * 2;
                        int ny = stones[j] - stones[i];

                        if(!d[nx][ny]) {
                            d[nx][ny] = true;
                            q.offer(new int[]{nx, ny});
                        }
                    }
                }
            }
        }

        System.out.println(d[sum/3][sum/3] ? 1 : 0);
    }

    public static void main(String[] args) throws IOException {

        input();
        stoneGroup();
    }
}