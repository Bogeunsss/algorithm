package BEAKJOON.BOJ_2931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static final int[] dx = {-1, 1, 0, 0};
    public static final int[] dy = {0, 0, -1, 1};

    public static int r, c;
    public static boolean[][][] pipes;
    public static char[][] map;
    public static int[] moscow, zagreb;

    public static boolean[] setting(char c) {

        if(c == '|') return new boolean[]{true, true, false, false};
        if(c == '-') return new boolean[]{false, false, true, true};
        if(c == '+') return new boolean[]{true, true, true, true};
        if(c == '1') return new boolean[]{false, true, false, true};
        if(c == '2') return new boolean[]{true, false, false, true};
        if(c == '3') return new boolean[]{true, false, true, false};
        if(c == '4') return new boolean[]{false, true, true, false};
        return new boolean[]{false, false, false, false};
    }

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        pipes = new boolean[r][c][4];
        moscow = new int[2];
        zagreb = new int[2];

        for(int i=0; i<r; i++) {
            String row = br.readLine();
            for(int j=0; j<c; j++) {
                map[i][j] = row.charAt(j);
                pipes[i][j] = setting(map[i][j]);

                if(map[i][j] == 'M') {
                    moscow[0] = i;
                    moscow[1] = j;
                }else if(map[i][j] == 'Z') {
                    zagreb[0] = i;
                    zagreb[1] = j;
                }
            }
        }
    }

    public static boolean outOfRange(int x, int y) {
        return x < 0 || x >= r || y < 0 || y >= c;
    }

    public static int getOpposite(int d) {

        if(d == 0) return 1;
        if(d == 1) return 0;
        if(d == 2) return 3;
        return 2;
    }

    public static char getLabel(List<Integer> hacked) {

        if(hacked.size() == 4) {
            return '+';
        }

        int A = Math.min(hacked.get(0), hacked.get(1));
        int B = Math.max(hacked.get(0), hacked.get(1));

        if(A == 0 && B == 1) return '|';
        if(A == 2 && B == 3) return '-';
        if(A == 1 && B == 3) return '1';
        if(A == 0 && B == 3) return '2';
        if(A == 0 && B == 2) return '3';
        return '4';
    }

    public static void inspect(int _x, int _y, int _d) {

        Queue<int[]> q = new LinkedList<>();
        List<Integer> hacked = new ArrayList<>();
        int[] answer = new int[2];

        q.offer(new int[]{_x, _y, _d});

        while(!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.peek()[1];
            int d = q.peek()[2];
            q.poll();

            int nx = x + dx[d];
            int ny = y + dy[d];
            int nd = getOpposite(d);

            if(pipes[nx][ny][nd]) {
                if(pipes[nx][ny][d]) {
                    q.offer(new int[]{nx, ny, d});
                }else {
                    for(int i=0; i<4; i++) {
                        if(i == nd) continue;
                        if(pipes[nx][ny][i]) {
                            q.offer(new int[]{nx, ny, i});
                            break;
                        }
                    }
                }
            }else {
                answer[0] = nx + 1;
                answer[1] = ny + 1;

                for(int i=0; i<4; i++) {
                    int nnx = nx + dx[i];
                    int nny = ny + dy[i];
                    int nnd = getOpposite(i);

                    if(outOfRange(nnx, nny)) continue;
                    if(pipes[nnx][nny][nnd]) hacked.add(i);
                }
                break;
            }
        }

        System.out.println(answer[0] + " " + answer[1] + " " + getLabel(hacked));
    }

    public static void gasPipe() {

        for(int i=0; i<4; i++) {
            int nx = moscow[0] + dx[i];
            int ny = moscow[1] + dy[i];
            int nd = getOpposite(i);

            if(outOfRange(nx, ny)) continue;
            if(pipes[nx][ny][nd]) {
                inspect(moscow[0], moscow[1], i);
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        input();
        gasPipe();
    }
}