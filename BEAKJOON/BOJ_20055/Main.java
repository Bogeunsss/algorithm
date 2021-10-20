package BEAKJOON.BOJ_20055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int[] belt, robots;
    public static int n, k;
    public static int answer = 0;

    public static boolean fin() {
        int cnt = 0;

        for(int i=0; i<n*2; i++) {
            if(belt[i] == 0) cnt++;
            if(cnt >= k) return true;
        }
        return false;
    }

    public static void rotate() {

        int backup = belt[n*2-1];

        for(int i=n*2-1; i>0; i--) {
            belt[i] = belt[i-1];
            robots[i] = robots[i-1];
        }
        belt[0] = backup;
        robots[0] = robots[n] = 0;
    }

    public static void move() {

        for(int i=n; i>0; i--) {
            if(robots[i] == 1) continue;
            if(belt[i] == 0) {
                if(i < n) continue;
            }
            if(robots[i-1] == 1) {
                int temp = robots[i];
                robots[i] = robots[i-1];
                robots[i-1] = temp;
                if(i < n) belt[i]--;
            }
        }
        robots[n] = 0;
    }

    public static void carry() {

        while(!fin()) {
            rotate();
            move();

            if(belt[0] > 0) {
                robots[0] = 1;
                belt[0]--;
            }
            answer++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        belt = new int[n*2];
        robots = new int[n*2];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<n*2; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }

        carry();

        System.out.println(answer);
    }
}