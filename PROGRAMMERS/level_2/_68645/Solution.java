package PROGRAMMERS.level_2._68645;

public class Solution {
    public static int[] solution(int n) {
        int x = 0, y = 0, direct = 0, value = 1;
        int max = n * (n + 1) / 2;
        int[][] snail = new int[n][n];
        int[] answer = new int[max];

        for(int i=0; i<n; i++) {
            if(direct % 3 == 0) {
                for(int j=i; j<n; j++) {
                    snail[x++][y] = value++;
                }
                x--;
                y++;
                direct++;
            }
            else if(direct % 3 == 1) {
                for(int j=i; j<n; j++) snail[x][y++] = value++;
                x--;
                y -= 2;
                direct++;
            }
            else if(direct % 3 == 2){
                for(int j=i; j<n; j++) snail[x--][y--] = value++;
                x += 2;
                y++;
                direct++;
            }
        }

        int index = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<=i; j++) {
                answer[index++] = snail[i][j];
            }
        }
        return answer;
    }
}