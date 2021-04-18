package PROGRAMMERS.level_2._42860;

public class Solution {
    public static int solution(String name) {
        int answer = 0, min = name.length()-1;

        for(char n : name.toCharArray()) answer += Math.min(n-'A', 'Z'-n+1);
        for(int i=0; i<name.length(); i++) {
            int n = i + 1;
            while(n < name.length() && name.charAt(n) == 'A') n++;
            min = Math.min(min, i + name.length() - n + Math.min(i, name.length()-n));
        }

        return answer + min;
    }
}
