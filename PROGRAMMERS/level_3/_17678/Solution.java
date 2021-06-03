package PROGRAMMERS.level_3._17678;

import java.util.*;

public class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int bus = 9 * 60;
        int con = 0;
        String answer = "";

        for(String time : timetable) {
            int minute = Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3));
            pq.offer(minute);
        }

        for(int i=0; i<n; i++) {
            List<Integer> onBoard = new ArrayList<>();
            for(int j=0; j<m; j++) {
                if(!pq.isEmpty() && pq.peek() <= bus) {
                    onBoard.add(pq.poll());
                }else {
                    break;
                }
            }
            if(i == n-1) {
                if(onBoard.size() == m) {
                    con = onBoard.get(onBoard.size()-1)-1;
                }else {
                    con = bus;
                }
                break;
            }
            bus += t;
        }

        return String.format("%02d:%02d", con / 60, con % 60);
    }
}
