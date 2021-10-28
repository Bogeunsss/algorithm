package BEAKJOON.BOJ_21611;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static final int[] dx = {0, -1, 1, 0, 0};
    public static final int[] dy = {0, 0, 0, -1, 1};

    public static int n, m;
    public static int[][] map, magic;
    public static int[] answer;

    public static Map<Integer,Position> numbering;
    public static Map<Position,Integer> positions;
    public static List<Integer> beads;

    public static class Position {

        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return x == position.x && y == position.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void setupNumber() {

        int number = n * n;
        int x = 1, y = 0, pivot = 1;
        int len = n;

        for(int i=0; i<n; i++) {
            for(int j=0; j<len; j++) {
                y += pivot;
                numbering.put(--number, new Position(x, y));
                positions.put(new Position(x, y), number);
            }
            len--;
            for(int j=0; j<len; j++) {
                x += pivot;
                numbering.put(--number, new Position(x, y));
                positions.put(new Position(x, y), number);
            }
            pivot *= -1;
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n+1][n+1];
        magic = new int[m][2];
        answer = new int[4];

        numbering = new HashMap<>();
        positions = new HashMap<>();
        beads = new ArrayList<>();

        for(int i=0; i<n*n; i++) beads.add(0);
        setupNumber();

        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1; j<=n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                beads.set(positions.get(new Position(i, j)), map[i][j]);
            }
        }
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            magic[i][0] = Integer.parseInt(st.nextToken());
            magic[i][1] = Integer.parseInt(st.nextToken());
        }
    }

    public static void push() {

        while(true) {
            boolean isChange = false;

            for(int i=1; i<beads.size()-1; i++) {
                if(beads.get(i) == 0 && beads.get(i + 1) > 0) {
                    isChange = true;

                    int bead = beads.get(i);
                    beads.set(i, beads.get(i + 1));
                    beads.set(i + 1, bead);

                    Position prev = numbering.get(i);
                    Position next = numbering.get(i + 1);
                    map[prev.x][prev.y] = beads.get(i);
                    map[next.x][next.y] = beads.get(i + 1);
                }
            }
            if(!isChange) break;
        }
    }

    public static void icePiece(int idx) {

        int d = magic[idx][0];
        int s = magic[idx][1];
        int x = (n + 1) / 2, y = (n + 1) / 2;

        for(int i=1; i<=s; i++) {
            int nx = x + dx[d] * i;
            int ny = y + dy[d] * i;

            if(nx <= 0 || nx > n || ny < 0 || ny > n) break;

            map[nx][ny] = 0;
            beads.set(positions.get(new Position(nx, ny)), 0);
        }

        push();
    }

    public static void explode() {

        while(true) {
            int cnt = 1;
            boolean isExplode = false;

            for(int i=1; i<beads.size()-1; i++) {
                if(beads.get(i) > 0 && beads.get(i).equals(beads.get(i + 1))) {
                    cnt++;
                }else {
                    if(cnt >= 4) {
                        isExplode = true;
                        for(int j=i; j>i-cnt; j--) {
                            Position position = numbering.get(j);
                            answer[map[position.x][position.y]]++;
                            map[position.x][position.y] = 0;
                            beads.set(j, 0);
                        }
                    }
                    cnt = 1;
                }
            }

            push();

            if(!isExplode) break;
        }
    }

    public static void breeding() {

        List<Integer> update = new ArrayList<>();
        int cnt = 1;

        update.add(0);
        for(int i=1; i<beads.size()-1; i++) {
            if(beads.get(i) > 0) {
                if(beads.get(i).equals(beads.get(i + 1))) {
                    cnt++;
                }else {
                    update.add(cnt);
                    if(update.size() >= beads.size()) break;
                    update.add(beads.get(i));
                    if(update.size() >= beads.size()) break;
                    cnt = 1;
                }
            }
        }
        for(int i=update.size(); i<beads.size(); i++) {
            update.add(0);
        }

        beads = update;

        for(int i=1; i<beads.size(); i++) {
            Position position = numbering.get(i);

            map[position.x][position.y] = beads.get(i);
        }
    }

    public static void blizzard() {

        for(int i=0; i<m; i++) {
            icePiece(i);
            explode();
            breeding();
        }

        System.out.println(answer[1] + answer[2] * 2 + answer[3] * 3);
    }

    public static void main(String[] args) throws IOException {

        input();
        blizzard();

    }
}