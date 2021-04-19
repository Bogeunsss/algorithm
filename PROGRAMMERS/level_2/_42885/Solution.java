package PROGRAMMERS.level_2._42885;

import java.util.Arrays;

public class Solution {
    public int solution(int[] people, int limit) {
        int low = 0, high = people.length - 1;

        Arrays.sort(people);
        while(low < high) {
            if(people[low] + people[high] <= limit) low++;
            high--;
        }

        return people.length - low;
    }
}
