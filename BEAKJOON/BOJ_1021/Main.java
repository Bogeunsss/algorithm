package BEAKJOON.BOJ_1021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] target = new int[M];
        Deque<Integer> deq = new ArrayDeque<>();
        int answer = 0, idx = 0;

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<M; i++) target[i] = Integer.parseInt(st.nextToken());
        for(int i=1; i<=N; i++) deq.offer(i);
        while(!deq.isEmpty()) {
            if(target[idx] == deq.peek()) {
                deq.poll();
                idx++;
                if(idx == M) break;
            }else {
                int left = 0, right = 0, move = 0;
                int size = deq.size();
                while(size-- > 0) {
                    if(!deq.isEmpty() && deq.peek() == target[idx]) left = move;
                    deq.offer(deq.poll());
                    move++;
                }
                size = deq.size();
                move = 0;
                while(size-- > 0) {
                    if(!deq.isEmpty() && deq.peek() == target[idx]) right = move;
                    deq.offerFirst(deq.pollLast());
                    move++;
                }
                if(left < right) {
                    for(int i=0; i<left; i++) {
                        deq.offer(deq.poll());
                    }
                    answer += left;
                }else {
                    for(int i=0; i<right; i++) {
                        deq.offerFirst(deq.pollLast());
                    }
                    answer += right;
                }
            }
        }
        System.out.println(answer);
    }
}