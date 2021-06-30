package BEAKJOON.BOJ_1655;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> min = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        PriorityQueue<Integer> max = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        for(int i=0; i<N; i++) {
            int x = Integer.parseInt(br.readLine());

            if(min.size() == max.size()) max.offer(x);
            else min.offer(x);

            if(!min.isEmpty() && !max.isEmpty()) {
                if(min.peek() < max.peek()) {
                    int swap = min.poll();
                    min.offer(max.poll());
                    max.offer(swap);
                }
            }
            sb.append(max.peek()).append("\n");
        }
        System.out.println(sb);
    }
}