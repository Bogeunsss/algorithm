package PROGRAMMERS.level_2._87377;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Solution {

    public static class Point {
        long x;
        long y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private double[] contact(int[] a, int[] b) {
        double[] ret = new double[2];

        long[] A = new long[a.length];
        long[] B = new long[b.length];

        for(int i=0; i<a.length; i++) A[i] = a[i];
        for(int i=0; i<b.length; i++) B[i] = b[i];

        ret[0] = (double) (A[2] * B[0] - A[0] * B[2]) / (A[0] * B[1] - A[1] * B[0]);
        ret[1] = (double) (A[1] * B[2] - A[2] * B[1]) / (A[0] * B[1] - A[1] * B[0]);

        return ret;
    }

    private String[] draw(List<Point> points) {
        long x1 = Integer.MAX_VALUE;
        long y1 = Integer.MAX_VALUE;
        long x2 = Integer.MIN_VALUE;
        long y2 = Integer.MIN_VALUE;

        for(Point point : points) {
            x1 = Math.min(x1, point.x);
            y1 = Math.min(y1, point.y);
            x2 = Math.max(x2, point.x);
            y2 = Math.max(y2, point.y);
        }

        int n = (int) (x2 - x1 + 1);
        int m = (int) (y2 - y1 + 1);
        char[][] map = new char[n][m];
        String[] ret = new String[n];
        StringBuilder row;
        int index = 0;

        for(int i=0; i<n; i++) Arrays.fill(map[i], '.');
        for(Point point : points) {
            map[(int) (point.x - x1)][(int) (point.y - y1)] = '*';
        }
        for(int i=n-1; i>=0; i--) {
            row = new StringBuilder();

            for(int j=0; j<m; j++) {
                row.append(map[i][j]);
            }
            ret[index++] = row.toString();
        }

        return ret;
    }

    public String[] solution(int[][] line) {
        List<Point> points = new ArrayList<>();

        for(int i=0; i<line.length-1; i++) {
            int[] A = line[i];

            for(int j=i+1; j<line.length; j++) {
                int[] B = line[j];
                double[] C = contact(A, B);

                if(C[0] == (long) C[0] && C[1] == (long) C[1]) {
                    Point point = new Point((long) C[0], (long) C[1]);
                    if(!points.contains(point)) points.add(point);
                }
            }
        }

        return draw(points);
    }
}
