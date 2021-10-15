package BEAKJOON.BOJ_5585;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int money = 1000 - Integer.parseInt(br.readLine());
        int[] changes = {500, 100, 50, 10, 5, 1};
        int answer = 0;

        for(int i=0; i<6; i++) {
            while(changes[i] <= money) {
                money -= changes[i];
                answer++;
            }
        }

        System.out.println(answer);
    }
}
