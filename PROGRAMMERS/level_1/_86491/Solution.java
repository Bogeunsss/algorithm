package PROGRAMMERS.level_1._86491;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

    public int solution(int[][] sizes) {
        List<Integer> row = new ArrayList<>();
        List<Integer> col = new ArrayList<>();

        for(int[] size : sizes) {
            if(size[0] > size[1]) {
                row.add(size[0]);
                col.add(size[1]);
            }else {
                row.add(size[1]);
                col.add(size[0]);
            }
        }

        Collections.sort(row);
        Collections.sort(col);

        return row.get(row.size()-1) * col.get(col.size()-1);
    }
}
