package PROGRAMMERS.level_2._42890;

import java.util.*;

public class Solution {
    public static boolean isMinimum(int set, List<Integer> keys) {
        for(int key : keys) {
            if((set & key) == key) return false;
        }
        return true;
    }

    public static boolean isUnique(int set, int row, int col, List<Integer> keys, String[][] relation) {
        HashMap<String, String> map = new HashMap<>();

        for(int i=0; i<row; i++) {
            String data = "";
            for(int j=0; j<col; j++) {
                if((set & (1<<j)) != 0) data += relation[i][j];
            }
            if(map.containsKey(data)) return false;
            else map.put(data, data);
        }
        return true;
    }

    public int solution(String[][] relation) {
        List<Integer> set = new ArrayList<>();
        int row = relation.length;
        int col = relation[0].length;

        for(int i=1; i<(1<<col); i++) {
            if(!isMinimum(i, set)) continue;
            if(isUnique(i, row, col, set, relation)) {
                set.add(i);
            }
        }
        return set.size();
    }
}
