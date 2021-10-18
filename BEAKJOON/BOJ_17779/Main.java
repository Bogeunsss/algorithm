package BEAKJOON.BOJ_17779;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static List<Point> points;
    public static int[][] A, map;
    public static int n;
    public static int answer = Integer.MAX_VALUE;

    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void calc() {
        int[] sum = new int[6];

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                sum[map[i][j]] = sum[map[i][j]] + A[i][j];
            }
        }

        Arrays.sort(sum);

        answer = Math.min(answer, sum[5] - sum[1]);
    }

    public static void marking() {
        int left = 0, right = 0;

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                map[i][j] = 5;
            }
        }
        for(int i=0; i<points.get(1).x; i++) {
            if(i >= points.get(0).x) left++;
            for(int j=0; j<=points.get(0).y-left; j++) {
                map[i][j] = 1;
            }
        }
        for(int i=0; i<=points.get(2).x; i++) {
            if(i > points.get(0).x) right++;
            for(int j=points.get(0).y+right+1; j<n; j++) {
                map[i][j] = 2;
            }
        }
        left = 0;
        for(int i=n-1; i>=points.get(1).x; i--) {
            if(i < points.get(3).x) left++;
            for(int j=0; j<points.get(3).y-left; j++) {
                map[i][j] = 3;
            }
        }
        right = 0;
        for(int i=n-1; i>points.get(2).x; i--) {
            if(i <= points.get(3).x) right++;
            for(int j=points.get(3).y+right; j<n; j++) {
                map[i][j] = 4;
            }
        }

        calc();
    }

    public static boolean inRange(int x, int y, int d1, int d2) {

        if(x + d1 >= n || y - d1 < 0) return false;
        if(x + d2 >= n || y + d2 >= n) return false;
        if(x + d1 + d2 >= n || y - d1 + d2 >= n) return false;
        if(x + d2 + d1 >= n || y + d2 - d1 < 0) return false;

        return true;
    }

    public static void gerrymandering() {

        for(int i=0; i<n; i++) {
            for(int j=1; j<n; j++) {
                for(int d1=1; d1<=j; d1++) {
                    for(int d2=1; d2<n-j; d2++) {
                        if(inRange(i, j, d1, d2)) {
                            points = new ArrayList<>();

                            points.add(new Point(i, j));
                            points.add(new Point(i+d1, j-d1));
                            points.add(new Point(i+d2, j+d2));
                            points.add(new Point(i+d1+d2, j-d1+d2));

                            marking();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        A = new int[n][n];
        map = new int[n][n];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<n; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        gerrymandering();

        System.out.println(answer);
    }
}