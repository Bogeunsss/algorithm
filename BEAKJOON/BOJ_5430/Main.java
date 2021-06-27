package BEAKJOON.BOJ_5430;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int tc=0; tc<T; tc++) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            Deque<Integer> d1 = new ArrayDeque<>();
            Deque<Integer> d2 = new ArrayDeque<>();
            boolean dir = true, error = false;

            String s = br.readLine();
            String[] arr = s.substring(1,s.length()-1).split(",");
            for(int i=0; i<n; i++) {
                d1.offer(Integer.parseInt(arr[i]));
                d2.offer(Integer.parseInt(arr[n-i-1]));
            }
            for(int i=0; i<p.length(); i++) {
                char command = p.charAt(i);
                if(command == 'R') {
                    dir = !dir;
                }else if(command == 'D') {
                    if(d1.isEmpty()) {
                        error = true;
                        break;
                    }else {
                        if(dir) {
                            d1.poll();
                            d2.pollLast();
                        }else {
                            d1.pollLast();
                            d2.poll();
                        }
                    }
                }
            }
            if(error) {
                sb.append("error").append("\n");
            }else {
                sb.append("[");
                if(dir) {
                    while(!d1.isEmpty()) {
                        sb.append(d1.poll());
                        if(!d1.isEmpty()) sb.append(",");
                    }
                }else {
                    while(!d2.isEmpty()) {
                        sb.append(d2.poll());
                        if(!d2.isEmpty()) sb.append(",");
                    }
                }
                sb.append("]").append("\n");
            }
        }
        System.out.println(sb);
    }
}