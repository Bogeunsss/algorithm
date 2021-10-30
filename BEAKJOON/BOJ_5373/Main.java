package BEAKJOON.BOJ_5373;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static char[] colors = {'w', 'y', 'o', 'g', 'r', 'b'};

    public static int T;
    public static int[] N;
    public static List<List<String>> commands;
    public static char[][][] cube;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        N = new int[T];
        commands = new ArrayList<>();

        for(int tc=0; tc<T; tc++) {
            N[tc] = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), " ");
            commands.add(new ArrayList<>());

            for(int i = 0; i< N[tc]; i++) {
                commands.get(tc).add(st.nextToken());
            }
        }
    }

    public static void initialize() {

        cube = new char[6][3][3];

        for(int i=0; i<6; i++) {
            for(int j=0; j<3; j++) {
                for(int k=0; k<3; k++) {
                    cube[i][j][k] = colors[i];
                }
            }
        }
    }

    public static void rotate(int color, boolean clockwise) {

        char[][] side = cube[color];
        char[] backup = new char[3];

        for(int i=0; i<3; i++) {
            backup[i] = side[0][i];
        }

        if(clockwise) {
            for(int i=0; i<3; i++) side[0][i] = side[2-i][0];
            for(int i=2; i>0; i--) side[i][0] = side[2][i];
            for(int i=2; i>0; i--) side[2][i] = side[2-i][2];
            for(int i=0; i<3; i++) side[i][2] = backup[i];
        }else {
            for(int i=0; i<3; i++) side[0][i] = side[i][2];
            for(int i=0; i<3; i++) side[i][2] = side[2][2-i];
            for(int i=2; i>0; i--) side[2][i] = side[i][0];
            for(int i=2; i>0; i--) side[i][0] = backup[2-i];
        }
    }

    public static void sideRotate(int color, boolean clockwise) {

        char[] backup = new char[3];

        if(clockwise) {
            if(color == 0) {
                for(int i=0; i<3; i++) backup[i] = cube[2][0][i];
                for(int i=0; i<3; i++) cube[2][0][i] = cube[3][0][i];
                for(int i=0; i<3; i++) cube[3][0][i] = cube[4][0][i];
                for(int i=0; i<3; i++) cube[4][0][i] = cube[5][0][i];
                for(int i=0; i<3; i++) cube[5][0][i] = backup[i];
            }else if(color == 1) {
                for(int i=0; i<3; i++) backup[i] = cube[4][2][i];
                for(int i=0; i<3; i++) cube[4][2][i] = cube[3][2][i];
                for(int i=0; i<3; i++) cube[3][2][i] = cube[2][2][i];
                for(int i=0; i<3; i++) cube[2][2][i] = cube[5][2][i];
                for(int i=0; i<3; i++) cube[5][2][i] = backup[i];
            }else if(color == 2) {
                for(int i=0; i<3; i++) backup[i] = cube[0][0][i];
                for(int i=0; i<3; i++) cube[0][0][i] = cube[5][i][2];
                for(int i=0; i<3; i++) cube[5][i][2] = cube[1][2][2-i];
                for(int i=0; i<3; i++) cube[1][2][2-i] = cube[3][2-i][0];
                for(int i=0; i<3; i++) cube[3][2-i][0] = backup[i];
            }else if(color == 3) {
                for(int i=0; i<3; i++) backup[i] = cube[0][i][0];
                for(int i=0; i<3; i++) cube[0][i][0] = cube[2][2-i][2];
                for(int i=0; i<3; i++) cube[2][2-i][2] = cube[1][i][0];
                for(int i=0; i<3; i++) cube[1][i][0] = cube[4][i][0];
                for(int i=0; i<3; i++) cube[4][i][0] = backup[i];
            }else if(color == 4) {
                for(int i=0; i<3; i++) backup[i] = cube[0][2][i];
                for(int i=0; i<3; i++) cube[0][2][i] = cube[3][2-i][2];
                for(int i=0; i<3; i++) cube[3][2-i][2] = cube[1][0][2-i];
                for(int i=0; i<3; i++) cube[1][0][2-i] = cube[5][i][0];
                for(int i=0; i<3; i++) cube[5][i][0] = backup[i];
            }else {
                for(int i=0; i<3; i++) backup[i] = cube[0][i][2];
                for(int i=0; i<3; i++) cube[0][i][2] = cube[4][i][2];
                for(int i=0; i<3; i++) cube[4][i][2] = cube[1][i][2];
                for(int i=0; i<3; i++) cube[1][i][2] = cube[2][2-i][0];
                for(int i=0; i<3; i++) cube[2][2-i][0] = backup[i];
            }
        }else {
            if(color == 0) {
                for(int i=0; i<3; i++) backup[i] = cube[2][0][i];
                for(int i=0; i<3; i++) cube[2][0][i] = cube[5][0][i];
                for(int i=0; i<3; i++) cube[5][0][i] = cube[4][0][i];
                for(int i=0; i<3; i++) cube[4][0][i] = cube[3][0][i];
                for(int i=0; i<3; i++) cube[3][0][i] = backup[i];
            }else if(color == 1) {
                for(int i=0; i<3; i++) backup[i] = cube[4][2][i];
                for(int i=0; i<3; i++) cube[4][2][i] = cube[5][2][i];
                for(int i=0; i<3; i++) cube[5][2][i] = cube[2][2][i];
                for(int i=0; i<3; i++) cube[2][2][i] = cube[3][2][i];
                for(int i=0; i<3; i++) cube[3][2][i] = backup[i];
            }else if(color == 2) {
                for(int i=0; i<3; i++) backup[i] = cube[0][0][i];
                for(int i=0; i<3; i++) cube[0][0][i] = cube[3][2-i][0];
                for(int i=0; i<3; i++) cube[3][2-i][0] = cube[1][2][2-i];
                for(int i=0; i<3; i++) cube[1][2][2-i] = cube[5][i][2];
                for(int i=0; i<3; i++) cube[5][i][2] = backup[i];
            }else if(color == 3) {
                for(int i=0; i<3; i++) backup[i] = cube[0][i][0];
                for(int i=0; i<3; i++) cube[0][i][0] = cube[4][i][0];
                for(int i=0; i<3; i++) cube[4][i][0] = cube[1][i][0];
                for(int i=0; i<3; i++) cube[1][i][0] = cube[2][2-i][2];
                for(int i=0; i<3; i++) cube[2][2-i][2] = backup[i];
            }else if(color == 4) {
                for(int i=0; i<3; i++) backup[i] = cube[0][2][i];
                for(int i=0; i<3; i++) cube[0][2][i] = cube[5][i][0];
                for(int i=0; i<3; i++) cube[5][i][0] = cube[1][0][2-i];
                for(int i=0; i<3; i++) cube[1][0][2-i] = cube[3][2-i][2];
                for(int i=0; i<3; i++) cube[3][2-i][2] = backup[i];
            }else {
                for(int i=0; i<3; i++) backup[i] = cube[0][i][2];
                for(int i=0; i<3; i++) cube[0][i][2] = cube[2][2-i][0];
                for(int i=0; i<3; i++) cube[2][2-i][0] = cube[1][i][2];
                for(int i=0; i<3; i++) cube[1][i][2] = cube[4][i][2];
                for(int i=0; i<3; i++) cube[4][i][2] = backup[i];
            }
        }
    }

    public static void cubing() {

        for(int tc=0; tc<T; tc++) {
            initialize();

            int n = N[tc];
            List<String> command = commands.get(tc);

            for(int i=0; i<n; i++) {
                String cmd = command.get(i);
                int color = -1;

                if(cmd.charAt(0) == 'U') color = 0;
                else if(cmd.charAt(0) == 'D') color = 1;
                else if(cmd.charAt(0) == 'B') color = 2;
                else if(cmd.charAt(0) == 'L') color = 3;
                else if(cmd.charAt(0) == 'F') color = 4;
                else color = 5;


                rotate(color, cmd.charAt(1) == '+');
                sideRotate(color, cmd.charAt(1) == '+');
            }

            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    System.out.print(cube[0][i][j]);
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) throws IOException {

        input();
        cubing();
    }
}