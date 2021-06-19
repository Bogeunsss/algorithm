package BEAKJOON.BOJ_9020;

import java.util.Scanner;

public class Main {
    static final int MAX = 10000;
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
        int T = sc.nextInt();

        eratosthenes();

        for(int tc=0; tc<T; tc++) {
            int n = sc.nextInt();
            int min = Integer.MAX_VALUE;
            int p1 = 0, p2 = 0;

            for(int i=2; i<=n; i++) {
                if(!prime[i] && !prime[n-i]) {
                    if(min > Math.abs(n - i * 2)) {
                        min = Math.abs(n - i * 2);
                        p1 = Math.min(i, n - i);
                        p2 = Math.max(i, n - i);
                    }
                }
            }
            System.out.println(p1 + " " + p2);
        }
    }
}