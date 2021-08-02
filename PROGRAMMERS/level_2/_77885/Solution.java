package PROGRAMMERS.level_2._77885;

public class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        int index = 0;

        for(long number : numbers) {
            String binaryString = Long.toBinaryString(number);
            if(binaryString.charAt(0) == '1') {
                binaryString = "0" + binaryString;
            }
            char[] bits = binaryString.toCharArray();
            boolean isFirst = true;

            for(int i=bits.length-1; i>=0; i--) {
                if(bits[i] == '0') {
                    if(!isFirst) bits[i+1] = '0';
                    bits[i] = '1';
                    answer[index++] = Long.parseLong(String.valueOf(bits), 2);
                    break;
                }
                isFirst = false;
            }
        }
        return answer;
    }
}