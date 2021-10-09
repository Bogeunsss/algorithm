package BEAKJOON.BOJ_1967;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static final int INF = Integer.MAX_VALUE;

    public static List<List<int[]>> tree = new ArrayList<>();
    public static int[] d;
    public static int n;

    public static int diameter(int src, boolean inner) {
        Queue<Integer> q = new LinkedList<>();
        d = new int[n + 1];
        int max = 0, idx = 0;

        q.offer(src);
        d[src] = 1;
        while(!q.isEmpty()) {
            int now = q.poll();

            for(int[] node : tree.get(now)) {
                int next = node[0];
                int weight = node[1];

                if(d[next] > 0) continue;
                d[next] = d[now] + weight;
                q.offer(next);
            }
        }

        for(int i=1; i<=n; i++) {
            if(max < d[i]) {
                max = d[i];
                idx = i;
            }
        }

        if(inner) return idx;
        return max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        for(int i=0; i<=n; i++) tree.add(new ArrayList<>());
        for(int i=0; i<n-1; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            tree.get(x).add(new int[]{y, z});
            tree.get(y).add(new int[]{x, z});
        }

        System.out.println(diameter(diameter(1, true), false) - 1);
    }
}