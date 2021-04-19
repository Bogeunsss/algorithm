package PROGRAMMERS.level_2._43165;

public class Solution {
    static int count;
    static boolean[] visited;

    public static void dfs(int[] numbers, int index, int target, int sum) {
        if(index == numbers.length && sum == target) {
            count++;
            return;
        }
        if(index == numbers.length) return;
        dfs(numbers, index+1, target, sum+numbers[index]);
        dfs(numbers, index+1, target, sum-numbers[index]);
    }

    public int solution(int[] numbers, int target) {
        count = 0;
        visited = new boolean[numbers.length];

        dfs(numbers, 0, target, 0);

        return count;
    }
}
