package PROGRAMMERS.level_1._17682;

import java.util.stream.IntStream;

public class Solution {
    public int solution(String dartResult) {
        int answer = 0;
        int pos = 0;
        int index = 0;
        int[] score = new int[3];
        char[] dart = dartResult.toCharArray();

        while(pos < dart.length) {
            int point = 0;
            if(Character.isDigit(dart[pos])) {
                point = dart[pos] - '0';
                if(Character.isDigit(dart[pos+1])) {
                    point = 10;
                    pos++;
                }
                if(dart[pos+1] == 'S') score[index++] = point;
                else if(dart[pos+1] == 'D') score[index++] = (int) Math.pow(point, 2);
                else if(dart[pos+1] == 'T') score[index++] = (int) Math.pow(point, 3);
                pos += 2;
            }else if(dart[pos] == '*') {
                if(index == 1) {
                    score[0] *= 2;
                }else {
                    score[index-1] *= 2;
                    score[index-2] *= 2;
                }
                pos++;
            }else {
                score[index-1] *= -1;
                pos++;
            }
        }
        answer = IntStream.of(score).sum();
        return answer;
    }
}
