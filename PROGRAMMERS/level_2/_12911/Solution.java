package PROGRAMMERS.level_2._12911;

public class Solution {
    public int solution(int n) {
        String binary = Integer.toBinaryString(n);
        int count = 0;

        for(int i=0; i<binary.length(); i++) {
            if(binary.charAt(i) == '1') count++;
        }
        for(int i=n+1; i<=10000000; i++) {
            String large = Integer.toBinaryString(i);
            int largeCount = 0;
            for(int j=0; j<large.length(); j++) {
                if(large.charAt(j) == '1') largeCount++;
            }
            if(count == largeCount) return i;
        }

        return 0;
    }
}
