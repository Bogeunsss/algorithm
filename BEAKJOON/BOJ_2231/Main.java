package BEAKJOON.BOJ_2231;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int answer = Integer.MAX_VALUE;
		
		for(int i=0;i<N;i++) {
			int val = constructor(i);
			if(val == N && val < answer) {
				answer = i;
			}
		}
		if(answer == Integer.MAX_VALUE) System.out.println(0);
		else System.out.println(answer);
	}
	
	public static int constructor(int N) {
		int result = N;
		while(N > 0) {
			result += N % 10;
			N /= 10;
		}
		return result;
	}

}
