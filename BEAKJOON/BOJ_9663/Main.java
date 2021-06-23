package BEAKJOON.BOJ_9663;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] arr;
    static int N;
    static int answer = 0;

    public static void nQueen(int depth) {
        if(depth == N) answer++;
        else{
            for(int i=0; i<N; i++) {
                arr[depth] = i;
                if(isPossible(depth)) {
                    nQueen(depth + 1);
                }
            }
        }
    }

    public static boolean isPossible(int x) {
        for(int i=0; i<x; i++) {
            if(arr[x] == arr[i]) return false;
            else if(Math.abs(x-i) == Math.abs(arr[x] - arr[i])) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        nQueen(0);

        System.out.println(answer);
    }
}