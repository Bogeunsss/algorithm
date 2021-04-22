package PROGRAMMERS.level_2._12985;

public class Solution {
    public int solution(int n, int a, int b) {
        return Integer.toBinaryString((a-1)^(b-1)).length();
    }
}
