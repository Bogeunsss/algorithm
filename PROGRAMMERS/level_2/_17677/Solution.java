package PROGRAMMERS.level_2._17677;

import java.util.*;

public class Solution {
    public static float getIntersection(List<String> _A, List<String> _B) {
        List<String> A = new ArrayList<>(_A);
        List<String> B = new ArrayList<>(_B);
        List<String> ret = new ArrayList<>();

        for(String value : A) {
            if(B.contains(value)) {
                ret.add(value);
                B.remove(value);
            }
        }
        return ret.size();
    }

    public int solution(String str1, String str2) {
        List<String> setA = new ArrayList<>();
        List<String> setB = new ArrayList<>();

        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();

        for(int i=0; i<str1.length()-1; i++) {
            char first = str1.charAt(i);
            char second = str1.charAt(i+1);
            if(Character.isLetter(first) && Character.isLetter(second)) {
                setA.add(Character.toString(first) + Character.toString(second));
            }
        }
        for(int i=0; i<str2.length()-1; i++) {
            char first = str2.charAt(i);
            char second = str2.charAt(i+1);
            if(Character.isLetter(first) && Character.isLetter(second)) {
                setB.add(Character.toString(first) + Character.toString(second));
            }
        }
        float intersection = getIntersection(setA, setB);
        float union = setA.size() + setB.size() - intersection;

        if(intersection == 0 && union == 0) return 65536;
        return (int) (intersection / union * 65536);
    }
}
