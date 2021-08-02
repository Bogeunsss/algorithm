package PROGRAMMERS.level_1._82612;

public class Solution {
    public static long solution(int price, int money, int count) {
        long answer = 0;

        for(int i=1; i<=count; i++) {
            answer += (long) price * i;
        }

        return (money - answer) >= 0 ? 0 : answer - money;
    }

    public static void main(String[] args) {
        System.out.println(solution(3, 20, 4));
    }
}
