package BEAKJOON.BOJ_2331;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static List<String> seq = new ArrayList<>();

    public static String D(String A, int P) {
        long sum = 0;

        for(char c : A.toCharArray()) {
            sum += (long) Math.pow(c - '0', P);
        }

        return Long.toString(sum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        String A = st.nextToken();
        int P = Integer.parseInt(st.nextToken());
        String next;
        int answer = 0;

        seq.add(A);
        while(true) {
            next = D(seq.get(seq.size()-1), P);

            if(seq.contains(next)) break;
            seq.add(next);
        }
        for(int i=0; i<seq.size(); i++) {
            if(seq.get(i).equals(next)) break;
            answer++;
        }

        System.out.println(answer);
    }
}
