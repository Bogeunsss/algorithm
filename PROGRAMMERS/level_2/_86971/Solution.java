package PROGRAMMERS.level_2._86971;

import java.util.*;

public class Solution {

    private int n;
    private int[] parents;
    private int[][] wires;

    private int find(int x) {
        if(parents[x] == x) return x;
        return find(parents[x]);
    }

    private void union(int _x, int _y) {
        int x = find(_x);
        int y = find(_y);

        if(y < x) {
            int t = x;
            x = y;
            y = t;
        }
        if(x != y) parents[y] = x;
    }

    public int cut(int index) {
        int ret = Integer.MAX_VALUE;

        while(index++ < n - 1) {
            int count = 0;

            for(int i=1; i<=n; i++) parents[i] = i;
            for(int i=0; i<n-1; i++) {
                if(i == index) continue;
                union(wires[i][0], wires[i][1]);
            }
            for(int i=1; i<=n; i++) {
                if(find(parents[i]) == 1) count++;
            }

            ret = Math.min(ret, Math.abs(n - count * 2));
        }

        return ret;
    }

    public int solution(int n, int[][] wires) {
        this.n = n;
        this.wires = wires;

        parents = new int[n+1];

        return cut(-1);
    }
}
