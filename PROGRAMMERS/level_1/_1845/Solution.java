package PROGRAMMERS.level_1._1845;

import java.util.*;

public class Solution {
    public int solution(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for(int num : nums) set.add(num);

        return Math.min(set.size(), nums.length/2);
    }
}
