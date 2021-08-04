package PROGRAMMERS.level_3._12946;

public class Solution {
    public static int[][] answer;
    public static int index = 0;

    public static void hanoiTower(int n, int start, int end, int mid) {
        if(n == 1) {
            answer[index][0] = start;
            answer[index++][1] = end;
        }else {
            hanoiTower(n-1, start, mid, end);
            answer[index][0] = start;
            answer[index++][1] = end;
            hanoiTower(n-1, mid, end, start);
        }
    }

    public int[][] solution(int n) {
        answer = new int[(int)Math.pow(2,n)-1][2];
        hanoiTower(n, 1, 3, 2);
        return answer;
    }
}
