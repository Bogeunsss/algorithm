package BEAKJOON.BOJ_14891;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void rotate(int[] gear, int d) {
        int temp;

        if(d == 1) {
            temp = gear[7];
            for(int i=7; i>0; i--) {
                gear[i] = gear[i-1];
            }
            gear[0] = temp;
        }else if(d == -1) {
            temp = gear[0];
            for(int i=0; i<7; i++) {
                gear[i] = gear[i+1];
            }
            gear[7] = temp;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] gears = new int[4][8];

        for(int i=0; i<4; i++) {
            String gear = br.readLine();
            int index = 0;

            for(int j=6; j<8; j++) {
                gears[i][index++] = gear.charAt(j) - '0';
            }
            for(int j=0; j<6; j++) {
                gears[i][index++] = gear.charAt(j) - '0';
            }
        }

        int k = Integer.parseInt(br.readLine());
        boolean[] contact = new boolean[4];
        StringTokenizer st;

        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int g = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());

            Arrays.fill(contact, false);

            contact[g] = true;
            for(int j=g; j<3; j++) {
                if(gears[j][4] != gears[j+1][0] && contact[j]) {
                    contact[j+1] = true;
                }
            }
            for(int j=g; j>0; j--) {
                if(gears[j-1][4] != gears[j][0] && contact[j]) {
                    contact[j-1] = true;
                }
            }
            for(int j=0; j<4; j++) {
                if(contact[j]) {
                    if(g % 2 == 0) {
                        rotate(gears[j], j % 2 == 0 ? d : -d);
                    }else {
                        rotate(gears[j], j % 2 == 1 ? d : -d);
                    }
                }
            }
        }

        int answer = 0;
        int score = 1;

        for(int i=0; i<4; i++) {
            if(gears[i][2] == 1) answer += score;
            score *= 2;
        }

        System.out.println(answer);
    }
}