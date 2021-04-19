package PROGRAMMERS.level_2._42577;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class Solution {
    public boolean solution(String[] phone_book) {
        HashMap<String, Integer> map = new HashMap<>();

        Arrays.sort(phone_book, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        });

        for(String book : phone_book) {
            if(map.get(book) != null) return false;
            for(int i=1; i<=book.length(); i++) map.put(book.substring(0, i), 0);
        }
        return true;
    }
}
