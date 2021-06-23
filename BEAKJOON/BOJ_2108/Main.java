package BEAKJOON.BOJ_2108;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        HashMap<Integer,Integer> map = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        double sum = 0;

        for(int i=0; i<N; i++) {
            int n = Integer.parseInt(br.readLine());
            list.add(n);
            map.put(n, map.getOrDefault(n, 0) + 1);
            sum += n;
        }
        Collections.sort(list);

        List<Integer> keys = new ArrayList<>();
        int max = 0;
        for(int key : map.keySet()) {
            max = Math.max(max, map.get(key));
        }
        for(int key : map.keySet()) {
            if(max == map.get(key)) {
                keys.add(key);
            }
        }
        Collections.sort(keys);

        sb.append(Math.round(sum / N)).append("\n");
        sb.append(list.get(N/2)).append("\n");
        sb.append(keys.get(keys.size() > 1 ? 1 : keys.size() - 1)).append("\n");
        sb.append(list.get(N-1) - list.get(0)).append("\n");
        System.out.println(sb);
    }
}