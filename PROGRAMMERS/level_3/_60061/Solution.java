package PROGRAMMERS.level_3._60061;

import java.util.*;

public class Solution {

    public static boolean impossible(Set<List<Integer>> result) {
        int ROW = 1, COL = 0;

        for(List<Integer> res : result) {
            int x = res.get(0);
            int y = res.get(1);
            int a = res.get(2);

            if(a == COL) {
                if(y != 0
                        && !result.contains(new ArrayList<>(Arrays.asList(x, y-1, COL)))
                        && !result.contains(new ArrayList<>(Arrays.asList(x-1, y, ROW)))
                        && !result.contains(new ArrayList<>(Arrays.asList(x, y, ROW)))) {
                    return true;
                }
            }else {
                if(!result.contains(new ArrayList<>(Arrays.asList(x, y-1, COL)))
                        && !result.contains(new ArrayList<>(Arrays.asList(x+1, y-1, COL)))
                        && !(result.contains(new ArrayList<>(Arrays.asList(x-1, y, ROW)))
                        && result.contains(new ArrayList<>(Arrays.asList(x+1, y, ROW))))) {
                    return true;
                }
            }
        }
        return false;
    }

    public int[][] solution(int n, int[][] build_frame) {
        Set<List<Integer>> result = new HashSet<>();

        for(int[] build : build_frame) {
            int x = build[0];
            int y = build[1];
            int a = build[2];
            int b = build[3];
            List<Integer> item = new ArrayList<>(Arrays.asList(x, y, a));

            if(b == 1) {
                result.add(item);
                if(impossible(result)) {
                    result.remove(item);
                }
            }else if(b == 0 && result.contains(item)) {
                result.remove(item);
                if(impossible(result)) {
                    result.add(item);
                }
            }
        }

        List<List<Integer>> res = new ArrayList<>(result);
        res.sort(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                if(o1.get(0).equals(o2.get(0))) return o1.get(1) - o2.get(1);
                return o1.get(0) - o2.get(0);
            }
        });

        int[][] answer = new int[res.size()][3];
        for(int i=0; i<res.size(); i++) {
            for(int j=0; j<3; j++) {
                answer[i][j] = res.get(i).get(j);
            }
        }

        return answer;
    }
}
