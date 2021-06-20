package BEAKJOON.BOJ_1436;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int number = 666, idx = 0;

        while(idx != N) {
            if(String.valueOf(number).contains("666")) idx++;
            number++;
        }
        System.out.println(number - 1);
    }
}