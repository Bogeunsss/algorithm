package PROGRAMMERS.level_2._70129;

public class Solution {
    static int step;
    static int zeroCount;

    public static void makeOne(String str) {
        if(str.length() == 1) return;
        String removeZero = str.replaceAll("0", "");
        String newStr = Integer.toBinaryString(removeZero.length());

        zeroCount += str.length() - removeZero.length();
        step++;

        makeOne(newStr);
    }

    public int[] solution(String s) {
        step = 0;
        zeroCount = 0;

        makeOne(s);

        return new int[]{step, zeroCount};
    }
}
