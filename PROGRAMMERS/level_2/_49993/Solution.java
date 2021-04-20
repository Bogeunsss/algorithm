package PROGRAMMERS.level_2._49993;

public class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        for(String skill_tree : skill_trees) {
            int index = 0;
            boolean possible = true;
            for(int i=0; i<skill_tree.length(); i++) {
                if(skill.contains(String.valueOf(skill_tree.charAt(i)))) {
                    if(skill.charAt(index) != skill_tree.charAt(i)) {
                        possible = false;
                        break;
                    }
                    index++;
                }
            }
            if(possible) {
                answer++;
            }
        }
        return answer;
    }
}
