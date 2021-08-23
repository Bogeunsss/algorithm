package PROGRAMMERS.level_3._84325;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
    public String solution(String[] table, String[] languages, int[] preference) {
        String answer = "";
        StringTokenizer st;
        int max = 0;

        for(String data : table) {
            st = new StringTokenizer(data, " ");
            String job = st.nextToken();
            Map<String,Integer> score = new HashMap<>();
            int point = 5, total = 0;

            while(st.hasMoreTokens()) {
                score.put(st.nextToken(), point--);
            }
            for(int i=0; i<languages.length; i++) {
                if(score.containsKey(languages[i])) {
                    total += score.get(languages[i]) * preference[i];
                }
            }
            if(max <= total) {
                if(max == total) {
                    answer = answer.compareTo(job) < 0 ? answer : job;
                }else {
                    answer = job;
                }
                max = total;
            }
        }
        return answer;
    }
}
