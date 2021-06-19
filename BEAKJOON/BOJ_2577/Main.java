package BEAKJOON.BOJ_2577;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int digits[] = new int[10];
		int val = 1;
		for(int i=0;i<3;i++) {
			val *= Integer.parseInt(br.readLine());
		}
		while(val > 0) {
			digits[val%10]++;
			val /= 10;
		}
		for(int i=0;i<digits.length;i++) {
			bw.write(Integer.toString(digits[i]));
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}

}
