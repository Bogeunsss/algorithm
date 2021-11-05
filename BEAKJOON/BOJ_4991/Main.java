package BEAKJOON.BOJ_4991;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static final int[] dx = {-1, 1, 0, 0};
    public static final int[] dy = {0, 0, -1, 1};
    public static final int INF = Integer.MAX_VALUE;

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int w, h;
    public static char[][] map;
    public static int[][] graph;
    public static boolean[] visited;
    public static int[] route;
    public static List<int[]> dust;
    public static int sx, sy, answer;

    public static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        if(w == 0 && h == 0) return;

        map = new char[h][w];
        dust = new ArrayList<>();
        answer = INF;

        for(int i=0; i<h; i++) {
            String row = br.readLine();
            for(int j=0; j<w; j++) {
                map[i][j] = row.charAt(j);

                if(map[i][j] == 'o') {
                    sx = i;
                    sy = j;
                }else if(map[i][j] == '*') {
                    dust.add(new int[]{i, j, 0});
                }
            }
        }

        graph = new int[dust.size()+1][dust.size()+1];
        route = new int[dust.size()+1];
    }

    public static int cleaning(int _x, int _y, int idx) {

        Queue<int[]> q = new LinkedList<>();
        int[][] d = new int[h][w];

        for(int i=0; i<h; i++) Arrays.fill(d[i], INF);
        q.offer(new int[]{_x, _y});
        d[_x][_y] = 0;

        while(!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.peek()[1];
            q.poll();

            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
                if(map[nx][ny] == 'x') continue;
                if(d[nx][ny] > d[x][y] + 1) {
                    d[nx][ny] = d[x][y] + 1;
                    q.offer(new int[]{nx, ny});
                }
            }
        }

        return d[dust.get(idx)[0]][dust.get(idx)[1]];
    }

    public static void distanceCalculating(int now, int distance, int count) {

        if(distance > answer) return;
        if(count == dust.size()) {
            answer = Math.min(answer, distance);
            return;
        }
        for(int next=0; next<dust.size(); next++) {
            if(now == next) continue;
            if(graph[now][next] == INF) continue;
            if(!visited[next]) {
                visited[next] = true;
                route[next] = now;
                distanceCalculating(next, distance + graph[now][next], count + 1);
                visited[next] = false;
            }
        }
    }

    public static void robotCleaner() throws IOException {

        while(true) {
            input();

            int size = dust.size();

            if(w == 0 && h == 0) break;
            for(int i=0; i<size; i++) {
                dust.get(i)[2] = cleaning(sx, sy, i);

                graph[size][i] = dust.get(i)[2];
                graph[i][size] = dust.get(i)[2];
            }

            dust.sort(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o2[2] - o1[2];
                }
            });

            if(dust.get(0)[2] == INF) {
                System.out.println(-1);
                continue;
            }
            for(int i=0; i<size; i++) {
                int x = dust.get(i)[0];
                int y = dust.get(i)[1];

                for(int j=0; j<size; j++) {
                    if(i == j) continue;

                    int distance = cleaning(x, y, j);
                    graph[i][j] = distance;
                    graph[j][i] = distance;
                }
            }
            for(int i=0; i<size; i++) {
                if(dust.get(i)[2] == INF) continue;

                visited = new boolean[size+1];
                visited[i] = true;
                distanceCalculating(i, dust.get(i)[2], 1);
                visited[i] = false;
            }

            System.out.println(answer == INF ? -1 : answer);
        }
    }

    public static void main(String[] args) throws IOException {

        robotCleaner();
    }
}