package PROGRAMMERS.level_2._42888;

import java.util.*;

public class Solution {
    public String[] solution(String[] record) {
        HashMap<String, String> Users = new HashMap<>();
        List<String> log = new ArrayList<>();

        for(String rec : record) {
            String[] query = rec.split(" ");
            switch(query[0]) {
                case "Enter":
                    Users.put(query[1], query[2]);
                    log.add(query[1] + "님이 들어왔습니다.");
                    break;
                case "Leave":
                    log.add(query[1] + "님이 나갔습니다.");
                    break;
                case "Change":
                    Users.put(query[1], query[2]);
                    break;
            }
        }
        String[] answer = new String[log.size()];
        for(int i=0; i<log.size(); i++) {
            String user = log.get(i).substring(0, log.get(i).indexOf("님"));
            answer[i] = log.get(i).replace(user, Users.get(user));
        }
        return answer;
    }
}
