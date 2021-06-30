package BEAKJOON.BOJ_2470;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] solution = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int low = 0, high = N - 1, min = Integer.MAX_VALUE;
        int[] answer = new int[2];

        for(int i=0; i<N; i++) {
            solution[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(solution);

        while(low < high) {
            int sum = solution[low] + solution[high];
            if(Math.abs(sum) < min) {
                min = Math.abs(sum);
                answer[0] = solution[low];
                answer[1] = solution[high];
            }
            if(sum > 0) high--;
            else low++;
        }
        System.out.println(answer[0] + " " + answer[1]);
    }
}