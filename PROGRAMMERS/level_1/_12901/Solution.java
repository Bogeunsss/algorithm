package PROGRAMMERS.level_1._12901;

public class Solution {
    String[] weeks = {"THU", "FRI", "SAT", "SUN", "MON", "TUE", "WED"};
    int[] months = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public String solution(int a, int b) {
        int days = 0;
        for(int i=0; i<a-1; i++) days += months[i];
        days += b;

        return weeks[days % 7];
    }
}
