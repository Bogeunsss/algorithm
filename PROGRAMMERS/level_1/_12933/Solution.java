package PROGRAMMERS.level_1._12933;

import java.util.Arrays;

public class Solution {
    public long solution(long n) {
        char[] numbers = Long.toString(n).toCharArray();
        Arrays.sort(numbers);
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(numbers));

        return Long.parseLong(stringBuilder.reverse().toString());
    }
}
