package PROGRAMMERS.level_2._17680;

import java.util.*;

public class Solution {
    public int solution(int cacheSize, String[] cities) {
        Queue<String> cache = new LinkedList<>();
        int answer = 0;

        if(cacheSize == 0) return cities.length * 5;
        for(String city : cities) {
            city = city.toUpperCase();
            if(cache.contains(city)) {
                if(cache.remove(city)) {
                    cache.add(city);
                    answer++;
                }
            }else {
                if(cache.size() >= cacheSize) cache.poll();
                cache.add(city);
                answer += 5;
            }
        }
        return answer;
    }
}
