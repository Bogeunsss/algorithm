package PROGRAMMERS.level_2._17683;

import java.util.*;

public class Solution {
    public static boolean match(String m, String[] sheet) {
        int shopCount = 0, index = 0;
        for(int i=0; i<m.length(); i++) {
            if(m.charAt(i) == '#') shopCount++;
        }
        String[] target = new String[m.length()-shopCount];
        for(int i=0; i<m.length(); i++) {
            if(m.charAt(i) == '#') {
                target[index-1] += "#";
            }else {
                target[index++] = Character.toString(m.charAt(i));
            }
        }
        for(int i=0; i<sheet.length; i++) {
            if(sheet[i].equals(target[0])) {
                boolean isMatch = true;
                for(int j=0; j<target.length; j++) {
                    if(i+j >= sheet.length || !target[j].equals(sheet[i+j])) isMatch = false;
                }
                if(isMatch) return true;
            }
        }
        return false;
    }

    public String solution(String m, String[] musicinfos) {
        HashMap<Integer, String> answer = new HashMap<>();
        int max = 0;

        for(String musicinfo : musicinfos) {
            String[] info = musicinfo.split(",");
            int hour = Integer.parseInt(info[1].substring(0, 2)) - Integer.parseInt(info[0].substring(0, 2));
            int minute = Integer.parseInt(info[1].substring(3, 5)) - Integer.parseInt(info[0].substring(3, 5)) + hour * 60;
            String[] sheet = new String[minute];

            for(int i=0, j=0; i<minute; i++) {
                if(j < info[3].length() - 1 && info[3].charAt(j+1) == '#') {
                    sheet[i] = Character.toString(info[3].charAt(j++)) + Character.toString(info[3].charAt(j++));
                }else {
                    sheet[i] = Character.toString(info[3].charAt(j++));
                }
                if(j == info[3].length()) j = 0;
            }
            if(match(m, sheet)) {
                if(!answer.containsKey(sheet.length)) {
                    answer.put(sheet.length, info[2]);
                }
                if(sheet.length > max) max = sheet.length;
            }
        }
        if(answer.size() > 0) return answer.get(max);
        return "(None)";
    }
}
