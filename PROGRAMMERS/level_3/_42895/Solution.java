package PROGRAMMERS.level_3._42895;

public class Solution {
    static int n;
    static int target;
    static int answer;

    public static void dfs(int cnt, int prev) {
        if(cnt > 8) {
            answer = -1;
            return;
        }
        if(prev == target) {
            answer = Math.min(answer, cnt);
            return;
        }
        int temp = n;
        for(int i=0; i<8-cnt; i++) {
            int next = cnt + i + 1;
            dfs(next, prev + temp);
            dfs(next, prev - temp);
            dfs(next, prev * temp);
            dfs(next, prev / temp);

            temp = temp * 10 + n;
        }
    }

    public int solution(int N, int number) {
        n = N;
        target = number;
        answer = 9;

        dfs(0, 0);
        return answer == 9 ? -1 : answer;
    }
}
