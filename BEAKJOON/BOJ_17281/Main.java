package BEAKJOON.BOJ_17281;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static final int MAX = 10;

    public static int[][] abilities;
    public static boolean[] visited;
    public static int[] lineup;
    public static int n;
    public static int answer = 0;

    public static void run(int[] bases) {
        int home = bases[3];
        for(int i=3; i>0; i--) bases[i] = bases[i-1];
        bases[0] = home;
    }

    public static void play() {
        int innings = 1;
        int hitter = 0, score = 0;

        while(innings <= n) {
            int[] bases = new int[4];
            int out = 0;

            while(out < 3) {
                int hit = abilities[innings][lineup[hitter++ % 9 + 1]];

                if(hit == 0) out++;
                else {
                    bases[0] = 1;
                    for(int i=0; i<hit; i++) {
                        run(bases);
                        if(bases[0] == 1) {
                            score++;
                            bases[0] = 0;
                        }
                    }
                }
            }

            innings++;
        }

        answer = Math.max(answer, score);
    }

    public static void baseball(int order) {
        if(order >= MAX) {
            play();
            return;
        }

        for(int i=1; i<MAX; i++) {
            if(visited[i]) continue;

            visited[i] = true;
            lineup[i] = order;
            baseball(order + 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        abilities = new int[n+1][MAX];
        lineup = new int[MAX];
        visited = new boolean[MAX];

        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1; j<MAX; j++) {
                abilities[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        lineup[4] = 1;
        visited[4] = true;

        baseball(2);

        System.out.println(answer);
    }
}