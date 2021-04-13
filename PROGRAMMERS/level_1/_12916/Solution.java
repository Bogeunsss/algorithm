package PROGRAMMERS.level_1._12916;

public class Solution {
    boolean solution(String s) {
        s = s.toLowerCase();
        char[] py = new char[2];

        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == 'p') py[0]++;
            else if(s.charAt(i) == 'y') py[1]++;
        }

        return py[0] == py[1];
    }
}
