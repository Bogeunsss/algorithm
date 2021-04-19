package PROGRAMMERS.level_2._42842;

public class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];

        for(int i=1; i<=yellow; i++) {
            if(yellow % i == 0) {
                int row = yellow / i;
                int col = i;
                if(brown == row * 2 + col * 2 + 4) {
                    answer[0] = row > col ? row + 2 : col + 2;
                    answer[1] = row < col ? row + 2 : col + 2;
                }
            }
        }
        return answer;
    }
}
