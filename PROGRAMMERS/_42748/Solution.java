package PROGRAMMERS._42748;

import java.util.Arrays;

public class Solution {
    public static int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int index = 0;

        for(int[] command : commands) {
            int i = command[0]-1, j = command[1], k = command[2]-1;
            int[] temp = Arrays.stream(array, i, j).toArray();
            Arrays.sort(temp);
            answer[index++] = temp[k];
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};

        System.out.println(Arrays.toString(solution(array, commands)));
    }
}
