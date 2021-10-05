package BEAKJOON.BOJ_17140;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static final int MAX = 101;

    public static int[][] matrix = new int[MAX][MAX];
    public static int r, c, k;

    public static int operation() {
        Map<Integer,Integer> map;
        int time = 0;
        int row = 3, col = 3;

        List<int[]> list;
        int[] count;
        int max;

        while(time <= 100) {
            if(matrix[r][c] == k) return time;

            max = 0;
            if(row >= col) {

                for(int i=1; i<=row; i++) {
                    list = new ArrayList<>();
                    count = new int[MAX];
                    int index = 1;

                    for(int j=1; j<=col; j++) count[matrix[i][j]]++;
                    for(int j=1; j<MAX; j++) {
                        if(count[j] != 0) {
                            list.add(new int[]{j, count[j]});
                        }
                    }

                    Collections.sort(list, new Comparator<int[]>() {
                        @Override
                        public int compare(int[] o1, int[] o2) {
                            if(o1[1] == o2[1]) return o1[0] - o2[0];
                            return o1[1] - o2[1];
                        }
                    });

                    for(int j=1; j<=col; j++) matrix[i][j] = 0;
                    for(int j=0; j<list.size(); j++) {
                        matrix[i][index++] = list.get(j)[0];
                        matrix[i][index++] = list.get(j)[1];
                    }
                    max = Math.max(max, index - 1);
                }

                col = max;
            }else {
                for(int i=1; i<=col; i++) {
                    list = new ArrayList<>();
                    count = new int[MAX];
                    int index = 1;

                    for(int j=1; j<=row; j++) count[matrix[j][i]]++;
                    for(int j=1; j<MAX; j++) {
                        if(count[j] != 0) {
                            list.add(new int[]{j, count[j]});
                        }
                    }

                    Collections.sort(list, new Comparator<int[]>() {
                        @Override
                        public int compare(int[] o1, int[] o2) {
                            if(o1[1] == o2[1]) return o1[0] - o2[0];
                            return o1[1] - o2[1];
                        }
                    });

                    for(int j=1; j<=row; j++) matrix[j][i] = 0;
                    for(int j=0; j<list.size(); j++) {
                        matrix[index++][i] = list.get(j)[0];
                        matrix[index++][i] = list.get(j)[1];
                    }
                    max = Math.max(max, index - 1);
                }

                row = max;
            }

            time++;
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i=1; i<=3; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            for(int j=1; j<=3; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if(matrix[r][c] == k) System.out.println(0);
        else System.out.println(operation());
    }
}