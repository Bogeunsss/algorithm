package PROGRAMMERS._12930;

public class Solution {
    public String solution(String s) {
        char[] arr = s.toCharArray();
        int index = 0;
        for(int i=0; i<arr.length; i++) {
            if(arr[i] == ' ') {
                index = 0;
                continue;
            }
            else if(index % 2 == 0) arr[i] = Character.toUpperCase(arr[i]);
            else arr[i] = Character.toLowerCase(arr[i]);
            index++;
        }

        return String.valueOf(arr);
    }
}
