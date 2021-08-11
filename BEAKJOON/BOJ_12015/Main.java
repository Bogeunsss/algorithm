package BEAKJOON.BOJ_12015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];

        List<Integer> list = new ArrayList<>();
        list.add(0);

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++) {
            int value = A[i] = Integer.parseInt(st.nextToken());
            if(value > list.get(list.size() - 1)) {
                list.add(value);
            }else {
                int left = 0, right = list.size() - 1;
                while(left < right) {
                    int mid = (left + right) >> 1;
                    if(list.get(mid) >= value) {
                        right = mid;
                    }else {
                        left = mid + 1;
                    }
                }
                list.set(right, value);
            }
        }
        System.out.println(list.size() - 1);
    }
}