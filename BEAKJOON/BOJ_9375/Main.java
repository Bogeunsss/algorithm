package BEAKJOON.BOJ_9375;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        while(T-- > 0) {
            Map<String,Integer> cloths = new HashMap<>();
            int n = Integer.parseInt(br.readLine());
            int count = 1;

            for(int i=0; i<n; i++) {
                st = new StringTokenizer(br.readLine(), " ");

                st.nextToken();
                String type = st.nextToken();

                cloths.put(type, cloths.getOrDefault(type, 1) + 1);
            }
            for(int cloth : cloths.values()) {
                count *= cloth;
            }
            answer.append(count - 1).append("\n");
        }
        System.out.println(answer);
    }
}
