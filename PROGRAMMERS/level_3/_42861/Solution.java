package PROGRAMMERS.level_3._42861;

import java.util.*;

public class Solution {
    static int[] island;

    public static int find(int x) {
        if(x == island[x]) return x;
        return island[x] = find(island[x]);
    }

    public static boolean merge(int a, int b) {
        a = find(a);
        b = find(b);
        if(a == b) return false;
        if(a > b) island[a] = b;
        else island[b] = a;
        return true;
    }

    public int solution(int n, int[][] costs) {
        Arrays.sort(costs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        int answer = 0;
        island = new int[n];

        for(int i=0; i<n; i++) island[i] = i;
        for (int[] cost : costs) {
            if (merge(cost[0], cost[1])) answer += cost[2];
        }
        return answer;
    }
}
