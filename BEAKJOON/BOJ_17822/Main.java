package BEAKJOON.BOJ_17822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int[][] disks, commands;
    public static int n, m, t;

    public static void rotate(int[] disk, int di) {
        int backup;

        if(di == 0) {
            backup = disk[m];

            for(int i=m; i>0; i--) {
                disk[i] = disk[i-1];
            }
            disk[1] = backup;
        }else if(di == 1){
            backup = disk[1];

            for(int i=1; i<m; i++) {
                disk[i] = disk[i+1];
            }
            disk[m] = backup;
        }
    }

    public static void removeOrChange() {
        boolean[][] check = new boolean[n+1][m+1];
        boolean checked = false;
        int sum = 0, cnt = 0;

        for(int i=1; i<=n; i++) {
            if(disks[i][1] != 0 && disks[i][m] != 0 && disks[i][1] == disks[i][m]) {
                check[i][1] = check[i][m] = true;
            }
            for(int j=1; j<m; j++) {
                if(disks[i][j] != 0 && disks[i][j+1] != 0 && disks[i][j] == disks[i][j+1]) {
                    check[i][j] = check[i][j+1] = true;
                }
            }
        }
        for(int i=1; i<=m; i++) {
            for(int j=1; j<n; j++) {
                if(disks[j][i] != 0 && disks[j+1][i] != 0 && disks[j][i] == disks[j+1][i]) {
                    check[j][i] = check[j+1][i] = true;
                }
            }
        }
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if(check[i][j]) {
                    disks[i][j] = 0;
                    checked = true;
                }
                if(disks[i][j] > 0) cnt++;
                sum += disks[i][j];
            }
        }

        if(!checked) {
            double average = (double) sum / cnt;

            for(int i=1; i<=n; i++) {
                for(int j=1; j<=m; j++) {
                    if(disks[i][j] > 0) {
                        if(disks[i][j] < average) disks[i][j]++;
                        else if(disks[i][j] > average) disks[i][j]--;
                    }
                }
            }
        }
    }

    public static int operate() {
        int ret = 0;

        for(int k=0; k<t; k++) {
            int xi = commands[k][0];
            int di = commands[k][1];
            int ki = commands[k][2];

            for(int i=xi; i<=n; i+=xi) {
                for(int j=0; j<ki; j++) {
                    rotate(disks[i], di);
                }
            }
            removeOrChange();
        }

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                ret += disks[i][j];
            }
        }

        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken()); // 총 회전하는 횟수
        disks = new int[n+1][m+1];
        commands = new int[t][3];

        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1; j<=m; j++) {
                disks[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<t; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<3; j++) {
                commands[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(operate());
    }
}