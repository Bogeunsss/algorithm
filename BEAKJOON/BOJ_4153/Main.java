package BEAKJOON.BOJ_4153;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true) {
            int[] sides = new int[3];
            int quit = 0;
            for(int i=0; i<3; i++) {
                int n = sc.nextInt();
                sides[i] = n;
                quit += n;
            }
            if(quit == 0) break;

            Arrays.sort(sides);
            if(Math.pow(sides[0], 2) + Math.pow(sides[1], 2) == Math.pow(sides[2], 2)) {
                System.out.println("right");
            }else {
                System.out.println("wrong");
            }
        }
    }
}