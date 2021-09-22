package BEAKJOON.BOJ_15683;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static final int[] d = {0, 4, 2, 4, 4, 1};

    public static int[][] office;
    public static int n, m;
    public static int size = 0, answer = Integer.MAX_VALUE;

    public static List<CCTV> list = new ArrayList<>();

    public static class CCTV {
        int x;
        int y;
        int type;

        public CCTV(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    public static int[][] copy() {
        int[][] ret = new int[n][m];

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                ret[i][j] = office[i][j];
            }
        }

        return ret;
    }

    public static void watch(int dir, CCTV cctv) {
        switch(dir % 4) {
            case 0:
                for(int i=cctv.y+1; i<m; i++) {
                    if(office[cctv.x][i] == 6) break;
                    office[cctv.x][i] = -1;
                }
                break;
            case 1:
                for(int i=cctv.x-1; i>=0; i--) {
                    if(office[i][cctv.y] == 6) break;
                    office[i][cctv.y] = -1;
                }
                break;
            case 2:
                for(int i=cctv.y-1; i>=0; i--) {
                    if(office[cctv.x][i] == 6) break;
                    office[cctv.x][i] = -1;
                }
                break;
            case 3:
                for(int i=cctv.x+1; i<n; i++) {
                    if(office[i][cctv.y] == 6) break;
                    office[i][cctv.y] = -1;
                }
                break;
        }
    }

    public static void select(int index) {
        if(index == size) {
            int count = 0;

            for(int i=0; i<n; i++) {
                for(int j=0; j<m; j++) {
                    if(office[i][j] == 0) count++;
                }
            }
            answer = Math.min(answer, count);
            return;
        }

        int[][] backup;
        int type = list.get(index).type;
        CCTV cctv = list.get(index);

        for(int i=0; i<d[type]; i++) {
            backup = copy();

            switch(type) {
                case 1:
                    watch(i, cctv);
                    break;
                case 2:
                    watch(i, cctv);
                    watch(i + 2, cctv);
                    break;
                case 3:
                    watch(i, cctv);
                    watch(i + 1, cctv);
                    break;
                case 4:
                    watch(i, cctv);
                    watch(i + 1, cctv);
                    watch(i + 2, cctv);
                    break;
                case 5:
                    watch(i, cctv);
                    watch(i + 1, cctv);
                    watch(i + 2, cctv);
                    watch(i + 3, cctv);
                    break;
            }
            select(index + 1);
            office = backup;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        office = new int[n][m];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            for(int j=0; j<m; j++) {
                office[i][j] = Integer.parseInt(st.nextToken());

                if(office[i][j] != 0 && office[i][j] != 6) {
                    list.add(new CCTV(i, j, office[i][j]));
                    size++;
                }
            }
        }

        select(0);

        System.out.println(answer);
    }
}