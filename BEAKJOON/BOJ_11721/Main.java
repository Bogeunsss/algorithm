package BEAKJOON.BOJ_11721;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String s = br.readLine();
        int index = 1;

        while(index < s.length()) {
            sb.append(s.charAt(index-1));

            if(index % 10 == 0) sb.append("\n");

            index++;
        }
        sb.append(s.charAt(index-1));

        System.out.println(sb);
    }
}