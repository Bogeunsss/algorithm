package PROGRAMMERS.level_2._68936;

public class Solution {
    static int[] answer;

    public static void quadTree(int[][] arr, int x, int y, int unit) {
        int seed = arr[x][y];
        boolean pure = true;
        for(int i=x; i<x+unit; i++) {
            for(int j=y; j<y+unit; j++) {
                if(arr[i][j] != arr[x][y]) {
                    pure = false;
                    break;
                }
            }
            if(!pure) break;
        }
        if(pure) answer[seed]++;
        else {
            int next = unit / 2;
            quadTree(arr, x, y, next);
            quadTree(arr, x, y+next, next);
            quadTree(arr, x+next, y, next);
            quadTree(arr, x+next, y+next, next);
        }
    }

    public int[] solution(int[][] arr) {
        answer = new int[2];

        quadTree(arr, 0, 0, arr.length);
        return answer;
    }
}
