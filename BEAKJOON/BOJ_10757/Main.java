package BEAKJOON.BOJ_10757;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String A = sc.next();
        String B = sc.next();
        if(A.length() > B.length()) {
            String temp = A;
            A = B;
            B = temp;
        }

        int[] sum = new int[B.length() + 1];
        int index = sum.length - 1;
        for(int i=A.length()-1; i>=0; i--) {
            sum[index--] += A.charAt(i) - '0';
        }
        index = sum.length - 1;
        for(int i=B.length()-1; i>=0; i--) {
            sum[index--] += B.charAt(i) - '0';
        }
        for(int i=sum.length - 1; i>0; i--) {
            if(sum[i] >= 10) {
                sum[i-1] += sum[i] / 10;
                sum[i] %= 10;
            }
        }
        for(int i=0; i<sum.length; i++) {
            if(i==0 && sum[i]==0) continue;
            System.out.print(sum[i]);
        }
        System.out.println();
    }
}
