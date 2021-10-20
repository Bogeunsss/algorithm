package BEAKJOON.BOJ_20061;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int[][] B = new int[4][6];
    public static int[][] G = new int[6][4];
    public static int[][] blocks;
    public static int n;
    public static int answer = 0;

    public static void initialize() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        blocks = new int[n][3];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<3; j++) {
                blocks[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void green(int t, int x, int y) {

        for(int i=0; i<6; i++) {
            if(t == 1) {
                if(i == 5) G[i][y] = 1;
                else {
                    if(G[i][y] == 0 && G[i+1][y] == 1) {
                        G[i][y] = 1;
                        break;
                    }
                }
            }else if(t == 2) {
                if(i == 5) G[i][y] = G[i][y+1] = 1;
                else {
                    if(G[i][y] == 0 && G[i][y+1] == 0) {
                        if(G[i+1][y] == 1 || G[i+1][y+1] == 1) {
                            G[i][y] = G[i][y+1] = 1;
                            break;
                        }
                    }
                }
            }else {
                if(i == 4) {
                    G[i][y] = G[i+1][y] = 1;
                    break;
                }else {
                    if(G[i][y] == 0 && G[i+1][y] == 0) {
                        if(G[i+2][y] == 1) {
                            G[i][y] = G[i+1][y] = 1;
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void blue(int t, int x, int y) {

        for(int i=0; i<6; i++) {
            if(t == 1) {
                if(i == 5) B[x][i] = 1;
                else {
                    if(B[x][i] == 0 && B[x][i+1] == 1) {
                        B[x][i] = 1;
                        break;
                    }
                }
            }else if(t == 2) {
                if(i == 4) {
                    B[x][i] = B[x][i+1] = 1;
                    break;
                }else {
                    if(B[x][i] == 0 && B[x][i+1] == 0) {
                        if(B[x][i+2] == 1) {
                            B[x][i] = B[x][i+1] = 1;
                            break;
                        }
                    }
                }
            }else {
                if(i == 5) B[x][i] = B[x+1][i] = 1;
                else {
                    if(B[x][i] == 0 && B[x+1][i] == 0) {
                        if(B[x][i+1] == 1 || B[x+1][i+1] == 1) {
                            B[x][i] = B[x+1][i] = 1;
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void specialZone() {
        int cnt = 0;

        for(int i=0; i<=1; i++) {
            for(int j=0; j<4; j++) {
                if(G[i][j] == 1) {
                    cnt++;
                    break;
                }
            }
        }
        while(cnt-- > 0) {
            for(int i=0; i<4; i++) {
                for(int j=5; j>0; j--) {
                    G[j][i] = G[j-1][i];
                }
                G[0][i] = 0;
            }
        }

        cnt = 0;
        for(int i=0; i<=1; i++) {
            for(int j=0; j<4; j++) {
                if(B[j][i] == 1) {
                    cnt++;
                    break;
                }
            }
        }
        while(cnt-- > 0) {
            for(int i=0; i<4; i++) {
                for(int j=5; j>0; j--) {
                    B[i][j] = B[i][j-1];
                }
                B[i][0] = 0;
            }
        }
    }

    public static void remove() {
        int line = 0, gIdx = -1, bIdx = -1;

        for(int i=2; i<6; i++) {
            int cnt = 0;

            for(int j=0; j<4; j++) {
                if(G[i][j] == 1) cnt++;
            }
            if(cnt == 4) {
                for(int j=0; j<4; j++) G[i][j] = 0;
                gIdx = i;
                line++;
            }
        }

        answer += line;
        while(line-- > 0) {
            for(int i=0; i<4; i++) {
                for(int j=gIdx; j>0; j--) {
                    if(G[j][i] == 1) continue;
                    int temp = G[j][i];
                    G[j][i] = G[j-1][i];
                    G[j-1][i] = temp;
                }
            }
        }

        line = 0;
        for(int i=2; i<6; i++) {
            int cnt = 0;

            for(int j=0; j<4; j++) {
                if(B[j][i] == 1) cnt++;
            }
            if(cnt == 4) {
                for(int j=0; j<4; j++) B[j][i] = 0;
                bIdx = i;
                line++;
            }
        }

        answer += line;
        while(line-- > 0) {

            for(int i=0; i<4; i++) {
                for(int j=bIdx; j>0; j--) {
                    if(B[i][j] == 1) continue;
                    int temp = B[i][j];
                    B[i][j] = B[i][j-1];
                    B[i][j-1] = temp;
                }
            }
        }
    }

    public static void play() {

        for(int[] block : blocks) {
            int t = block[0];
            int x = block[1];
            int y = block[2];

             green(t, x, y);
             blue(t, x, y);

             remove();
             specialZone();
        }
    }

    public static void finish() {
        int cnt = 0;

        for(int i=0; i<6; i++) {
            for(int j=0; j<4; j++) {
                if(G[i][j] == 1) cnt++;
                if(B[j][i] == 1) cnt++;
            }
        }

        System.out.println(answer);
        System.out.println(cnt);
    }

    public static void main(String[] args) throws IOException {

        initialize();
        play();
        finish();
        
    }
}