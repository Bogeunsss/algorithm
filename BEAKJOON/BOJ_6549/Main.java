package BEAKJOON.BOJ_6549;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] histogram;

    public static long getArea(int low, int high) {
        if(high == low) return histogram[low];

        int mid = (high + low) / 2;
        long leftHand = getArea(low, mid);
        long rightHand = getArea(mid + 1, high);
        long max = Math.max(leftHand, rightHand);

        return Math.max(max, getMidArea(low, high, mid));
    }

    public static long getMidArea(int low, int high, int mid) {
        int left = mid;
        int right = mid;
        long height = histogram[mid];
        long max = height;

        while(low < left && right < high) {
            if(histogram[left - 1] < histogram[right + 1]) {
                right++;
                height = Math.min(height, histogram[right]);
            }else {
                left--;
                height = Math.min(height, histogram[left]);
            }
            max = Math.max(max, height * (right - left + 1));
        }
        while(right < high) {
            right++;
            height = Math.min(height, histogram[right]);
            max = Math.max(max, height * (right - left + 1));
        }
        while(low < left) {
            left--;
            height = Math.min(height, histogram[left]);
            max = Math.max(max, height * (right - left + 1));
        }

        return max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n;

        while(true) {
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            if(n == 0) break;

            histogram = new int[n];
            for(int i=0; i<n; i++) histogram[i] = Integer.parseInt(st.nextToken());

            System.out.println(getArea(0, n-1));
        }
    }
}