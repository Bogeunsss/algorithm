package PROGRAMMERS.level_3._17676;

public class Solution {
    public int solution(String[] lines) {
        int answer = 0;
        int[] starts = new int[lines.length];
        int[] ends = new int[lines.length];

        for(int i=0; i<lines.length; i++) {
            String[] log = lines[i].split(" ");
            String[] response = log[1].split(":");
            int process = (int) Float.parseFloat(log[2].substring(0, log[2].length()-1)) * 1000;

            int end = Integer.parseInt(response[0]) * 1000 * 60 * 60;
            end += Integer.parseInt(response[1]) * 1000 * 60;
            end += (int) Float.parseFloat(response[2]) * 1000;
            int start = end - process + 1;

            starts[i] = start;
            ends[i] = end;
        }

        for(int i=0; i<lines.length; i++) {
            int count = 0;
            int startOfSection = starts[i];
            int endOfSection = startOfSection + 1000;

            for(int j=0; j< lines.length; j++) {
                if(starts[j] >= startOfSection && starts[j] < endOfSection) count++;
                else if(ends[j] >= startOfSection && ends[j] < endOfSection) count++;
                else if(starts[j] <= startOfSection && ends[j] >= endOfSection) count++;
            }
            answer = Math.max(answer, count);
        }

        for(int i=0; i<lines.length; i++) {
            int count = 0;
            int startOfSection = ends[i];
            int endOfSection = startOfSection + 1000;

            for(int j=0; j< lines.length; j++) {
                if(starts[j] >= startOfSection && starts[j] < endOfSection) count++;
                else if(ends[j] >= startOfSection && ends[j] < endOfSection) count++;
                else if(starts[j] <= startOfSection && ends[j] >= endOfSection) count++;
            }
            answer = Math.max(answer, count);
        }

        return answer;
    }
}
