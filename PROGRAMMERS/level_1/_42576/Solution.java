package PROGRAMMERS.level_1._42576;

import java.util.HashMap;

public class Solution {
    public String solution(String[] participant, String[] completion) {
        int hashSum = 0;
        HashMap<Integer, String> player = new HashMap<Integer, String>();
        for(String p : participant) {
            player.put(p.hashCode(), p);
            hashSum += p.hashCode();
        }
        for(String c : completion) {
            hashSum -= c.hashCode();
        }
        return player.get(hashSum);
    }
}
