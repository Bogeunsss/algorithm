package PROGRAMMERS.level_2._17684;

import java.util.*;

public class Solution {
    public int[] solution(String msg) {
        HashMap<String, Integer> dict = new HashMap<>();
        List<Integer> LZW = new ArrayList<>();
        int size = 26;
        boolean fin = false;
        int[] answer;

        for(int i=1; i<=size; i++) {
            dict.put(Character.toString((char) (i + 64)), i);
        }
        for(int i=0; i<msg.length(); i++) {
            String temp = Character.toString(msg.charAt(i));

            while(dict.containsKey(temp)) {
                if(i + 1 == msg.length()) {
                    fin = true;
                    break;
                }
                temp += msg.charAt(++i);
            }
            if(fin) {
                LZW.add(dict.get(temp));
                break;
            }
            LZW.add(dict.get(temp.substring(0, temp.length()-1)));
            dict.put(temp, ++size);
            i--;
        }
        answer = new int[LZW.size()];
        for(int i=0; i<LZW.size(); i++) answer[i] = LZW.get(i);
        return answer;
    }
}
