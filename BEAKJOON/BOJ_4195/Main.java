package BEAKJOON.BOJ_4195;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static int[] parent;
    public static int[] answer;

    public static int union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x != y) {
            parent[y] = x;
            answer[x] += answer[y];
        }
        return answer[x];
    }

    public static int find(int x) {
        if(x == parent[x]) return parent[x];
        return parent[x] = find(parent[x]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        HashMap<String,Integer> indies;

        for(int tc=0; tc<T; tc++) {
            int F = Integer.parseInt(br.readLine());
            indies = new HashMap<>();
            parent = new int[F*2];
            answer = new int[F*2];
            int index = 0;

            for(int i=0; i<F*2; i++) {
                parent[i] = i;
                answer[i] = 1;
            }
            for(int i=0; i<F; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                String a = st.nextToken();
                String b = st.nextToken();

                if(!indies.containsKey(a)) {
                    indies.put(a, index++);
                }
                if(!indies.containsKey(b)) {
                    indies.put(b, index++);
                }
                sb.append(union(indies.get(a), indies.get(b))).append("\n");
            }
        }
        System.out.println(sb);
    }
}