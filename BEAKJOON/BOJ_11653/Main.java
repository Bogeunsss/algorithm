package BEAKJOON.BOJ_11653;

import java.util.Scanner;

public class Main {
    static final int MAX = 10000000;
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

        int N = sc.nextInt();
        int factor = 2;

        eratosthenes();

        while(N > 1) {
            if(prime[factor]) {
                factor++;
                continue;
            }
            if(N % factor == 0) {
                System.out.println(factor);
                N /= factor;
            }else {
                factor++;
            }
        }
    }
}
