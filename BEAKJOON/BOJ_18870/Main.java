package BEAKJOON.BOJ_18870;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder answer = new StringBuilder();
        HashMap<Integer,Integer> map = new HashMap<>();
        int[] origin = new int[N];
        int[] processing = new int[N];
        int index = 0;

        for(int i=0; i<N; i++) {
            origin[i] = Integer.parseInt(st.nextToken());
            processing[i] = origin[i];
        }
        Arrays.sort(processing);

        for(int process : processing) {
            if(!map.containsKey(process)) map.put(process, index++);
        }
        for(int i=0; i<N; i++) {
            answer.append(map.get(origin[i])).append(" ");
        }
        System.out.println(answer);
    }
}