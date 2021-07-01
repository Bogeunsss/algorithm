package BEAKJOON.BOJ_1644;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static boolean[] primes;

    public static void eratosthenes(int n) {
        primes[0] = primes[1] = true;
        for(int i=2; i*i<=n; i++) {
            if(!primes[i]) {
                for(int j=i*i; j<=n; j+=i) primes[j] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> list = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());
        int low = 0, high = 0;
        int sum = 0, answer = 0;

        primes = new boolean[N+1];
        eratosthenes(N);

        for(int i=1; i<=N; i++) {
            if(!primes[i]) list.add(i);
        }
        while(true) {
            if(sum >= N) sum -= list.get(low++);
            else if(high == list.size()) break;
            else sum += list.get(high++);
            if(sum == N) answer++;
        }
        System.out.println(answer);
    }
}