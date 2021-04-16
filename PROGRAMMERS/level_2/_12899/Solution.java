package PROGRAMMERS.level_2._12899;

public class Solution {
    public String solution(int n) {
        StringBuilder answer = new StringBuilder();
        char[] base = {'4', '1', '2'};

        while(n > 0) {
            int m = n % 3;
            n /= 3;
            if(m == 0) n--;
            answer.append(base[m]);
        }

        return answer.reverse().toString();
    }
}
