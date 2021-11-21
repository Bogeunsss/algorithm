package BEAKJOON.BOJ_13913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static final int MAX = 100000;

    public static int n, k;
    public static int[] map, path;
    public static boolean[] visited;

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[MAX*2+1];
        path = new int[MAX*2+1];
        visited = new boolean[MAX*2+1];
    }

    public static int getNext(int k, int v) {

        if(k == 0) return v - 1;
        if(k == 1) return v + 1;
        return v * 2;
    }

    public static void play() {

        Queue<Integer> q = new LinkedList<>();

        Arrays.fill(map, -1);

        q.offer(n);
        visited[n] = true;
        path[n] = -1;

        while(!q.isEmpty()) {
            int now = q.poll();

            for(int i=0; i<3; i++) {
                int next = getNext(i, now);

                if(next < 0 || next > MAX * 2) continue;
                if(visited[next]) continue;

                path[next] = now;
                visited[next] = true;
                q.offer(next);
            }
        }
    }

    public static void findPath() {

        List<Integer> answer = new ArrayList<>();

        answer.add(k);
        while(path[k] != -1) {
            answer.add(path[k]);
            k = path[k];
        }

        System.out.println(answer.size() - 1);
        for(int i=answer.size()-1; i>=0; i--) {
            System.out.print(answer.get(i) + " ");
        }
    }

    public static void hideAndSeek() {

        if(n == k) {
            System.out.println(0);
            System.out.println(n);
            return;
        }
        play();
        findPath();
    }

    public static void main(String[] args) throws IOException {

        input();
        hideAndSeek();
    }
}