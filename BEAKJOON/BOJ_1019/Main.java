package BEAKJOON.BOJ_1019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int n;
    public static int[] pages;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        pages = new int[10];
    }

    public static void count(int index, int unit) {

        while(index > 0) {
            pages[index % 10] += unit;
            index /= 10;
        }
    }

    public static void countPages() {

        int start = 1;
        int unit = 1;

        while(start <= n) {
            while(n % 10 != 9 && start <= n) {
                count(n, unit);
                n--;
            }

            if(n < start) break;

            while(start % 10 != 0 && start <= n) {
                count(start, unit);
                start++;
            }

            start /= 10;
            n /= 10;

            for(int i=0; i<10; i++) {
                pages[i] += (n - start + 1) * unit;
            }
            unit *= 10;
        }

        for(int i=0; i<10; i++) {
            System.out.print(pages[i] + " ");
        }
    }

    public static void main(String[] args) throws IOException {

        input();
        countPages();
    }
}