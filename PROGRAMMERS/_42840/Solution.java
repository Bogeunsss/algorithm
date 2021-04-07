package PROGRAMMERS._42840;

import java.util.ArrayList;
import java.util.Comparator;

public class Solution {
    public ArrayList<Integer> solution(int[] answers) {
        ArrayList<Integer> answer = new ArrayList<Integer>();
        int[][] patterns = {{1, 2, 3, 4, 5},
                            {2, 1, 2, 3, 2, 4, 2, 5},
                            {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}};
        int[] students = new int[3];

        for(int i=0; i<answers.length; i++) {
            if(patterns[0][i%patterns[0].length] == answers[i]) students[0]++;
            if(patterns[1][i%patterns[1].length] == answers[i]) students[1]++;
            if(patterns[2][i%patterns[2].length] == answers[i]) students[2]++;
        }

        for(int i=0; i<students.length; i++) {
            if(Math.max(Math.max(students[0], students[1]), students[2]) == students[i]) answer.add(i+1);
        }
        answer.sort(Comparator.naturalOrder());

        return answer;
    }
}
