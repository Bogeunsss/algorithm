package PROGRAMMERS.level_2._81302;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static int manhattan(int[] x, int[] y) {
        return Math.abs(x[0] - y[0]) + Math.abs(x[1] - y[1]);
    }

    public static boolean isDistancing(String[] place, int r1, int r2, int c1, int c2) {
        for(int i=r2; i<=r1; i++) {
            for(int j=c2; j<=c1; j++) {
                if(place[i].charAt(j) == 'O') return false;
            }
        }
        return true;
    }

    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        int index = 0;

        for(String[] place : places) {
            List<int[]> locations = new ArrayList<>();
            for(int i=0; i<5; i++) {
                for(int j=0; j<5; j++) {
                    if(place[i].charAt(j) == 'P') {
                        locations.add(new int[]{i, j});
                    }
                }
            }
            boolean isAvailable = true;
            for(int i=0; i<locations.size()-1; i++) {
                int[] p1 = locations.get(i);
                for(int j=i+1; j<locations.size(); j++) {
                    int[] p2 = locations.get(j);
                    if(manhattan(p1, p2) == 2) {
                        int r1 = Math.max(p1[0], p2[0]);
                        int r2 = Math.min(p1[0], p2[0]);
                        int c1 = Math.max(p1[1], p2[1]);
                        int c2 = Math.min(p1[1], p2[1]);

                        if(!isDistancing(place, r1, r2, c1, c2)) {
                            isAvailable = false;
                            break;
                        }
                    }else if(manhattan(p1, p2) < 2) {
                        isAvailable = false;
                        break;
                    }
                }
                if(!isAvailable) break;
            }
            answer[index++] = isAvailable ? 1 : 0;
        }
        return answer;
    }
}
