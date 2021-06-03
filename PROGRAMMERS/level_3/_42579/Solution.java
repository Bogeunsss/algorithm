package PROGRAMMERS.level_3._42579;

import java.util.*;

public class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String,List<List<Integer>>> map = new HashMap<>();

        for(int i=0; i<plays.length; i++) {
            List<Integer> temp = new ArrayList<>();
            temp.add(i);
            temp.add(plays[i]);
            map.compute(genres[i], (k, v) -> v == null ? new ArrayList<>() : v).add(temp);
        }
        for(String key : map.keySet()) {
            map.get(key).sort((o1, o2) -> o2.get(1) - o1.get(1));
        }
        List<String> list = new ArrayList<>(map.keySet());
        list.sort((o1, o2) -> {
            int sum1 = 0, sum2 = 0;
            for (List<Integer> l1 : map.get(o1)) {
                sum1 += l1.get(1);
            }
            for (List<Integer> l2 : map.get(o2)) {
                sum2 += l2.get(1);
            }

            return sum2 - sum1;
        });

        List<Integer> indices = new ArrayList<>();
        for(String genre : list) {
            if(map.get(genre).size() == 1) {
                indices.add(map.get(genre).get(0).get(0));
            }else {
                for(int i=0; i<2; i++) {
                    indices.add(map.get(genre).get(i).get(0));
                }
            }
        }
        int[] answer = new int[indices.size()];
        for(int i=0; i<indices.size(); i++) answer[i] = indices.get(i);

        return answer;
    }
}
