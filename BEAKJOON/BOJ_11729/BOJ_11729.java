package BEAKJOON.BOJ_11729;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11729 {
	
	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		sb.append((int)(Math.pow(2, N)-1)).append("\n");
		
		Hanoi(N, 1, 2, 3);
		
		System.out.print(sb);
	}
	
	public static void Hanoi(int N, int A, int B, int C) {
		if(N == 1) sb.append(A+" "+C+"\n");
		else {
			Hanoi(N-1, A, C, B);
			sb.append(A+" "+C+"\n");
			Hanoi(N-1, B, A, C);			
		}
	}

}
