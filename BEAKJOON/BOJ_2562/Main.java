package BEAKJOON.BOJ_2562;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int max = Integer.parseInt(br.readLine());
		int index = 0;
		
		for(int i=1;i<9;i++) {
			int val = Integer.parseInt(br.readLine());	
			if(val > max) {
				max = val;
				index = i;
			}
		}
		bw.write(Integer.toString(max));
		bw.newLine();
		bw.write(Integer.toString(index+1));
		
		bw.flush();
		bw.close();
	}

}
