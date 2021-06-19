package BEAKJOON.BOJ_3009;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<Integer,Integer> x = new HashMap<>();
        HashMap<Integer,Integer> y = new HashMap<>();
        int[] answer = new int[2];

        for(int i=0; i<3; i++) {
            int _x = sc.nextInt();
            int _y = sc.nextInt();
            x.put(_x, x.getOrDefault(_x, 0) + 1);
            y.put(_y, y.getOrDefault(_y, 0) + 1);
        }
        for(int key : x.keySet()) {
            if(x.get(key) == 1) answer[0] = key;
        }
        for(int key : y.keySet()) {
            if(y.get(key) == 1) answer[1] = key;
        }
        System.out.println(answer[0] + " " + answer[1]);
    }
}