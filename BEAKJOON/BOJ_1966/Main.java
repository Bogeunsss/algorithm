package BEAKJOON.BOJ_1966;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for(int tc=0; tc<T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            Queue<int[]> q = new LinkedList<>();
            PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
            int answer = 1;

            st = new StringTokenizer(br.readLine(), " ");
            for(int i=0; i<N; i++) {
               int document = Integer.parseInt(st.nextToken());
               pq.offer(document);
               q.offer(new int[]{document, i});
            }
            while(!q.isEmpty() && !pq.isEmpty()) {
                int[] document = q.peek();
                if(pq.peek() == document[0]) {
                    if(M == document[1]) {
                        sb.append(answer).append("\n");
                        break;
                    }
                    pq.poll();
                    q.poll();
                    answer++;
                }else {
                    q.offer(q.poll());
                }
            }
        }
        System.out.println(sb);
    }
}