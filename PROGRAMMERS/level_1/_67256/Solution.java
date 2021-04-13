package PROGRAMMERS.level_1._67256;

public class Solution {
    public String solution(int[] numbers, String hand) {
        StringBuilder answer = new StringBuilder();
        int[][] phone = {{-1, 7, 4, 1},
                         { 0, 8, 5, 2},
                         {-1, 9, 6, 3}};

        int lx = 0, ly = 0, rx = 2, ry = 0;
        for(int number : numbers) {
            if(number == 1 || number == 4 || number == 7) {
                answer.append("L");
                for(int i=0; i<4; i++) {
                    if(phone[0][i] == number) {
                        lx = 0;
                        ly = i;
                        break;
                    }
                }
            }else if(number == 3 || number == 6 || number == 9) {
                answer.append("R");
                for(int i=0; i<4; i++) {
                    if(phone[2][i] == number) {
                        rx = 2;
                        ry = i;
                        break;
                    }
                }
            }else {
                int cx = 0, cy = 0;
                for(int i=0; i<4; i++) {
                    if(phone[1][i] == number) {
                        cx = 1;
                        cy = i;
                        break;
                    }
                }
                if((Math.abs(lx - cx) + Math.abs(ly - cy)) < (Math.abs(rx - cx) + Math.abs(ry - cy))) {
                    answer.append("L");
                    lx = cx;
                    ly = cy;
                }else if((Math.abs(lx - cx) + Math.abs(ly - cy)) > (Math.abs(rx - cx) + Math.abs(ry - cy))) {
                    answer.append("R");
                    rx = cx;
                    ry = cy;
                }else {
                    if(hand.equals("left")) {
                        answer.append("L");
                        lx = cx;
                        ly = cy;
                    }
                    else {
                        answer.append("R");
                        rx = cx;
                        ry = cy;
                    }
                }
            }
        }

        return answer.toString();
    }
}
