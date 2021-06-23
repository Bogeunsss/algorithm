package BEAKJOON.BOJ_10814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<String[]> pq = new PriorityQueue<>(new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                if(Integer.parseInt(o1[1]) == Integer.parseInt(o2[1])) return Integer.parseInt(o1[0]) -Integer.parseInt(o2[0]);
                return Integer.parseInt(o1[1]) - Integer.parseInt(o2[1]);
            }
        });

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String age = st.nextToken();
            String name = st.nextToken();

            pq.offer(new String[]{Integer.toString(i), age, name});
        }

        while(!pq.isEmpty()) {
            String[] user = pq.poll();
            System.out.println(user[1] + " " + user[2]);
        }
    }
}
