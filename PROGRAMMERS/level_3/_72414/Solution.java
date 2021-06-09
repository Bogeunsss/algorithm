package PROGRAMMERS.level_3._72414;

import java.util.*;

public class Solution {

    public static int timeToSecond(String times) {
        String[] time = times.split(":");
        return Integer.parseInt(time[0]) * 3600 + Integer.parseInt(time[1]) * 60 + Integer.parseInt(time[2]);
    }

    public static String secondToTime(int times) {
        String ret = "";

        if(times / 3600 < 10) ret += "0";
        ret += Integer.toString(times / 3600);
        ret += ":";
        times %= 3600;

        if(times / 60 < 10) ret += "0";
        ret += Integer.toString(times / 60);
        ret += ":";
        times %= 60;

        if(times < 10) ret += "0";
        ret += Integer.toString(times);

        return ret;
    }

    public String solution(String play_time, String adv_time, String[] logs) {
        int total = timeToSecond(play_time);
        int ad = timeToSecond(adv_time);
        int[] totalPlayTime = new int[total];

        for(String log : logs) {
            int start = timeToSecond(log.substring(0, 8));
            int end = timeToSecond(log.substring(9));
            for(int i=start; i<end; i++) totalPlayTime[i]++;
        }

        Queue<Integer> q = new LinkedList<>();
        long sum = 0, max = 0;
        int idx = 0;
        for(int i=0; i<ad; i++) {
            sum += totalPlayTime[i];
            q.offer(totalPlayTime[i]);
        }
        max = sum;

        for(int i=ad; i<total; i++) {
            sum += totalPlayTime[i];
            q.offer(totalPlayTime[i]);
            sum -= q.isEmpty() ? 0 : q.poll();
            if(max < sum) {
                max = sum;
                idx = i - ad + 1;
            }
        }

        return secondToTime(idx);
    }
}
