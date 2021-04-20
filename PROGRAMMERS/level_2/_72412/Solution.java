package PROGRAMMERS.level_2._72412;

import java.util.*;

public class Solution {
    static Map<String, List<Integer>> map;
    static int[] answer;

    public static void dfs(String[] info, String str, int depth) {
        if(depth == 4) {
            if(!map.containsKey(str)) {
                List<Integer> list = new ArrayList<>();
                list.add(Integer.parseInt(info[4]));
                map.put(str, list);
            }else {
                map.get(str).add(Integer.parseInt(info[4]));
            }
        }else {
            dfs(info, str, depth + 1);
            dfs(info, str + info[depth], depth + 1);
        }
    }

    public static void setInfo(String[] info) {
        for(String i : info) {

            dfs(i.split(" "), "", 0);
        }

        for (String key : map.keySet()) {
            List<Integer> list = map.get(key);
            Collections.sort(list);
        }
    }

    public static int count(String str, int score) {
        if(!map.containsKey(str)) return 0;

        List<Integer> list = map.get(str);
        int start = 0, end = list.size() - 1;

        while(start <= end) {
            int mid = (start + end) / 2;
            if(list.get(mid) < score) start = mid + 1;
            else end = mid - 1;
        }
        return list.size() - start;
    }

    public static void process(String[] query) {
        int index = 0;

        for(String q : query) {
            StringBuilder str = new StringBuilder();
            String[] arr = q.split(" ");

            for(int i=0; i<arr.length-1; i++) {
                if(arr[i].equals("and") || arr[i].equals("-")) continue;
                str.append(arr[i]);
            }
            answer[index++] = count(str.toString(), Integer.parseInt(arr[arr.length-1]));
        }
    }

    public int[] solution(String[] info, String[] query) {
        map = new HashMap<>();
        answer = new int[query.length];

        setInfo(info);
        process(query);

        return answer;
    }
}
