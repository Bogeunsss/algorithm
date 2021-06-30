package BEAKJOON.BOJ_2261;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] locations;

    public static int closest(int start, int end) {
        if(end - start + 1 < 4) {
            return brute(start, end);
        }
        int mid = (start + end) / 2;
        int left = closest(start, mid);
        int right = closest(mid+1, end);
        int min = Math.min(left, right);

        return Math.min(min, getMid(start, end, mid, min));
    }

    public static int getMid(int start, int end, int mid, int min) {
        List<int[]> list = new ArrayList<>();
        int distX;

        int midX = locations[mid][0];
        for(int i=start; i<=end; i++) {
            distX = locations[i][0] - midX;
            if(distX * distX < min) {
                list.add(locations[i]);
            }
        }

        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int distY;
        for(int i=0; i<list.size(); i++) {
            for(int j=i+1; j<list.size(); j++) {
                distY = list.get(i)[1] - list.get(j)[1];
                if(distY * distY < min) {
                    min = Math.min(getDistance(list.get(i), list.get(j)), min);
                }else{
                    break;
                }
            }
        }
        return min;
    }

    public static int brute(int start, int end) {
        int min = Integer.MAX_VALUE;

        for(int i=start; i<end; i++) {
            int[] x = locations[i];
            for(int j=i+1; j<=end; j++) {
                min = Math.min(min, getDistance(x, locations[j]));
            }
        }
        return min;
    }

    public static int getDistance(int[] o1, int[] o2) {
        return (o2[0] - o1[0]) * (o2[0] - o1[0]) + (o2[1] - o1[1]) * (o2[1] - o1[1]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        locations = new int[n][2];

        StringTokenizer st;
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            locations[i][0] = Integer.parseInt(st.nextToken());
            locations[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(locations, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        System.out.println(closest(0, n-1));
    }
}