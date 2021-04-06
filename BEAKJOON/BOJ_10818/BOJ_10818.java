package BEAKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_10818 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int min, max;
		
		br.readLine();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		min = Integer.parseInt(st.nextToken());
		max = min;
		while(st.hasMoreTokens()) {
			int val = Integer.parseInt(st.nextToken());
			if(val > max) max = val;
			if(val < min) min = val;
		}
		bw.write(Integer.toString(min)+" "+Integer.toString(max));
		
		bw.flush();
		bw.close();
	}

}
