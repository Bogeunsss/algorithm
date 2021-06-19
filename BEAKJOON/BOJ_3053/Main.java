package BEAKJOON.BOJ_3053;

import java.util.Scanner;

public class Main {
    static final double pi = Math.PI;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int R = sc.nextInt();
        double d = Math.sqrt(2 * R * R);

        System.out.println(pi * R * R);
        System.out.println(d * d);
    }
}