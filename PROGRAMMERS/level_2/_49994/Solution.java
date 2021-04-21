package PROGRAMMERS.level_2._49994;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public int solution(String dirs) {
        List<List<Integer>> visited = new ArrayList<>();
        List<Integer> path, back;
        int x = 5, y = 5;
        int answer = 0;

        for(char dir : dirs.toCharArray()) {
            int nx = x, ny = y;
            if(dir == 'U') nx--;
            else if(dir == 'D') nx++;
            else if(dir == 'R') ny++;
            else ny--;

            if(nx < 0 || nx >= 11 || ny < 0 || ny >= 11) continue;
            path = new ArrayList<>(Arrays.asList(x, y, nx, ny));
            back = new ArrayList<>(Arrays.asList(nx, ny, x, y));
            if(!visited.contains(path) && !visited.contains(back)) {
                visited.add(path);
                visited.add(back);
                answer++;
            }
            x = nx;
            y = ny;
        }

        return answer;
    }
}
