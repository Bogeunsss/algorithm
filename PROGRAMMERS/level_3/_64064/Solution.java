package PROGRAMMERS.level_3._64064;

import java.util.*;

public class Solution {
    static List<List<String>> list;
    static List<Set<String>> set;
    static List<String> visited;
    static boolean[] check;
    static int count;

    public static void dfs(int depth, int index) {
        if(depth == index) {
            Set<String> temp = new HashSet<>(visited);
            if(!set.contains(temp)) {
                set.add(temp);
                count++;
            }
            return;
        }
        for(int j=0; j<list.get(index).size(); j++) {
            if(visited.contains(list.get(index).get(j))) continue;
            if(check[index]) continue;
            check[index] = true;
            visited.add(list.get(index).get(j));
            dfs(depth, index+1);
            visited.remove(list.get(index).get(j));
            check[index] = false;
        }
    }

    public int solution(String[] user_id, String[] banned_id) {
        list = new ArrayList<>();
        set = new ArrayList<>();
        visited = new ArrayList<>();
        check = new boolean[banned_id.length];
        count = 0;

        for(int i=0; i< banned_id.length; i++) {
            list.add(new ArrayList<>());
        }
        for(int i=0; i< banned_id.length; i++) {
            for(String user : user_id) {
                if(banned_id[i].length() == user.length()) {
                    int match = 0;
                    for(int j=0; j<user.length(); j++) {
                        if(banned_id[i].charAt(j) == '*' || banned_id[i].charAt(j) == user.charAt(j)) match++;
                    }
                    if(match == banned_id[i].length()) {
                        list.get(i).add(user);
                    }
                }
            }
        }

        dfs(banned_id.length, 0);
        return count;
    }
}
