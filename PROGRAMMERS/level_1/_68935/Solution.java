package PROGRAMMERS.level_1._68935;

public class Solution {
    public int solution(int n) {
        int answer = 0;
        StringBuilder base = new StringBuilder();
        while(n > 0) {
            base.insert(0, n % 3);
            n /= 3;
        }
        int three = 1;
        for(int i=0; i<base.length(); i++) {
            answer += three * Integer.parseInt(String.valueOf(base.charAt(i)));
            three *= 3;
        }
        return answer;
    }
}
