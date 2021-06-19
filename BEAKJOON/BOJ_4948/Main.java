package BEAKJOON.BOJ_4948;

import java.util.Scanner;

public class Main {
    static final int MAX = 123456 * 2;
    static boolean[] prime = new boolean[MAX+1];

    public static void eratosthenes() {
        prime[0] = prime[1] = true;
        for(int i=2; i*i<=MAX; i++) {
            if(!prime[i]) {
                for(int j=i*i; j<=MAX; j+=i) prime[j] = true;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        eratosthenes();

        while(true) {
            int n = sc.nextInt();
            if(n == 0) break;

            int answer = 0;
            for(int i=n+1; i<=n*2; i++) {
                if(!prime[i]) answer++;
            }
            System.out.println(answer);
        }
    }
}
