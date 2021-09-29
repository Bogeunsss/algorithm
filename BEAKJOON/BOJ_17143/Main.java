package BEAKJOON.BOJ_17143;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static final int[] dx = {-1, 1, 0, 0};
    public static final int[] dy = {0, 0, 1, -1};

    public static int R, C, answer;
    public static List<Shark> store;
    public static PriorityQueue<Shark> sharks = new PriorityQueue<>(new Comparator<Shark>() {
        @Override
        public int compare(Shark o1, Shark o2) {
            if(o1.c == o2.c) {
                return o1.r - o2.r;
            }
            return o1.c - o2.c;
        }
    });

    public static class Shark {
        int r;
        int c;
        int speed;
        int direction;
        int size;

        public Shark(int r, int c, int speed, int direction, int size) {
            this.r = r;
            this.c = c;
            this.speed = speed;
            this.direction = direction;
            this.size = size;
        }
    }

    public static void predation() {
        store = new ArrayList<>();
        store.addAll(sharks);

        for(int i=0; i<store.size()-1; i++) {
            Shark A = store.get(i);

            if(A == null) continue;
            for(int j=i+1; j<store.size(); j++) {
                Shark B = store.get(j);

                if(B == null) continue;
                if(A.r == B.r && A.c == B.c) {
                    if(B.size > A.size) {
                        store.set(i, null);
                        break;
                    }else {
                        store.set(j, null);
                    }
                }
            }
        }

        for(int i=0; i<store.size(); i++) {
            if(store.get(i) != null) {
                sharks.offer(store.get(i));
            }
        }
    }

    public static void move() {
        store = new ArrayList<>();
        int len = sharks.size();

        while(!sharks.isEmpty() && len-- > 0) {
            Shark shark = sharks.poll();
            int speed = shark.speed;
            int d = 1;

            while(speed-- > 0) {
                int nx = shark.r + dx[shark.direction-1] * d;
                int ny = shark.c + dy[shark.direction-1] * d;

                if(nx <= 1 || nx >= R || ny <= 1 || ny >= C) {
                    d *= -1;
                }
                shark.r = nx;
                shark.c = ny;
            }
            if(d == -1) {
                if(shark.direction == 1) shark.direction = 2;
                else if(shark.direction == 2) shark.direction = 1;
                else if(shark.direction == 3) shark.direction = 4;
                else shark.direction = 3;
            }

            store.add(shark);
        }

        sharks.addAll(store);
    }

    public static void fishing() {
        int len;

        for(int i=1; i<=C; i++) {
            store = new ArrayList<>();
            len = sharks.size();

            while(!sharks.isEmpty() && len-- > 0) {
                Shark shark = sharks.poll();

                if(shark.c == i) {
                    answer += shark.size;
                    break;
                }
                store.add(shark);
            }

            sharks.addAll(store);
            move();
            predation();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        answer = 0;

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            sharks.offer(new Shark(r, c, s, d, z));
        }

        fishing();

        System.out.println(answer);
    }
}
